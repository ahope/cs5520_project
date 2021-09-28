package edu.northeastern.cs5520.todo_adrienne;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.Snackbar;

import edu.northeastern.cs5520.todo_adrienne.data.ToDo;
import edu.northeastern.cs5520.todo_adrienne.data.ToDoItemRepository;
import edu.northeastern.cs5520.todo_adrienne.data.ToDoItemRepositoryChangeListener;
import edu.northeastern.cs5520.todo_adrienne.databinding.FragmentToDoListBinding;
import edu.northeastern.cs5520.todo_adrienne.databinding.ToDoItemViewBinding;

public class ToDoListFragment extends Fragment implements ToDoItemRepositoryChangeListener {

    private FragmentToDoListBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentToDoListBinding.inflate(inflater, container, false);

        for (ToDo todo : ToDoItemRepository.getAllTodos()) {
            ToDoItemViewBinding todoBinding = ToDoItemViewBinding.inflate(inflater, binding.todoItemsLayout, true);
            todoBinding.setTodoTask(todo);
            ((ConstraintLayout)(todoBinding.titleTextView.getParent())).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, todo.getTitle(), Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ToDoListFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void ToDoItemAdded(ToDo newToDoItem) {

    }
}