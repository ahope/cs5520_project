package edu.northeastern.cs5520.todo_adrienne;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.northeastern.cs5520.todo_adrienne.databinding.FragmentToDoBinding;


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
        // Wire up the button to ensure the task gets created.
        binding.buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewModel.createTodo();
            }
        });

    }


}