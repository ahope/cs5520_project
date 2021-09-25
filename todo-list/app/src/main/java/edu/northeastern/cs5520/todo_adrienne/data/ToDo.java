package edu.northeastern.cs5520.todo_adrienne.data;

import java.time.LocalDateTime;
import java.util.Set;

public class ToDo {

    private String title;

    private String description;

    private Set tags;

    private LocalDateTime deadline;

    private boolean remindMe;

    private LocalDateTime reminderDateTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set getTags() {
        return tags;
    }

    public void setTags(Set tags) {
        this.tags = tags;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public boolean isRemindMe() {
        return remindMe;
    }

    public void setRemindMe(boolean remindMe) {
        this.remindMe = remindMe;
    }

    public LocalDateTime getReminderDateTime() {
        return reminderDateTime;
    }

    public void setReminderDateTime(LocalDateTime reminderDateTime) {
        this.reminderDateTime = reminderDateTime;
    }

    public static ToDo createTodo(String title, String detail) {
        ToDo todo = new ToDo();
        todo.setTitle(title);
        todo.setDescription(detail);
        return todo;
    }
}
