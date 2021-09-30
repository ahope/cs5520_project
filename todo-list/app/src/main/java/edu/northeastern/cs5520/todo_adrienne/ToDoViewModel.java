package edu.northeastern.cs5520.todo_adrienne;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import edu.northeastern.cs5520.todo_adrienne.data.ToDo;
import edu.northeastern.cs5520.todo_adrienne.data.ToDoItemRepository;

public class ToDoViewModel extends ViewModel {


    public MutableLiveData<String> todoTitle = new MutableLiveData<>();
    public MutableLiveData<String> todoDescription = new MutableLiveData<>();

    private MutableLiveData<Boolean> todoCreated = new MutableLiveData<>();

    // TODO(ahs): Review/include the SavedStateHandle stuff
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
        todoCreated.setValue(Boolean.FALSE);
    }

    public LiveData<Boolean> getTodoCreated() {
        return todoCreated;
    }

    public void createTodo() {
        ToDoItemRepository.addToDo(ToDo.createTodo(todoTitle.getValue(), todoDescription.getValue()));
        todoCreated.setValue(Boolean.TRUE);
    }

}