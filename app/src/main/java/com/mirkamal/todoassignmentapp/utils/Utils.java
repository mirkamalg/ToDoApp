package com.mirkamal.todoassignmentapp.utils;

import com.mirkamal.todoassignmentapp.model.entity.TodoItem;
import com.mirkamal.todoassignmentapp.model.pojo.TodoItemPOJO;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static List<TodoItem> convertTodoItemPOJOtoTodoItem(List<TodoItemPOJO> pojos) {
        List<TodoItem> todoItems = new ArrayList<>();
        for (TodoItemPOJO itemPOJO:pojos) {
            todoItems.add(new TodoItem(itemPOJO.getUuid(), itemPOJO.getTitle(), itemPOJO.getContent()));
        }
        return todoItems;
    }

}
