package com.mirkamal.todoassignmentapp.ui.fragments.sharetodos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.textfield.TextInputLayout;
import com.mirkamal.todoassignmentapp.R;
import com.mirkamal.todoassignmentapp.model.pojo.TodoItemPOJO;
import com.mirkamal.todoassignmentapp.network.ApiInitHelper;
import com.mirkamal.todoassignmentapp.repository.TodoRepository;
import com.mirkamal.todoassignmentapp.utils.lib.ResponseCallBack;

import java.util.Objects;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShareTodosFragment extends Fragment {

    private TodoRepository repository;

    private NavController navController;

    @BindView(R.id.edit_text_title)
    TextInputLayout editTextTitle;

    @BindView(R.id.edit_text_content)
    EditText editTextContent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_share_todos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        repository = new TodoRepository(ApiInitHelper.getInstance().getService());

        navController = NavHostFragment.findNavController(this);

        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.button_add)
    void onAddButtonClicked() {
        //TODO implement note addition algo

        String id = UUID.randomUUID().toString();
        String title = Objects.requireNonNull(editTextTitle.getEditText()).getText().toString();
        String content = editTextContent.getText().toString();

        repository.addNewTodoItemToNetworkDatabase(new TodoItemPOJO(id, title, content), new ResponseCallBack() {
            @Override
            public void handleTheResponseOfSuccessfulPost() {
                Toast.makeText(getContext(), "Task is added successfully!", Toast.LENGTH_SHORT).show();
                navController.popBackStack();
            }

            @Override
            public void handleTheResponseOfFailedPost() {
                Toast.makeText(getContext(), "Couldn't add the task, try again!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void handleTheResponseOfFailedGet() {
                // Not needed
            }

            @Override
            public void handleTheResponseOfSuccessfulGet() {
                //Not needed
            }
        });
    }
}
