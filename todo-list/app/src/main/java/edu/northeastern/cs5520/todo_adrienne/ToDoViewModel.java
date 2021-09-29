package edu.northeastern.cs5520.todo_adrienne;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import edu.northeastern.cs5520.todo_adrienne.data.ToDo;
import edu.northeastern.cs5520.todo_adrienne.data.ToDoItemRepository;

public class ToDoViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    ToDo toDoTask;

    MutableLiveData<String> todoTitle;

    MutableLiveData<String> todoDescription;

    public ToDoViewModel(SavedStateHandle savedStateHandle) {
        todoTitle = savedStateHandle.get("title");
        if (todoTitle == null) {
            todoTitle = new MutableLiveData<>();
            todoTitle.setValue("");
        }
        todoDescription = savedStateHandle.get("description");
        if (todoDescription == null) {
            todoDescription = new MutableLiveData<>();
            todoDescription.setValue("");
        }
    }
}