package edu.northeastern.cs5520.todo_adrienne;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import edu.northeastern.cs5520.todo_adrienne.data.ToDo;
import edu.northeastern.cs5520.todo_adrienne.data.ToDoItemRepository;

public class ToDoViewModel extends AndroidViewModel {
    public MutableLiveData<String> todoTitle = new MutableLiveData<>();
    public MutableLiveData<String> todoDescription = new MutableLiveData<>();

    private MutableLiveData<Boolean> todoCreated = new MutableLiveData<>();

    private ToDoItemRepository repository;

    private final LiveData<List<ToDo>> mAllToDos;

    // TODO(ahs): Review/include the SavedStateHandle stuff
    public ToDoViewModel(Application application) {
        super(application);
        repository = ToDoItemRepository.getSingleton(application);
//        todoTitle = savedStateHandle.get("title");
        if (todoTitle == null) {
            todoTitle = new MutableLiveData<>();
            todoTitle.setValue("");
        }
//        todoDescription = savedStateHandle.get("description");
        if (todoDescription == null) {
            todoDescription = new MutableLiveData<>();
            todoDescription.setValue("");
        }

        mAllToDos = repository.getAllTodos();

        todoCreated.setValue(Boolean.FALSE);
    }

    public LiveData<Boolean> getTodoCreated() {
        return todoCreated;
    }

    public void createTodo() {
        repository.addToDo(ToDo.createTodo(todoTitle.getValue(), todoDescription.getValue()));
        todoCreated.setValue(Boolean.TRUE);
    }

    public LiveData<List<ToDo>> getAllToDos() {
        return mAllToDos;
    }


}