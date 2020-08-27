package com.mirkamal.todoassignmentapp.ui.fragments.todos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mirkamal.todoassignmentapp.R;
import com.mirkamal.todoassignmentapp.model.entity.TodoItem;
import com.mirkamal.todoassignmentapp.network.ApiInitHelper;
import com.mirkamal.todoassignmentapp.repository.TodoRepository;
import com.mirkamal.todoassignmentapp.utils.lib.ResponseCallBack;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TodosFragment extends Fragment {

    private NavController navController;

    private TodoRepository repository;

    private TodoItemListAdapter adapter;

    private ResponseCallBack callBack;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.button_retry)
    MaterialButton buttonRetry;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        navController = NavHostFragment.findNavController(this);

        return inflater.inflate(R.layout.fragment_todos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        repository = new TodoRepository(ApiInitHelper.getInstance().getService());

        configureRecyclerView();

        configureCallBack();
    }

    private void configureCallBack() {
        callBack = new ResponseCallBack() {
            @Override
            public void handleTheResponseOfSuccessfulPost() {
                // Not needed
            }

            @Override
            public void handleTheResponseOfFailedPost() {
                // Not needed
            }

            @Override
            public void handleTheResponseOfFailedGet() {
                progressBar.setVisibility(View.INVISIBLE);
                buttonRetry.setVisibility(View.VISIBLE);

                Toast.makeText(getContext(), "Something went wrong, try again!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void handleTheResponseOfSuccessfulGet() {
                progressBar.setVisibility(View.INVISIBLE);
            }
        };
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setObserver();
    }

    @Override
    public void onStart() {
        super.onStart();

        getAllTodoItems(callBack);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        repository.getObservable().deleteObservers();
    }

    private void configureRecyclerView() {
        adapter = new TodoItemListAdapter(new TodoItemDiffCallback());
        recyclerView.setAdapter(adapter);
    }

    private void setObserver() {
        repository.getObservable().addObserver(new Observer() {
            @Override
            public void update(Observable observable, Object o) {
                adapter.submitList((List<TodoItem>) o);
            }
        });
    }

    private void getAllTodoItems(ResponseCallBack callBack) {
        repository.getAllTodoItems(callBack);
    }

    @OnClick(R.id.fab_add)
    void onFabClicked() {
        navController.navigate(R.id.shareTodosFragment);
    }

    @OnClick(R.id.button_retry)
    void onRetryClicked() {
        progressBar.setVisibility(View.VISIBLE);
        buttonRetry.setVisibility(View.INVISIBLE);
        getAllTodoItems(callBack);
    }
}
