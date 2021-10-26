package edu.northeastern.cs5520.todo_adrienne.reminders;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class DoReminderNotificationReceiver extends BroadcastReceiver {

    public static String EXTRA_KEY_TODO_ID = "todo_id";

    @Override
    public void onReceive(Context context, Intent intent) {
        int todoId = intent.getIntExtra(EXTRA_KEY_TODO_ID, -1);
        if (todoId != -1) {
            NotificationManager.sendNotification(context, todoId);
        }
    }
}