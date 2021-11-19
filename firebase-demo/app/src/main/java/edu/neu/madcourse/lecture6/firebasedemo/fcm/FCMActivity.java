package edu.neu.madcourse.lecture6.firebasedemo.fcm;

import android.os.Handler;
import android.os.Looper;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.iid.FirebaseInstanceId;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import edu.neu.madcourse.lecture6.firebasedemo.R;

public class FCMActivity extends AppCompatActivity {

    private static final String TAG = FCMActivity.class.getSimpleName();

    // Please add the server key from your firebase console in the following format "key=<serverKey>"
    private static final String SERVER_KEY = "key=AAAAH_d7SW8:APA91bHxXEeOwys_WtQKQ-E8dreUTJIa1tMVKxNHUMYBAB0k9ac9XJndOvdzO42dHJezuRQtJK799IjfjJdADYH3HqPjsbDao9N8YOkInU4FPXnEldWbf473Giw5mSNruTiBOmu7-9KC";

    // This is the client registration token
    private static final String CLIENT_REGISTRATION_TOKEN = "eHW7_oFgX3k:APA91bGo40oFxqNDhFZajtfZ3yZmlSSy9N9O7ULjDgPa6C3fpLN7xHXx0bXMkLNTnsy_H7IP1LleUnLMVuqFndnVcEvCOglzpBM4AuQMLm8Tt2jOAg9Lz1-vwuxZYwScGAWhL3YjdSHc";

    private TextView tokenTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fcm);

        tokenTextView = findViewById(R.id.displayTokenTextView);

        Button logTokenButton = (Button) findViewById(R.id.logTokenButton);
        logTokenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get token

                FirebaseMessaging.getInstance().getToken().addOnSuccessListener(
                        new OnSuccessListener<String>() {
                            @Override
                            public void onSuccess(String token) {
                                tokenTextView.setText(token);
                                // Log and toast
                                String msg = getString(R.string.msg_token_fmt, token);
                                Log.d(TAG, msg);
                                Toast.makeText(FCMActivity.this, msg, Toast.LENGTH_SHORT).show();
                            }
                        }
                );


            }
        });
    }

    public void subscribeToNews(View view){
        // [START subscribe_topics]
        FirebaseMessaging.getInstance().subscribeToTopic("news")
                .addOnCompleteListener(
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Subscribe to 'news' topic successful!";
                        if (!task.isSuccessful()) {
                            msg = "Subscribe to 'news' topic failed";
                        }
                        Log.d(TAG, msg);
                        Toast.makeText(FCMActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                }
        );
        // [END subscribe_topics]

    }

    /**
     * Button Handler; creates a new thread that sends off a message
     * @param type
     */
    public void sendMessageToNews(View type) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                sendMessageToNews();
            }
        }).start();
    }

    private void sendMessageToNews(){
        JSONObject jPayload = new JSONObject();
        JSONObject jNotification = new JSONObject();
        try {
            jNotification.put("message", "This is a Firebase Cloud Messaging topic message!");
            jNotification.put("body", "Hello from the other side.... Adele");
            jNotification.put("sound", "default");
            jNotification.put("badge", "1");
            jNotification.put("click_action", "OPEN_ACTIVITY_1");

            // Populate the Payload object.
            // Note that "to" is a topic, not a token representing an app instance
            jPayload.put("to", "/topics/news");
            jPayload.put("priority", "high");
            jPayload.put("notification", jNotification);


            // Open the HTTP connection and send the payload
            URL url = new URL("https://fcm.googleapis.com/fcm/send");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", SERVER_KEY);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Send FCM message content.
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(jPayload.toString().getBytes());
            outputStream.close();

            // Read FCM response.
            InputStream inputStream = conn.getInputStream();
            final String resp = convertStreamToString(inputStream);


            // Execute the network request-- do it off the main/ui thread
            Handler h = new Handler(Looper.getMainLooper());
            h.post(new Runnable() {
                @Override
                public void run() {
                    Log.e(TAG, "run: " + resp);
                    Toast.makeText(FCMActivity.this,resp,Toast.LENGTH_LONG).show();
                }
            });
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessageToDevice(View type) {

        final String targetToken = ((EditText)findViewById(R.id.tokenEditText)).getText().toString();
        final String message = ((TextView)findViewById(R.id.message_text_view)).getText().toString();

        new Thread(new Runnable() {
            @Override
            public void run() {
                sendMessageToDevice(targetToken, message);
            }
        }).start();
    }


    public void sendMessageToDevice_New(View type) {
        FirebaseMessaging fm = FirebaseMessaging.getInstance();
        fm.send(new RemoteMessage.Builder(SERVER_KEY + "@fcm.googleapis.com")
                .setMessageId(Integer.toString(19))
                .addData("my_message", "Hello World")
                .addData("my_action","SAY_HELLO")
                .setMessageType("")
                .build());
    }

    /**
     * Pushes a notification to a given device-- in particular, this device,
     * because that's what the instanceID token is defined to be.
     */
    private void sendMessageToDevice(String targetToken, String message) {
        JSONObject jPayload = new JSONObject();
        JSONObject jNotification = new JSONObject();
        try {
            jNotification.put("title", "Mod 6 Demo");
            jNotification.put("body", message);
            jNotification.put("sound", "default");
            jNotification.put("badge", "1");
            /*
            // We can add more details into the notification if we want.
            // We happen to be ignoring them for this demo.
            jNotification.put("click_action", "OPEN_ACTIVITY_1");
            */

            /***
             * The Notification object is now populated.
             * Next, build the Payload that we send to the server.
             */

            // If sending to a single client
            jPayload.put("to", targetToken);

            /*
            // If sending to multiple clients (must be more than 1 and less than 1000)
            JSONArray ja = new JSONArray();
            ja.put(CLIENT_REGISTRATION_TOKEN);
            // Add Other client tokens
            ja.put(FirebaseInstanceId.getInstance().getToken());
            jPayload.put("registration_ids", ja);
            */

            jPayload.put("priority", "high");
            jPayload.put("notification", jNotification);


            /***
             * The Payload object is now populated.
             * Send it to Firebase to send the message to the appropriate recipient.
             */
            URL url = new URL("https://fcm.googleapis.com/fcm/send");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", SERVER_KEY);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Send FCM message content.
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(jPayload.toString().getBytes());
            outputStream.close();

            // Read FCM response.
            InputStream inputStream = conn.getInputStream();
            final String resp = convertStreamToString(inputStream);

            Handler h = new Handler(Looper.getMainLooper());
            h.post(new Runnable() {
                @Override
                public void run() {
                    Log.e(TAG, "run: " + resp);
                    Toast.makeText(FCMActivity.this,resp,Toast.LENGTH_LONG).show();
                }
            });
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper function
     * @param is
     * @return
     */
    private String convertStreamToString(InputStream is) {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next().replace(",", ",\n") : "";
    }



}
