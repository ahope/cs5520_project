package edu.northeastern.cs5520.todo_adrienne.reminders;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.time.LocalDateTime;
import java.util.List;

import edu.northeastern.cs5520.todo_adrienne.data.ToDo;
import edu.northeastern.cs5520.todo_adrienne.data.ToDoItemRepository;

public class ScheduleReminderNotificationsReceiver extends BroadcastReceiver {

    private static final String TAG = ScheduleReminderNotificationsReceiver.class.toString();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "Running ScheduleReminderNotificationReceiver");
        // Get all the ToDos to be done in the next 3 hours
        ToDoItemRepository repository = ToDoItemRepository.getSingleton(context);
        List<ToDo> todos = repository.getTodosToBeReminded(LocalDateTime.now(), LocalDateTime.now().plusHours(3));

        // Set up a reminder for each






        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}