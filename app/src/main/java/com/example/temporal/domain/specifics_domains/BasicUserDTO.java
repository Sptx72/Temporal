package com.example.temporal.domain.specifics_domains;

public class BasicUserDTO {

    private String uid;
    private String name;

    public BasicUserDTO(String uid, String name) {
        this.uid = uid;
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
