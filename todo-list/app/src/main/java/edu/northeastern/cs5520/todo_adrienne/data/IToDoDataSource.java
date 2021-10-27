package edu.northeastern.cs5520.todo_adrienne.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.time.LocalDateTime;
import java.util.List;

public interface IToDoDataSource {

    void insert(ToDo todo);

    void deleteAll();

    LiveData<List<ToDo>> getTodos();

    LiveData<List<ToDo>> getNTodos(int n);

    LiveData<ToDo> getToDoById(int id);

    int update(ToDo todo);

    LiveData<List<ToDo>> getTodosDueInRange(LocalDateTime start, LocalDateTime end);

    List<ToDo> getTodosToBeReminded(LocalDateTime start, LocalDateTime end);
    LiveData<List<ToDo>> getTodosToBeRemindedEventually(LocalDateTime start, LocalDateTime end);
}
