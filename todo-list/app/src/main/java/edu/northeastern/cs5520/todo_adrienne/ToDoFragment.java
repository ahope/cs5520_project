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
        binding = FragmentToDoBinding.inflate(inflater, container, false);
        mViewModel = new ViewModelProvider(requireActivity()).get(ToDoViewModel.class);
        binding.setViewmodel(mViewModel);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        binding.buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.createTodo();
            }
        });

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