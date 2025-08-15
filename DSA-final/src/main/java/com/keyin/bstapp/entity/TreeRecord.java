package com.keyin.bstapp.entity;

import jakarta.persistence.*;
import java.time.Instant;

@SuppressWarnings("unused")
@Entity
public class TreeRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2000)
    private String inputString;

    @Lob
    @Column(columnDefinition = "CLOB")
    private String treeJson;

    private Instant createdAt = Instant.now();

    public TreeRecord() {}

    public TreeRecord(String inputString, String treeJson) {
        this.inputString = inputString;
        this.treeJson = treeJson;
    }

    public Long getId() {return id;}
    public String getInputString() {return inputString;}
    public void setInputString(String inputString) {this.inputString = inputString;}
    public String getTreeJson() {return treeJson;}
    public void setTreeJson(String treeJson) {this.treeJson = treeJson;}
    public Instant getCreatedAt() {return createdAt;}
    public void setCreatedAt(Instant createdAt) {this.createdAt = createdAt;}
}
