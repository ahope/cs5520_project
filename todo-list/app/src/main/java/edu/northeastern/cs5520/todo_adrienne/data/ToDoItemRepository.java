package edu.northeastern.cs5520.todo_adrienne.data;

import java.util.HashSet;
import java.util.Set;

public class ToDoItemRepository {

    private static Set<ToDo> todoSet;

    private ToDoItemRepository() {
        todoSet = new HashSet<ToDo>();
    }

    public static Set<ToDo> getAllTodos() {
        if (todoSet == null) {
            todoSet = new HashSet<ToDo>();
        }
        return todoSet;
    }
}
