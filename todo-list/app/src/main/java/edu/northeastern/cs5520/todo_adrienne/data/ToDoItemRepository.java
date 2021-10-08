package edu.northeastern.cs5520.todo_adrienne.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class ToDoItemRepository implements Iterable<ToDo>{

    private IToDoDataSource mToDoDataSource;

    private static ToDoItemRepository singleton;

    private ToDoItemRepository(Application application) {
//        mToDoDataSource = new ToDoInMemoryDataSource();
        mToDoDataSource = new ToDoDbDataSource(application);
        this.createFakeData();
    }

    public static ToDoItemRepository getSingleton(Application application) {
        if (singleton == null) {
            singleton = new ToDoItemRepository(application);
        }
        return singleton;
    }

    public List<ToDo> asList() {
        return mToDoDataSource.getTodos().getValue();
    }

    public LiveData<List<ToDo>> getAllTodos() {
        return mToDoDataSource.getTodos();
    }

    public LiveData<List<ToDo>> getNToDos(int n) {
        return mToDoDataSource.getNTodos(n);
    }

    public void addToDo(ToDo newToDo) {
        mToDoDataSource.insert(newToDo);
    }

    private void createFakeData() {
        addToDo(ToDo.createTodo("Task todo 1", "do something, already"));
        addToDo(ToDo.createTodo("Task todo 2", "and another thign!"));

    }

    @NonNull
    @Override
    public Iterator<ToDo> iterator() {
        return mToDoDataSource.getTodos().getValue().iterator();
    }

    @Override
    public void forEach(@NonNull Consumer<? super ToDo> action) {
        mToDoDataSource.getTodos().getValue().forEach(action);
    }

    @NonNull
    @Override
    public Spliterator<ToDo> spliterator() {
        return mToDoDataSource.getTodos().getValue().spliterator();
    }
}
