package edu.northeastern.cs5520.todo_adrienne.data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import edu.northeastern.cs5520.todo_adrienne.R;
import edu.northeastern.cs5520.todo_adrienne.databinding.ActivityMainListViewBinding;
import edu.northeastern.cs5520.todo_adrienne.databinding.FragmentToDoListV2Binding;
import edu.northeastern.cs5520.todo_adrienne.databinding.ToDoItemViewBinding;
import edu.northeastern.cs5520.todo_adrienne.v2frags.MyToDoRecyclerViewAdapter;

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

//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.to_do_item_view, parent, false);
//
//        return new ToDoItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoItemViewHolder holder, int position) {
        // This is how we bind the UI to a specific task
//        holder.binding.setTodoTitle("Test");
//        holder.binding.setTodoDetail("test details");
//        holder.binding.titleTextView.setText("Adrienne");
        holder.bind(ToDoItemRepository.getAllTodos().asList().get(position));
//        holder.titleTextView.setText("Slaughter");
    }

    @Override
    public int getItemCount() {
//        return 2;
        return ToDoItemRepository.getAllTodos().asList().size();
    }
}
