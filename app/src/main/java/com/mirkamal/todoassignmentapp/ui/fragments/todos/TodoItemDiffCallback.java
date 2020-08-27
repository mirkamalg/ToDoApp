package com.mirkamal.todoassignmentapp.ui.fragments.todos;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.mirkamal.todoassignmentapp.model.entity.TodoItem;

public class TodoItemDiffCallback extends DiffUtil.ItemCallback<TodoItem> {
    @Override
    public boolean areItemsTheSame(@NonNull TodoItem oldItem, @NonNull TodoItem newItem) {
        return oldItem.getId().equals(newItem.getId());
    }

    @SuppressLint("DiffUtilEquals")
    @Override
    public boolean areContentsTheSame(@NonNull TodoItem oldItem, @NonNull TodoItem newItem) {
        return oldItem == newItem;
    }
}
