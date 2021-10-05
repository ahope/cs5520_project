package edu.northeastern.cs5520.todo_adrienne.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {ToDo.class}, version = 1, exportSchema = false)
public abstract class ToDoRoomDatabase extends RoomDatabase {

    public abstract ToDoDao ToDoDao();

    private static volatile ToDoRoomDatabase  INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ToDoRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ToDoRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ToDoRoomDatabase.class, "todo_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
