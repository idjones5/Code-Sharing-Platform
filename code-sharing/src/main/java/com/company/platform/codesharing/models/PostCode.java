package com.company.platform.codesharing.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class PostCode {
    private String id;

    public PostCode (String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
