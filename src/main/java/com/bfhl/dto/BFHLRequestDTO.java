package com.bfhl.dto;

import java.util.ArrayList;
import java.util.List;

public class BFHLRequestDTO {

    private List<String> data = new ArrayList<>();

    public BFHLRequestDTO() {
    }

    public BFHLRequestDTO(List<String> data) {
        this.data = data != null ? data : new ArrayList<>();
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data != null ? data : new ArrayList<>();
    }
}
