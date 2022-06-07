package com.elsevier.techassociates.springcrudapi.business.model;

public class Book {
    private String id;
    private String title;
    private Integer publishYear;

    public Book(String id, String title, Integer publishYear) {
        this.id = id;
        this.title = title;
        this.publishYear = publishYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(Integer publishYear) {
        this.publishYear = publishYear;
    }
}
