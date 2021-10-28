package edu.northeastern.cs5520.todo_adrienne.data;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import edu.northeastern.cs5520.todo_adrienne.databinding.ToDoItemViewBinding;

/**
 * This class holds the data collection, and allows the data to be mapped to the ViewHolder.
 * In this case, it "knows" the ToDoRepo (or, some collection of ToDo objects), and when is appropriate,
 * maps a specific ToDo object to a ViewHolder to display that ToDo instance.
 */
public class ToDoItemRecyclerViewAdapter extends ListAdapter<ToDo, ToDoItemViewHolder> {
    public ToDoItemRecyclerViewAdapter(@NonNull DiffUtil.ItemCallback<ToDo> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ToDoItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ToDoItemViewHolder holder =  new ToDoItemViewHolder(ToDoItemViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                                    parent,
                        false));

        holder.binding.isCompletedCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox check = (CheckBox)view;
                ToDo todo = getItem(holder.getLayoutPosition());
                todo.setCompleted(check.isChecked());
                ToDoItemRepository.getSingleton(view.getContext()).update(todo);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoItemViewHolder holder, int position) {
        // This is how we bind the UI to a specific task
        holder.bind(getItem(position), position);

    }

    public void sortByDeadlineAscending() {
        List<ToDo> items = new ArrayList<ToDo>();
        items.addAll(getCurrentList());
        Collections.sort(items, new Comparator<ToDo>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public int compare(ToDo t1, ToDo t2) {
                // -1: less than; 1: greater than; 0: equal
                return t1.getDeadline().compareTo(t2.getDeadline());
            }
        });
        submitList(items);
    }

    public void sortByDeadlineDescending() {
        List<ToDo> items = new ArrayList<ToDo>();
        items.addAll(getCurrentList());
        Collections.sort(items, new Comparator<ToDo>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public int compare(ToDo t1, ToDo t2) {
                // -1: less than; 1: greater than; 0: equal
                return t2.getDeadline().compareTo(t1.getDeadline());
            }
        });
        submitList(items);
    }




    public static class TodoDiff extends DiffUtil.ItemCallback<ToDo> {

        @Override
        public boolean areItemsTheSame(@NonNull ToDo oldItem, @NonNull ToDo newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ToDo oldItem, @NonNull ToDo newItem) {
            return oldItem.getTitle().equals(newItem.getTitle());
        }
    }

}
