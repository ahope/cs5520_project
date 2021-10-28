package edu.neu.madcourse.slaughter.mod5proj;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SendNotificationActivity extends AppCompatActivity {

    private static final String CHANNEL_NAME = "Channel";
    private static String TAG = "SendNotificationActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_notification);
        createNotificationChannel();
    }

    public void sendNotification(View view){
        // Prepare intent which is triggered if the
        // notification is selected
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        Notification.Builder notificationBuilder;
        if (Build.VERSION.SDK_INT <= 25) {
            notificationBuilder = new Notification.Builder(this)
                    .setSmallIcon(R.drawable.foo)
                    .setContentTitle("Adrienne's Simple Notification")
                    .setContentText("Subject:")
                    .setAutoCancel(true)
                    .setContentIntent(pIntent);
        } else {
            notificationBuilder = new Notification.Builder(this, SendNotificationActivity.CHANNEL_NAME)
                    .setSmallIcon(R.drawable.foo)
                    .setContentTitle("Adrienne's Simple Notification")
                    .setContentText("Subject:")
                    .setAutoCancel(true)
                    .setContentIntent(pIntent);
        }

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }


    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_NAME, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}