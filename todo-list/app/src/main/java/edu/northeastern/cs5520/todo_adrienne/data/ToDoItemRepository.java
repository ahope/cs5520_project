package edu.northeastern.cs5520.todo_adrienne.data;

import androidx.annotation.NonNull;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;

public class ToDoItemRepository implements Iterable<ToDo>{

    private static Set<ToDo> todoSet;

    private static ToDoItemRepository singleton;

    private ToDoItemRepository() {
        todoSet = new HashSet<ToDo>();
        this.createFakeData();
    }

    public static ToDoItemRepository getAllTodos() {
        if (singleton == null) {
            singleton = new ToDoItemRepository();
        }
        return singleton;
    }

    public static void addToDo(ToDo newToDo) {
        getAllTodos().todoSet.add(newToDo);
    }

    private void createFakeData() {
        todoSet.add(ToDo.createTodo("Task todo 1", "do something, already"));
        todoSet.add(ToDo.createTodo("Task todo 2", "and another thign!"));

    }

    @NonNull
    @Override
    public Iterator<ToDo> iterator() {
        return todoSet.iterator();
    }

    @Override
    public void forEach(@NonNull Consumer<? super ToDo> action) {
        todoSet.forEach(action);
    }

    @NonNull
    @Override
    public Spliterator<ToDo> spliterator() {
        return todoSet.spliterator();
    }
}
