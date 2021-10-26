package edu.northeastern.cs5520.todo_adrienne.reminders;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.View;

import edu.northeastern.cs5520.todo_adrienne.NewToDoActivity;
import edu.northeastern.cs5520.todo_adrienne.R;

/***
 * Responsible for creating notifications.
 */
public class NotificationManager {

    private static final String CHANNEL_ID = "todoReminderChannel";

    private static String TAG = NotificationManager.class.toString();

    public static void sendNotification(Context context, int toDoId){
        // Prepare intent which is triggered if the
        // notification is selected
        Intent intent = new Intent(context, NewToDoActivity.class);
        intent.putExtra(NewToDoActivity.EXTRA_KEY_TODO_ID, toDoId);

        PendingIntent pIntent = PendingIntent.getActivity(
                context,
                (int) System.currentTimeMillis(), // TODO(ahs): Replace this with something meaningful
                intent,
                0);

        Notification.Builder notificationBuilder;
        if (Build.VERSION.SDK_INT <= 25) {
            notificationBuilder = new Notification.Builder(context)
                    .setSmallIcon(R.drawable.foo)
                    .setContentTitle("Adrienne's Simple Notification")
                    .setContentText("Subject:")
                    .setAutoCancel(true)
                    .setContentIntent(pIntent);
        } else {
            notificationBuilder = new Notification.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.foo)
                    .setContentTitle("Adrienne's Simple Notification")
                    .setContentText("Subject:")
                    .setAutoCancel(true)
                    .setContentIntent(pIntent);
        }

        android.app.NotificationManager notificationManager =
                (android.app.NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, // TODO(ahs): Replace this with something meaningful
                notificationBuilder.build());
    }


    public static void createNotificationChannel(Context context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = context.getString(R.string.channel_name);
            String description = context.getString(R.string.channel_description);
            int importance = android.app.NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            android.app.NotificationManager notificationManager = context.getSystemService(android.app.NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}
