package edu.neu.madcourse.slaughter.mod5proj;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";

    private boolean mTimerGoing = true;

    private Timer mTimer;

    protected int startTime = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void mainStartTimer(View view){

        TextView timerTV = (TextView) findViewById(R.id.timerTextView);
//
        int time = 0;
//
////        runOnUiThread();
//
        // Approach 1: BAD: Counting on the UI Thread
        mTimerGoing = true;
//        while (mTimerGoing ){
        while (time < 15 ){
            time++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            timerTV.setText(Integer.toString(time));

            Log.d(TAG, "time: " + time);
        }
//
////        // Approach 2: Use ASyncTask
////        mTimer = new Timer();
////        mTimer.execute(startTime, 5);
////
////
//        new CountDownTimer(30000, 1000) {
//
//            public void onTick(long millisUntilFinished) {
//                Log.v(TAG, "seconds remaining: " + millisUntilFinished / 1000);
//
//                TextView timerTV = (TextView)findViewById(R.id.timerTextView);
//                timerTV.setText(millisUntilFinished + "ms");
////                timerTV.post(new Runnable() {
////                    public void run() {
////                        MyService.this.timerTV.setText(Integer.toString(15));
////                    }
////                });
////                // TODO: Make this post work; complaining of not compliling.
//
//            }
//
//            public void onFinish() {
//                Log.v(TAG, "done!");
//
//            }
//        }.start();
//

    }

    public void mainStopTimer(View view){

        mTimerGoing = false;
//        mTimer.cancel(true);

    }


    public void mainStartService(View view){
        Log.d(TAG, "Starting service from MainActivity");
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("KEY1", "Value to be used by the service");
        intent.addCategory(CAMERA_SERVICE);

        startService(intent);
    }

    public void mainStopService(View view){
        Log.d(TAG, "Stop service from MainActivity");
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("KEY1", "Value to be used by the service");

        stopService(intent);
    }

    public void mainLaunchIntent(View view){
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        //intent.setAction(Intent.CATEGORY_APP_CALENDAR);
        //intent.addCategory(Intent.CATEGORY_APP_EMAIL);
       // intent.setAction();
        //startActivity(intent);


        String uriText = "mailto:" + Uri.encode("email@gmail.com") +
                "?subject=" + Uri.encode("the subject") +
                "&body=" + Uri.encode("the body of the message");
        Uri uri = Uri.parse(uriText);

        intent.setData(uri);

        startActivity(intent);


/*
        Intent chooser = Intent.createChooser(intent, "Title here");

        // Verify the original intent will resolve to at least one activity
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(chooser);
        }
*/

    }




    private void doTheCounting() {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        Handler handler = HandlerCompat.createAsync(Looper.getMainLooper());
        executor.execute(new Runnable() {

            @Override
            public void run() {
                int i = 0;

                while (i < 15) {
                    i++;
                    // Can't do this!!!
//                     TextView tv = findViewById(R.id.textView);
//                     tv.setText(i);

                    final int x = i;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            TextView tv = findViewById(R.id.timerTextView);
                            tv.setText(Integer.toString(x));
//                             tv.setText(i);
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                boolean result = handler.post(new Runnable() {
                    @Override
                    public void run() {
                        TextView tv = findViewById(R.id.timerTextView);
                        tv.setText("The answer to life, the universe... ");
                    }
                });
                if (!result) {
                    /// do something to recover
                }
            }
        });
    }

    private class Timer extends AsyncTask<Integer, Integer, Integer> {

        @Override
        protected void onProgressUpdate(Integer... values) {
            TextView timerTV = (TextView)findViewById(R.id.timerTextView);
            timerTV.setText(values[0] + "out of " + values[1]);
        }

        @Override
        protected Integer doInBackground(Integer... params) {
            for (int i = params[0]; i < params[1]; i++) {
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (isCancelled()) {
                    return i;
                }

                publishProgress(i, params[1]);
                //startTime = i;
            }
            return 42;
            //return null;
        }

        @Override
        protected void onCancelled(Integer integer) {
            super.onCancelled(integer);
            startTime = integer;
        }

        @Override
        protected void onPostExecute(Integer aVoid) {

            super.onPostExecute(aVoid);
            startTime = aVoid;

            TextView timerTV = (TextView) findViewById(R.id.timerTextView);
            timerTV.setText("Done: " + startTime);
        }
    }


}
