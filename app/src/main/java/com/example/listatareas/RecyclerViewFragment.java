package com.example.listatareas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewFragment extends Fragment {

    private EditText editTextTaskRecycler;
    private Button buttonAddRecycler;
    private RecyclerView recyclerViewTasks;
    private Adapter adapter;
    private List<String> tasksList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview, container, false);

        editTextTaskRecycler = view.findViewById(R.id.editTextTaskRecycler);
        buttonAddRecycler = view.findViewById(R.id.buttonAddRecycler);
        recyclerViewTasks = view.findViewById(R.id.recyclerViewTasks);

        tasksList = new ArrayList<>();
        adapter = new Adapter(tasksList, getContext());
        recyclerViewTasks.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewTasks.setAdapter(adapter);

        buttonAddRecycler.setOnClickListener(v -> {
            String task = editTextTaskRecycler.getText().toString();
            if (!task.isEmpty()) {
                tasksList.add(task);
                adapter.notifyDataSetChanged();
                editTextTaskRecycler.setText("");
            }
        });

        return view;
    }
}

