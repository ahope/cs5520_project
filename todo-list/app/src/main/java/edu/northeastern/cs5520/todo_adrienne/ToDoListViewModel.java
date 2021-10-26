package edu.northeastern.cs5520.todo_adrienne;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import edu.northeastern.cs5520.todo_adrienne.data.ToDo;
import edu.northeastern.cs5520.todo_adrienne.data.ToDoItemRepository;

public class ToDoListViewModel extends AndroidViewModel {

    private ToDoItemRepository repository;

    private final LiveData<List<ToDo>> mAllToDos;


    // TODO(ahs): Review/include the SavedStateHandle stuff
    public ToDoListViewModel(Application application) {
        super(application);
        repository = ToDoItemRepository.getSingleton(application);

        // Using only *n* todos
        mAllToDos = repository.getAllTodos();
//        mAllToDos = repository.getNToDos(5);
    }

    public LiveData<List<ToDo>> getAllToDos() {
        return mAllToDos;
    }
}