package edu.northeastern.cs5520.todo_adrienne.data;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;


@RequiresApi(api = Build.VERSION_CODES.O)
@Entity(tableName="todo_table")
public class ToDo {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="id")
    private int id;

    @NonNull
    private String title;

    private String description;

//    private Set tags;

    // https://stackoverflow.com/questions/7363112/best-way-to-work-with-dates-in-android-sqlite
    @ColumnInfo(name = "deadline")
    @TypeConverters({TimestampConverter.class})
    private LocalDateTime deadline = LocalDateTime.now().plusDays(1);


    private boolean remindMe = false;

    private boolean isCompleted = false;

    private int importance = 0;

    @TypeConverters({TimestampConverter.class})
    private LocalDateTime reminderDateTime;

    public static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private static DateTimeFormatter timeFormat  = DateTimeFormatter.ofPattern("hh:mm a");


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

//    public Set getTags() {
//        return tags;
//    }
//
//    public void setTags(Set tags) {
//        this.tags = tags;
//    }

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



    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public String getDeadlineDayAsString() {
          if (this.deadline == null) return ""; // TODO(ahs): Get rid of this hack
//        return dateFormat.format(this.deadline);
        return dateFormat.format(this.deadline);
    }

    public String getDeadlineTimeAsString() {
        if (this.deadline == null) return ""; // TODO(ahs): Get rid of this hack
        return timeFormat.format(this.deadline);
    }


    public static ToDo createTodo(String title, String detail) {
        ToDo todo = new ToDo();
        todo.setTitle(title);
        todo.setDescription(detail);
        return todo;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }
}
