package edu.northeastern.cs5520.todo_adrienne;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import edu.northeastern.cs5520.todo_adrienne.data.ToDo;
import edu.northeastern.cs5520.todo_adrienne.data.ToDoItemRepository;
import edu.northeastern.cs5520.todo_adrienne.databinding.ActivityMainBinding;
import edu.northeastern.cs5520.todo_adrienne.databinding.ActivityNewToDoBinding;

public class NewToDoActivity extends AppCompatActivity {

    public static String EXTRA_KEY_TODO_ID = "todo_id";

    private ToDoViewModel toDoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityNewToDoBinding binding = ActivityNewToDoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Get an instance to the shared ViewModel
        toDoViewModel = new ViewModelProvider(this).get(ToDoViewModel.class);
        int cur_id = getIntent().getIntExtra(EXTRA_KEY_TODO_ID, -1);
        toDoViewModel.loadToDo(cur_id);

        // Observe a flag we use to say the new ToDo has been created
        // This is a bit of a hack; there's a slightly better way to do this (observe an
        //   event rather than a Boolean), but this is okay for now. 
        toDoViewModel.getTodoCreated().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean todoCreated) {
                if (todoCreated) {
//                    setResult();
                    finish();
                }
            }
        });
    }


}