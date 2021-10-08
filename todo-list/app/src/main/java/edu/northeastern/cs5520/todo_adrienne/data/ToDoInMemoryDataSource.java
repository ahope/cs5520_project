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

    @Override
    public LiveData<List<ToDo>> getNTodos(int n) {
        int numItems = mToDoItems.getValue().size();
        List<ToDo> sublist = mToDoItems.getValue().subList(0, Math.min(n, numItems));
        LiveData<List<ToDo>> liveSublist = new MutableLiveData<>(sublist);
        return liveSublist;
    }
}
