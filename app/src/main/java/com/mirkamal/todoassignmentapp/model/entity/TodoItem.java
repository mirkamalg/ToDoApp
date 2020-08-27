package com.mirkamal.todoassignmentapp.model.entity;

public class TodoItem {

    private String id;
    private String title;
    private String content;

    public TodoItem(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
