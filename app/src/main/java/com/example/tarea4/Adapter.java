package com.example.tarea4;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private TaskViewModel taskViewModel;
    private List<Task> tasksList;
    private Context context;

    public Adapter(List<Task> tasksList, Context context, TaskViewModel taskViewModel) {
        this.tasksList = tasksList;
        this.context = context;
        this.taskViewModel = taskViewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = tasksList.get(position);
        holder.textView.setText(task.getName());
        holder.cbTaskCompleted.setChecked(task.isCompleted());

        if (task.isCompleted()) {
            holder.textView.setPaintFlags(holder.textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.textView.setPaintFlags(holder.textView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }

        holder.cbTaskCompleted.setOnClickListener(v -> {
            boolean isChecked = holder.cbTaskCompleted.isChecked();
            task.setCompleted(isChecked);
            if(taskViewModel != null) {
                taskViewModel.update(task);
            } else {
                Log.e("Adapter", "TaskViewModel is null");
            }
            if (isChecked) {
                holder.textView.setPaintFlags(holder.textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                holder.textView.setPaintFlags(holder.textView.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }
        });

        holder.itemView.setOnClickListener(v -> {
            CharSequence[] items = {"Editar", "Eliminar"};

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Elige una acción");
            builder.setItems(items, (dialog, which) -> {
                if (which == 0) { // Editar
                    AlertDialog.Builder editDialog = new AlertDialog.Builder(context);
                    editDialog.setTitle("Editar tarea");

                    final EditText input = new EditText(context);
                    input.setText(task.getName());
                    editDialog.setView(input);

                    editDialog.setPositiveButton("Guardar", (d, w) -> {
                        String updatedTaskName = input.getText().toString();
                        task.setName(updatedTaskName);
                        new Thread(() -> {
                            AppDatabase.getDatabase(context).taskDao().update(task);
                            ((AppCompatActivity) context).runOnUiThread(() -> {
                                notifyItemChanged(position);
                            });
                        }).start();
                    });
                    editDialog.setNegativeButton("Cancelar", null);

                    editDialog.show();
                } else { // Eliminar
                    AlertDialog.Builder deleteDialog = new AlertDialog.Builder(context);
                    deleteDialog.setTitle("Eliminar tarea")
                            .setMessage("¿Deseas eliminar esta tarea?")
                            .setPositiveButton("Sí", (deleteDialogInterface, item) -> {
                                tasksList.remove(position);
                                new Thread(() -> {
                                    AppDatabase.getDatabase(context).taskDao().delete(task);
                                    ((AppCompatActivity) context).runOnUiThread(() -> {
                                        notifyItemRemoved(position);
                                    });
                                }).start();
                            })
                            .setNegativeButton("No", null)
                            .show();
                }
            });
            builder.show();
        });


    }

    @Override
    public int getItemCount() {
        return tasksList.size();
    }

    public void setTasks(List<Task> tasks) {
        this.tasksList = tasks;
        notifyDataSetChanged(); // Notifica al Adapter que los datos han cambiado
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        CheckBox cbTaskCompleted;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewTask);
            imageView = itemView.findViewById(R.id.imageViewTask);
            cbTaskCompleted = itemView.findViewById(R.id.cbTaskCompleted);
        }
    }


}
