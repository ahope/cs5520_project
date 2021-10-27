package edu.northeastern.cs5520.todo_adrienne.reminders;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class DoReminderNotificationReceiver extends BroadcastReceiver {

    public static String EXTRA_KEY_TODO_ID = "todo_id";
    public static String EXTRA_KEY_TODO_TITLE = "todo_title";

    private static String TAG = DoReminderNotificationReceiver.class.toString();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, " Receiving to do a notification!!");
        int todoId = intent.getIntExtra(EXTRA_KEY_TODO_ID, -1);
        String todoTitle = intent.getStringExtra(EXTRA_KEY_TODO_TITLE);
        if (todoId != -1) {
            NotificationManager.sendNotification(context, todoId, todoTitle);
        }
    }
}