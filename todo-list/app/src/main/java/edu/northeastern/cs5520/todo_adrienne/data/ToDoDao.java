package edu.northeastern.cs5520.todo_adrienne.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ToDoDao {

    @Insert(onConflict= OnConflictStrategy.IGNORE)
    void insert(ToDo todo);

    @Query("DELETE FROM todo_table")
    void deleteAll();

    @Query("SELECT * FROM todo_table")
    LiveData<List<ToDo>> getTodos();

    // Selects n elements from the table;
    // Does not specify order (e.g. most recent n items).
    @Query("SELECT * FROM todo_table LIMIT :n")
    LiveData<List<ToDo>> getNTodos(int n);

    @Query("SELECT * FROM todo_table WHERE id = :id")
    LiveData<ToDo> getToDoById(int id);

    @Update
    int update(ToDo todo);

}
