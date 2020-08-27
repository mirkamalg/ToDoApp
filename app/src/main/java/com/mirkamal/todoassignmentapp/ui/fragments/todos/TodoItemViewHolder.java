package com.mirkamal.todoassignmentapp.ui.fragments.todos;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mirkamal.todoassignmentapp.R;
import com.mirkamal.todoassignmentapp.model.entity.TodoItem;

public class TodoItemViewHolder extends RecyclerView.ViewHolder {

    public TodoItemViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bind(TodoItem item) {
        ((TextView) itemView.findViewById(R.id.text_view_title)).setText(item.getTitle());
        ((TextView) itemView.findViewById(R.id.text_view_content)).setText(item.getContent());
    }

}
