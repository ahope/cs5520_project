package edu.northeastern.cs5520.todo_adrienne.data;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.northeastern.cs5520.todo_adrienne.databinding.ToDoItemViewBinding;

/**
 * This class holds the data collection, and allows the data to be mapped to the ViewHolder.
 * In this case, it "knows" the ToDoRepo (or, some collection of ToDo objects), and when is appropriate,
 * maps a specific ToDo object to a ViewHolder to display that ToDo instance.
 */
public class ToDoItemRecyclerViewAdapter extends RecyclerView.Adapter<ToDoItemViewHolder> {
    @NonNull
    @Override
    public ToDoItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ToDoItemViewHolder(ToDoItemViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                                    parent,
                        false));
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoItemViewHolder holder, int position) {
        // This is how we bind the UI to a specific task
        holder.bind(ToDoItemRepository.getSingleton().getAllTodos().getValue().get(position));
    }

    @Override
    public int getItemCount() {
        return ToDoItemRepository.getSingleton().getAllTodos().getValue().size();
    }
}
