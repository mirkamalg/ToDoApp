package com.mirkamal.todoassignmentapp.repository;

import com.mirkamal.todoassignmentapp.model.entity.TodoItem;
import com.mirkamal.todoassignmentapp.model.pojo.TodoItemPOJO;
import com.mirkamal.todoassignmentapp.network.service.TodoItemsService;
import com.mirkamal.todoassignmentapp.utils.Utils;
import com.mirkamal.todoassignmentapp.utils.lib.Observable;
import com.mirkamal.todoassignmentapp.utils.lib.ResponseCallBack;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TodoRepository {

    private TodoItemsService service;

    private Observable<List<TodoItem>> observable = new Observable<>();

    public TodoRepository(TodoItemsService service) {
        this.service = service;
    }

    public Observable<List<TodoItem>> getObservable() {
        return observable;
    }

    public void getAllTodoItems(ResponseCallBack callBack) {
        getAllTodoItemsFromNetwork(callBack);
    }

    private void getAllTodoItemsFromNetwork(ResponseCallBack callBack) {
        service.getAllTodoItems().enqueue(new Callback<List<TodoItemPOJO>>() {
            @Override
            public void onResponse(Call<List<TodoItemPOJO>> call, Response<List<TodoItemPOJO>> response) {
                if (response.code() == 200 && response.body() != null) {
                    List<TodoItem> items = Utils.convertTodoItemPOJOtoTodoItem(response.body());
                    observable.setValue(items);

                    callBack.handleTheResponseOfSuccessfulGet();
                }
            }

            @Override
            public void onFailure(Call<List<TodoItemPOJO>> call, Throwable t) {
                callBack.handleTheResponseOfFailedGet();
            }
        });
    }

    public void addNewTodoItemToNetworkDatabase(TodoItemPOJO item, ResponseCallBack callBack) {

        Call<TodoItemPOJO> call = service.addTodoItem(item);
        call.enqueue(new Callback<TodoItemPOJO>() {
            @Override
            public void onResponse(Call<TodoItemPOJO> call, Response<TodoItemPOJO> response) {
                callBack.handleTheResponseOfSuccessfulPost();
            }

            @Override
            public void onFailure(Call<TodoItemPOJO> call, Throwable t) {
                callBack.handleTheResponseOfFailedPost();
            }
        });
    }
}
