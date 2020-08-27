package com.mirkamal.todoassignmentapp.network.service;

import com.mirkamal.todoassignmentapp.model.entity.TodoItem;
import com.mirkamal.todoassignmentapp.model.pojo.TodoItemPOJO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TodoItemsService {

    @GET("/all")
    Call<List<TodoItemPOJO>> getAllTodoItems();

    @POST("/add")
    Call<TodoItemPOJO> addTodoItem(@Body TodoItemPOJO item);
}
