package com.mirkamal.todoassignmentapp.ui.fragments.todos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.mirkamal.todoassignmentapp.R;
import com.mirkamal.todoassignmentapp.model.entity.TodoItem;

public class TodoItemListAdapter extends ListAdapter<TodoItem, TodoItemViewHolder> {

    protected TodoItemListAdapter(@NonNull DiffUtil.ItemCallback<TodoItem> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public TodoItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new TodoItemViewHolder(inflater.inflate(R.layout.item_todo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TodoItemViewHolder holder, int position) {
        holder.bind(getItem(position));
    }
}
