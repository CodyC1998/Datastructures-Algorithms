package com.keyin.bstapp.model.dto;

import java.util.List;

public class ProcessRequest {

    private List<Integer> numbers;

    private String inputString;

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public String getInputString() {
        return inputString;
    }

    public void setInputString(String inputString) {
        this.inputString = inputString;
    }
}