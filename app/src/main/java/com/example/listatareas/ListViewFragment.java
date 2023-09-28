package com.example.listatareas;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class ListViewFragment extends Fragment {

    private EditText editTextTask;
    private Button buttonAdd;
    private ListView listViewTasks;
    private ArrayAdapter<String> adapter;
    private List<String> tasksList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listview, container, false);

        editTextTask = view.findViewById(R.id.editTextTask);
        buttonAdd = view.findViewById(R.id.buttonAdd);
        listViewTasks = view.findViewById(R.id.listViewTasks);

        tasksList = new ArrayList<>();
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, tasksList);
        listViewTasks.setAdapter(adapter);

        buttonAdd.setOnClickListener(v -> {
            String task = editTextTask.getText().toString();
            if (!task.isEmpty()) {
                tasksList.add(task);
                adapter.notifyDataSetChanged();
                editTextTask.setText("");
            }
        });

        listViewTasks.setOnItemClickListener((parent, view1, position, id) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Eliminar tarea")
                    .setMessage("¿Deseas eliminar esta tarea?")
                    .setPositiveButton("Sí", (dialog, which) -> {
                        tasksList.remove(position);
                        adapter.notifyDataSetChanged();
                    })
                    .setNegativeButton("No", null)
                    .show();
        });

        return view;
    }
}

