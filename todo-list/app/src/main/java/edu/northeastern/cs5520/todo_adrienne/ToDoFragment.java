package edu.northeastern.cs5520.todo_adrienne;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.northeastern.cs5520.todo_adrienne.data.ToDo;
import edu.northeastern.cs5520.todo_adrienne.data.ToDoItemRepository;
import edu.northeastern.cs5520.todo_adrienne.databinding.FragmentToDoBinding;
import edu.northeastern.cs5520.todo_adrienne.databinding.FragmentToDoListBinding;

public class ToDoFragment extends Fragment {

    private ToDoViewModel mViewModel;
    private FragmentToDoBinding binding;

    public static ToDoFragment newInstance() {
        return new ToDoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_to_do, container, false);
        binding = FragmentToDoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(requireActivity()).get(ToDoViewModel.class);
        // Create the observer which updates the UI.
        final Observer<String> titleObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String newName) {
                // Update the UI, in this case, a TextView.
                binding.editTextTodoTitle.setText(newName);
            }
        };

        mViewModel.todoTitle.observe(getViewLifecycleOwner(), titleObserver);

        binding.editTextTodoTitle.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
//                mViewModel.todoTitle = binding.editTextTodoTitle.getText().toString();
                mViewModel.todoTitle.setValue(binding.editTextTodoTitle.getText().toString());
                return false;
            }
        });

        binding.editTextTodoDetail.setOnKeyListener(new View.OnKeyListener(){
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
//                mViewModel.todoTitle = binding.editTextTodoTitle.getText().toString();
                mViewModel.todoDescription.setValue(binding.editTextTodoDetail.getText().toString());
                return false;
            }
        });

//        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ToDoItemRepository.addToDo(ToDo.createTodo(binding.editTextTodoTitle.getText().toString(),
//                        binding.editTextTodoDetail.getText().toString()));
//
//            }
//        });

//        items.setOnClickListener(item -> {
//            // Set a new item
//            viewModel.select(item);
//        });
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(ToDoViewModel.class);
//        // TODO: Use the ViewModel
//
//    }


}