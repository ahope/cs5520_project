package edu.northeastern.cs5520.todo_adrienne.data;

import androidx.lifecycle.LiveData;

import java.util.List;

import edu.northeastern.cs5520.todo_adrienne.ToDoApplication;

public class ToDoDbDataSource implements IToDoDataSource{

    private ToDoDao mToDoDao;

    public ToDoDbDataSource() {
        ToDoRoomDatabase db = ToDoRoomDatabase.getDatabase(ToDoApplication.getInstance());
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
        return null;
    }




}
