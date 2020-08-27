package com.mirkamal.todoassignmentapp.network;

import com.mirkamal.todoassignmentapp.network.service.TodoItemsService;
import com.squareup.moshi.Moshi;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import com.mirkamal.todoassignmentapp.utils.Constants;

public class ApiInitHelper {

    private static ApiInitHelper instance;

    private Retrofit retrofit;
    private TodoItemsService service;

    private ApiInitHelper() {
        service = getClient().create(TodoItemsService.class);
    }

    private Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(
                            MoshiConverterFactory.create(
                                    new Moshi.Builder().build()
                            )
                    )
                    .baseUrl(Constants.BASE_URL)
                    .build();
        }
        return retrofit;
    }

    public static ApiInitHelper getInstance() {
        if (instance == null) {
            instance = new ApiInitHelper();
        }
        return instance;
    }

    public TodoItemsService getService() {
        return service;
    }

}
