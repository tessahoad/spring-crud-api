package com.elsevier.techassociates.springcrudapi.api.requests;

public class BookRequest {
    private String title;
    private Integer publishYear;

    public BookRequest() {}

    public BookRequest(String title, Integer publishYear) {
        this.title = title;
        this.publishYear = publishYear;
    }

    public Integer getPublishYear() {
        return publishYear;
    }

    public String getTitle() {
        return title;
    }

}
