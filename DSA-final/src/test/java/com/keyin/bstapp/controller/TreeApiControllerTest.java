package com.keyin.bstapp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TreeApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void processNumbers_acceptsFormAndReturnsTreeJson() throws Exception {
        mvc.perform(
                        post("/process-numbers")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .content("inputString=8,3,10,1,6,14,4,7,13")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.root.value").value(8))
                .andExpect(jsonPath("$.root.left.value").value(3))
                .andExpect(jsonPath("$.root.right.value").value(10));
    }

    @Test
    void processNumbers_acceptsJsonBody() throws Exception {
        String body = "{\"numbers\": [8,3,10,1,6,14,4,7,13]}";

        mvc.perform(
                        post("/process-numbers?balanced=false")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(body)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.root.value").value(8));
    }

    @Test
    void previousTrees_returnsArrayAfterPostingOne() throws Exception {

        mvc.perform(
                post("/process-numbers")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content("inputString=5,2,7")
        ).andExpect(status().isOk());

        mvc.perform(get("/previous-trees"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].inputString").exists())
                .andExpect(jsonPath("$[0].treeJson").exists());
    }
}
