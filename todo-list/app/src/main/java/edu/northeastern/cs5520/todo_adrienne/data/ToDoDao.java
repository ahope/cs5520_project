package edu.northeastern.cs5520.todo_adrienne.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

//   https://stackoverflow.com/questions/47199630/room-database-select-from-table-by-a-given-day
//    @Query("SELECT * FROM table_name WHERE date BETWEEN :dayst AND :dayet")
//    Object getFromTable(long dayst, long dayet);
    @Query("SELECT * FROM todo_table WHERE deadline BETWEEN :start AND :end")
    LiveData<List<ToDo>> getTodosDueInRange(LocalDateTime start, LocalDateTime end);


    @Query("SELECT * FROM todo_table WHERE reminderDateTime BETWEEN :start AND :end")
    LiveData<List<ToDo>> getTodosToBeReminded(LocalDateTime start, LocalDateTime end);

}
