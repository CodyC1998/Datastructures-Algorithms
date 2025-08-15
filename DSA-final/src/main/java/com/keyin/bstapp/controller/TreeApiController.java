package com.keyin.bstapp.controller;

import com.keyin.bstapp.model.BstNode;
import com.keyin.bstapp.model.dto.ProcessRequest;
import com.keyin.bstapp.model.dto.TreeResponse;
import com.keyin.bstapp.service.BstService;
import com.keyin.bstapp.service.TreeRecordService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TreeApiController {

    private final BstService bstService;
    private final TreeRecordService recordService;

    public TreeApiController(BstService bstService, TreeRecordService recordService){
        this.bstService=bstService;
        this.recordService=recordService;
    }

    @PostMapping(value="/process-numbers",produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TreeResponse> process(
            @RequestParam(name="inputString",required=false) String inputString,
            @RequestBody(required=false) ProcessRequest json,
            @RequestParam(name="balanced",defaultValue="false") boolean balanced
    ){
        List<Integer> numbers=null;
        String originalInput=null;

        if(json!=null && json.getNumbers()!=null && !json.getNumbers().isEmpty()){
            numbers=json.getNumbers();
            originalInput=numbers.toString();
        }else if(inputString!=null && !inputString.isBlank()){
            numbers=bstService.parseInput(inputString);
            originalInput=inputString;
        }else{
            return ResponseEntity.badRequest().build();
        }

        BstNode root=bstService.buildSequential(numbers);
        if(balanced) root=bstService.toBalanced(root);
        TreeResponse resp=bstService.toResponse(root);
        recordService.save(originalInput,resp);
        return ResponseEntity.ok(resp);
    }

    @GetMapping(value="/previous-trees",produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> previousTrees(){
        return ResponseEntity.ok(recordService.listAllNewestFirst());
    }
}
