package edu.northeastern.cs5520.todo_adrienne.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.time.LocalDateTime;
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

    @Override
    public LiveData<ToDo> getToDoById(int id) {
        return null; // TODO(ahs): Actually return a ToDo with ID
    }

    @Override
    public int update(ToDo todo) {
        // TODO(ahs): Actually update a ToDo
        return 0;
    }

    @Override
    public LiveData<List<ToDo>> getTodosDueInRange(LocalDateTime start, LocalDateTime end) {
        return null;
    }

    @Override
    public List<ToDo> getTodosToBeReminded(LocalDateTime start, LocalDateTime end) {
        return null;
    }

    @Override
    public LiveData<List<ToDo>> getTodosToBeRemindedEventually(LocalDateTime start, LocalDateTime end) {
        return null;
    }


}
