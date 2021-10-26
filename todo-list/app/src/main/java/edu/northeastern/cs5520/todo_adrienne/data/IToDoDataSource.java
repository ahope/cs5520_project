package edu.northeastern.cs5520.todo_adrienne.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public interface IToDoDataSource {

    void insert(ToDo todo);

    void deleteAll();

    LiveData<List<ToDo>> getTodos();

    LiveData<List<ToDo>> getNTodos(int n);

    LiveData<ToDo> getToDoById(int id);

    int update(ToDo todo);
}
