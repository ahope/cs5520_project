package edu.neu.madcourse.lecture6.firebasedemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SendNotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_notification);
    }

    public void sendNotification(View view){
        // Prepare intent which is triggered if the
        // notification is selected
        Intent intent = new Intent(this, ReceiveNotificationActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        PendingIntent callIntent = PendingIntent.getActivity(this, (int)System.currentTimeMillis(),
                new Intent(this, FakeCallActivity.class), 0);

        Notification.Builder notificationBuilder;
        if (Build.VERSION.SDK_INT <= 25) {
            notificationBuilder = new Notification.Builder(this)
                    .setSmallIcon(R.drawable.foo)
                    .setContentTitle("Firebase Message (Mod6)")
                    .setContentText("Subject:")
                    .setAutoCancel(true)
                    .addAction(R.drawable.foo, "Call", callIntent)
                    .setContentIntent(pIntent);
        } else {
            notificationBuilder = new Notification.Builder(this, MainActivity.CHANNEL_NAME)
                    .setSmallIcon(R.drawable.foo)
                    .setContentTitle("Firebase Message (Mod6)")
                    .setContentText("Subject:")
                    .setAutoCancel(true)
                    .addAction(R.drawable.foo, "Call", callIntent)
                    .setContentIntent(pIntent);
        }

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
