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
        adapter = new ArrayAdapter<>(getContext(), R.layout.itemlv, R.id.textViewTask, tasksList);
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
            CharSequence[] items = {"Editar", "Eliminar"};

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Elige una acción");
            builder.setItems(items, (dialog, which) -> {
                if (which == 0) { // Editar
                    AlertDialog.Builder editDialog = new AlertDialog.Builder(getContext());
                    editDialog.setTitle("Editar tarea");

                    final EditText input = new EditText(getContext());
                    input.setText(tasksList.get(position));
                    editDialog.setView(input);

                    editDialog.setPositiveButton("Guardar", (d, w) -> {
                        tasksList.set(position, input.getText().toString());
                        adapter.notifyDataSetChanged(); // Usar esto en lugar de notifyItemChanged
                    });
                    editDialog.setNegativeButton("Cancelar", null);

                    editDialog.show();
                } else {
                    AlertDialog.Builder deleteDialog = new AlertDialog.Builder(getContext());
                    deleteDialog.setTitle("Eliminar tarea")
                            .setMessage("¿Deseas eliminar esta tarea?")
                            .setPositiveButton("Sí", (deleteD, item) -> {
                                tasksList.remove(position);
                                adapter.notifyDataSetChanged();
                            })
                            .setNegativeButton("No", null)
                            .show();
                }
            });

            builder.show();
        });


        return view;
    }
}

