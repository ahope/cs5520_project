package edu.northeastern.cs5520.todo_adrienne;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import edu.northeastern.cs5520.todo_adrienne.data.ToDo;
import edu.northeastern.cs5520.todo_adrienne.data.ToDoItemRepository;

public class NewToDoActivity extends AppCompatActivity {

    private ToDoViewModel toDoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_to_do);
        toDoViewModel = new ViewModelProvider(this).get(ToDoViewModel.class);

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