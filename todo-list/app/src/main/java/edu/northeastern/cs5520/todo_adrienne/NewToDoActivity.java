package edu.northeastern.cs5520.todo_adrienne;

import androidx.appcompat.app.AppCompatActivity;
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
    }

    public void createTodo(View view) {
        ToDoItemRepository.addToDo(ToDo.createTodo(toDoViewModel.todoTitle.getValue(),
                                        toDoViewModel.todoDescription.getValue()));
        this.finish();
    }
}