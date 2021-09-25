package edu.northeastern.cs5520.todo_adrienne;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import edu.northeastern.cs5520.todo_adrienne.data.ToDo;
import edu.northeastern.cs5520.todo_adrienne.databinding.FragmentFirstBinding;
import edu.northeastern.cs5520.todo_adrienne.databinding.ToDoItemViewBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private ToDoItemViewBinding todoViewBinding1;
    private ToDoItemViewBinding todoViewBinding2;

    private ToDo todo1 = ToDo.createTodo("Task todo 1", "do something, already");
    private ToDo todo2 = ToDo.createTodo("Task todo 2", "and another thign!");

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        todoViewBinding1 = ToDoItemViewBinding.inflate(inflater, binding.todoItemsLayout, true);
        todoViewBinding2 = ToDoItemViewBinding.inflate(inflater, binding.todoItemsLayout, true);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        todoViewBinding1.setTodoTask(todo1);
        todoViewBinding2.setTodoTask(todo2);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}