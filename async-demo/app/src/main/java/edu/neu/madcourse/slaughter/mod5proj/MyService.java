package edu.neu.madcourse.slaughter.mod5proj;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MyService extends Service {

    private static String TAG = "MyService";

    TextView timerTV;


    public MyService() {
    }

    @Override
    public void onCreate() {
        Log.v(TAG, "onCreate");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.v(TAG, "onDestroy       ");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.v(TAG, "onBind");
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v(TAG, "onStartCommand");

        Toast.makeText(this, " Service Started", Toast.LENGTH_LONG).show();

        // Return one of:
//        Service.START_STICKY
//        Service.START_NOT_STICKY
//        Service.START_REDELIVER_INTENT


        // NOTE: This is bad form. This is blocking the Main Thread.
        // Don't do this in real life!!
     /*  for (int i=0; i<15; i++){
            Log.v(TAG, "Running: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/


        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                Log.v(TAG, "seconds remaining: " + millisUntilFinished / 1000);

//
////
//                 TextView timerTV = (TextView)findViewById(R.id.timerTextView);
//                 timerTV.setText(Integer.toString(values[0]) + "out of " + Integer.toString(values[1]));
//                timerTV.post(new Runnable() {
//                    public void run() {
//                        MyService.this.timerTV.setText(Integer.toString(15));
//                    }
//                });
//

            }

            public void onFinish() {
                Log.v(TAG, "done!");

            }
        }.start();


        Toast.makeText(this, " Service Done Counting", Toast.LENGTH_LONG).show();
        return Service.START_STICKY;
        //return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean stopService(Intent name) {
        Log.v(TAG, "mainStopService");
        return super.stopService(name);
    }


    /***
     * Use bindService to start the service when you want to communicate with the service while it runs.
     * @param service
     * @param conn
     * @param flags
     * @return
     */
    @Override
    public boolean bindService(Intent service, ServiceConnection conn, int flags) {
        Log.v(TAG, "bindService");
        return super.bindService(service, conn, flags);
    }
}
