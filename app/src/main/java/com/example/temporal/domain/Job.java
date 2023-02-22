package com.example.temporal.domain;

import java.io.File;
import java.util.Collection;

public class Job {

    public static String COLLECTION = "job_data";

    public static String NAME = "name";
    public static String DESCRIPTION = "description";
    public static String DURATION = "duration";
    public static String COMPANY = "company";

    private String name;
    private String description;
    private Integer duration;
    private String company;

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    private File image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Job() {

    }
}
