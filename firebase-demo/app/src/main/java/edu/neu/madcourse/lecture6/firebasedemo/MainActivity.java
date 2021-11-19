package edu.neu.madcourse.lecture6.firebasedemo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.messaging.FirebaseMessaging;

import edu.neu.madcourse.lecture6.firebasedemo.fcm.FCMActivity;
import edu.neu.madcourse.lecture6.firebasedemo.realtimedatabase.RealtimeDatabaseActivity;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";

    public static final String CHANNEL_NAME = "cs5520channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createNotificationChannel();

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

    public void openFCMActivity(View view) {
        startActivity(new Intent(MainActivity.this, FCMActivity.class));
    }

    public void openDBActivity(View view) {
        startActivity(new Intent(MainActivity.this, RealtimeDatabaseActivity.class));
    }

    public void openSendNotificationActivity(View view){
        startActivity(new Intent(MainActivity.this, SendNotificationActivity.class));
    }

    public void openPermissionsActivity(View view){
        startActivity(new Intent(MainActivity.this, ShowPermActivity.class));
    }


}
