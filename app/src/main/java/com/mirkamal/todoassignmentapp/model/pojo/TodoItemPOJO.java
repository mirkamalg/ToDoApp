package com.mirkamal.todoassignmentapp.model.pojo;

import com.squareup.moshi.Json;

public class TodoItemPOJO {

    @Json(name = "uuid")
    private String uuid;

    @Json(name = "title")
    private String title;

    @Json(name = "content")
    private String content;

    public TodoItemPOJO(String uuid, String title, String content) {
        this.uuid = uuid;
        this.title = title;
        this.content = content;
    }

    public String getUuid() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
