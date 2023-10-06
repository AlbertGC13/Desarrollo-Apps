package com.example.tarea4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTaskActivity extends AppCompatActivity {

    private EditText editTextTaskName;
    private Button addTaskButton;
    private TaskViewModel taskViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        editTextTaskName = findViewById(R.id.editTextTaskName);
        addTaskButton = findViewById(R.id.addTaskButton);

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        addTaskButton.setOnClickListener(v -> {
            String taskName = editTextTaskName.getText().toString().trim();
            if (!taskName.isEmpty()) {
                Task newTask = new Task(taskName, false);
                taskViewModel.insert(newTask); // Insertar la tarea en la base de datos

                finish(); // Finalizar esta actividad y volver a MainActivity
            } else {
                Toast.makeText(this, "El nombre de la tarea no puede estar vacío", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
