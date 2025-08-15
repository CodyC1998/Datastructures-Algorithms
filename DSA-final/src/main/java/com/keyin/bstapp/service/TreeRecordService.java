package com.keyin.bstapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.bstapp.entity.TreeRecord;
import com.keyin.bstapp.model.dto.TreeResponse;
import com.keyin.bstapp.repo.TreeRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeRecordService {

    private final TreeRecordRepository repo;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public TreeRecordService(TreeRecordRepository repo) {
        this.repo = repo;
    }

    public void save(String inputString, TreeResponse response) {
        try {
            String json = objectMapper.writeValueAsString(response);
            repo.save(new TreeRecord(inputString, json));
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize tree JSON", e);
        }
    }

    public List<TreeRecord> listAllNewestFirst() {
        List<TreeRecord> all = repo.findAll();
        all.sort((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()));
        return all;
    }
}
