package edu.northeastern.cs5520.todo_adrienne.data;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.time.LocalDateTime;
import java.util.List;

import edu.northeastern.cs5520.todo_adrienne.ToDoApplication;

public class ToDoDbDataSource implements IToDoDataSource{

    private ToDoDao mToDoDao;

    public ToDoDbDataSource(Context context) {
        ToDoRoomDatabase db = ToDoRoomDatabase.getDatabase(context);
        mToDoDao = db.ToDoDao();
    }

    @Override
    public void insert(ToDo todo) {
        ToDoRoomDatabase.databaseWriteExecutor.execute(() -> {
            mToDoDao.insert(todo);
        });
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public LiveData<List<ToDo>> getTodos() {
        return mToDoDao.getTodos();
    }

    @Override
    public LiveData<List<ToDo>> getNTodos(int n) {
        return mToDoDao.getNTodos(n);
    }

    @Override
    public LiveData<ToDo> getToDoById(int id) {
        return mToDoDao.getToDoById(id);
    }

    @Override
    public int update(ToDo todo){
//        return mToDoDao.update(todo);

        ToDoRoomDatabase.databaseWriteExecutor.execute(() -> {
            mToDoDao.update(todo);
        });

        return 0;
    }

    @Override
    public LiveData<List<ToDo>> getTodosDueInRange(LocalDateTime start, LocalDateTime end) {
        return  mToDoDao.getTodosDueInRange(start, end);
    }

    @Override
    public List<ToDo> getTodosToBeReminded(LocalDateTime start, LocalDateTime end) {
        return mToDoDao.getTodosToBeReminded(start, end);
    }

    @Override
    public LiveData<List<ToDo>> getTodosToBeRemindedEventually(LocalDateTime start, LocalDateTime end) {
        return mToDoDao.getTodosToBeRemindedEventually(start, end);
    }
}
