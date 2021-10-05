package edu.northeastern.cs5520.todo_adrienne.data;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface IToDoDataSource {

    void insert(ToDo todo);

    void deleteAll();

    LiveData<List<ToDo>> getTodos();
}
