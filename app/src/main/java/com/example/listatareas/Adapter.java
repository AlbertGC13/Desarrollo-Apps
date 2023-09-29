package com.example.listatareas;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<String> tasksList;
    private Context context;

    public Adapter(List<String> tasksList, Context context) {
        this.tasksList = tasksList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String task = tasksList.get(position);
        holder.textView.setText(task);

        holder.itemView.setOnClickListener(v -> {
            CharSequence[] items = {"Editar", "Eliminar"};

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Elige una acción");
            builder.setItems(items, (dialog, which) -> {
                if (which == 0) { // Editar
                    AlertDialog.Builder editDialog = new AlertDialog.Builder(context);
                    editDialog.setTitle("Editar tarea");

                    final EditText input = new EditText(context);
                    input.setText(tasksList.get(position));
                    editDialog.setView(input);

                    editDialog.setPositiveButton("Guardar", (d, w) -> {
                        tasksList.set(position, input.getText().toString());
                        notifyItemChanged(position);
                    });
                    editDialog.setNegativeButton("Cancelar", null);

                    editDialog.show();
                } else {
                    AlertDialog.Builder editDialog = new AlertDialog.Builder(context);
                    editDialog.setTitle("Eliminar tarea")
                            .setMessage("¿Deseas eliminar esta tarea?")
                            .setPositiveButton("Sí", (deleteDialog, item) -> {
                                tasksList.remove(position);
                                notifyItemRemoved(position);
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textViewTask);
            imageView = itemView.findViewById(R.id.imageViewTask);
        }
    }
}

