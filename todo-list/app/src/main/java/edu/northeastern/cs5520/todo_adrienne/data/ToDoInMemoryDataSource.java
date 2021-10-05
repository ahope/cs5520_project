package edu.northeastern.cs5520.todo_adrienne.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class ToDoInMemoryDataSource implements IToDoDataSource{

    private MutableLiveData<List<ToDo>> mToDoItems = new MutableLiveData<>();

    public ToDoInMemoryDataSource() {
        mToDoItems.setValue(new ArrayList<ToDo>());
    }

    @Override
    public void insert(ToDo todo) {
        mToDoItems.getValue().add(todo);
    }

    @Override
    public void deleteAll() {
        throw new RuntimeException("Haven't implemented this yet. ");
    }

    @Override
    public LiveData<List<ToDo>> getTodos() {
        return mToDoItems;
    }
}
