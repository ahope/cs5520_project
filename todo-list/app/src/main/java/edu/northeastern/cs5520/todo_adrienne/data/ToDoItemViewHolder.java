package edu.northeastern.cs5520.todo_adrienne.data;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.northeastern.cs5520.todo_adrienne.MainActivity_ListView;
import edu.northeastern.cs5520.todo_adrienne.NewToDoActivity;
import edu.northeastern.cs5520.todo_adrienne.R;
import edu.northeastern.cs5520.todo_adrienne.databinding.ToDoItemViewBinding;

public class ToDoItemViewHolder extends RecyclerView.ViewHolder {

    public ToDoItemViewBinding binding;
    public TextView titleTextView;
    private int mToDoId = -1;
    private int mPosition = -1;

    public ToDoItemViewHolder(ToDoItemViewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;

        this.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(binding.getRoot().getContext(), NewToDoActivity.class);
                intent.putExtra(NewToDoActivity.EXTRA_KEY_TODO_ID, mToDoId);
                binding.getRoot().getContext().startActivity(intent);
            }
        });

    }

    public void bind(ToDo toDo, int position) {
        mToDoId = toDo.getId();
        mPosition = position;
        binding.setTodoDetail(toDo.getDescription());
        binding.setTodoTitle(toDo.getTitle());
        binding.setTodoTask(toDo);
        binding.executePendingBindings();
    }


}
