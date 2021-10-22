package edu.neu.madcourse.slaughter.mod5proj;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyCameraReceiver extends BroadcastReceiver {

    private static String TAG = "MyCameraReceiver";

    public MyCameraReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.d(TAG, "Received something");

        Toast.makeText(context, "Received", Toast.LENGTH_SHORT).show();


//        Intent actIntent = new Intent(context, MainActivity.class);
//        actIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(actIntent);

        Log.d(TAG, "Starting service from MainActivity");
        Intent sintent = new Intent(context, MyService.class);
        sintent.putExtra("KEY1", "Value to be used by the service");
        // intent.addCategory(CAMERA_SERVICE);
        context.startService(sintent);

        Intent fooIntent = new Intent(context, MainActivity.class);
        context.startActivity(fooIntent);


    }
}
