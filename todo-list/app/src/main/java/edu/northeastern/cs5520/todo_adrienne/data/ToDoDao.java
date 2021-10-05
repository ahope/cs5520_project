package edu.northeastern.cs5520.todo_adrienne.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ToDoDao {

    @Insert(onConflict= OnConflictStrategy.IGNORE)
    void insert(ToDo todo);

    @Query("DELETE FROM todo_table")
    void deleteAll();

    @Query("SELECT * FROM todo_table")
    LiveData<List<ToDo>> getTodos();
}
