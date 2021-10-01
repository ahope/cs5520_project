package edu.northeastern.cs5520.todo_adrienne.data;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.northeastern.cs5520.todo_adrienne.R;
import edu.northeastern.cs5520.todo_adrienne.databinding.ToDoItemViewBinding;

public class ToDoItemViewHolder extends RecyclerView.ViewHolder {

    public ToDoItemViewBinding binding;
    public TextView titleTextView;

    public ToDoItemViewHolder(ToDoItemViewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }


    public void bind(ToDo toDo) {
        binding.setTodoDetail(toDo.getDescription());
        binding.setTodoTitle(toDo.getTitle());
        binding.setTodoTask(toDo);
        binding.executePendingBindings();
    }


}
