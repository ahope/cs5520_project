package edu.neu.madcourse.mod7;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import edu.neu.madcourse.mod7.mod7.R;

public class WebServiceActivity extends AppCompatActivity {

    private static final String TAG = "WebServiceActivity";
    private String token;

    private EditText mArtistIdEditText;
    private EditText mArtistNameEditText;

    private static final String SPOTIFY_SEARCH_URL = "https://api.spotify.com/v1/search?type=artist&q=";

    private static final String SPOTIFY_ARTIST_URL = "https://api.spotify.com/v1/artists/";

    private static final String BEATLES_ID = "3WrFJ7ztbogyGnTHbHJFl2";

    List<String> genres = new ArrayList<String>();
    private ArrayAdapter<String> genreAdapter; //(this, android.R.layout.simple_list_item_1, values)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);

        mArtistIdEditText = (EditText)findViewById(R.id.artist_id_editText);
        mArtistNameEditText = (EditText)findViewById(R.id.artist_name_editText);

        new Thread(new Runnable() {
            @Override
            public void run() {
                getSpotifyAcessToken();
            }
        }).start();

        genreAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, genres);
        ListView genreListView = (ListView)findViewById(R.id.genre_listview);
        genreListView.setAdapter(genreAdapter);
    }

    public void getArtistIdButtonHandler(View view){
        GetArtistIdTask task = new GetArtistIdTask();
        task.execute(mArtistNameEditText.getText().toString());

    }


    // Pass in a String that is the name of a band.
    // When it returns, put the Spotify artist ID in an EditText.
    private class GetArtistIdTask  extends AsyncTask<String, Integer, String[]> {

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.i(TAG, "Making progress...");
        }

        @Override
        protected String[] doInBackground(String... params) {
            String[] results = new String[2];

            URL url = null;
            try {
                url = new URL(SPOTIFY_SEARCH_URL + params[0]);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Authorization", "Bearer " + token);
                conn.setDoInput(true);

                conn.connect();

                // Read response.
                InputStream inputStream = conn.getInputStream();
                final String resp = convertStreamToString(inputStream);

                JSONObject jObject = new JSONObject(resp);

                // Need artists:items:id from the json object

                JSONArray items = jObject.getJSONObject("artists").getJSONArray("items"); //.getString("id");

                JSONObject firstItem = items.getJSONObject(0);

                String foo = firstItem.getString("id");


                results[0] = foo;
                results[1] = firstItem.getString("name");

                return results;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            results[0] = "Something went wrong";
            return(results);
        }

        @Override
        protected void onPostExecute(String... s) {
            super.onPostExecute(s);
            EditText artist_id = (EditText)findViewById(R.id.artist_id_editText);
            artist_id.setText(s[0]);
            TextView artist_name_tv = (TextView)findViewById(R.id.artistNameTextView);
            artist_name_tv.setText(s[1]);
        }
    }

    public void getGenresForArtistId(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String results = getGenresInfo(mArtistIdEditText.getText().toString());
            }
        }).start();
    }

    private String getGenresInfo(String artistID){

        // Open the HTTP connection and send the payload
        URL url = null;
        try {
            url = new URL(SPOTIFY_ARTIST_URL + artistID);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Authorization", "Bearer " + token);
            conn.setDoInput(true);

            conn.connect();

            // Read FCM response.
            InputStream inputStream = conn.getInputStream();
            final String resp = convertStreamToString(inputStream);

            JSONObject jObject = new JSONObject(resp);
            JSONArray jArray = jObject.getJSONArray("genres");

            final List<String> tmp = new ArrayList<String>();

            for (int i=0; i < jArray.length(); i++)
            {
                try {
                    tmp.add(jArray.get(i).toString());
                } catch (JSONException e) {
                    // Oops
                }
            }

            // Attach a Handler to the UI (Main) thread so we can update the UI
            Handler h = new Handler(Looper.getMainLooper());
            h.post(new Runnable() {
                @Override
                public void run() {
                    Log.e(TAG, "run: " + resp);
                    Toast.makeText(WebServiceActivity.this,resp,Toast.LENGTH_LONG).show();

                    // Update the ListView by filling the adapter.
                    genreAdapter.clear();
                    genreAdapter.addAll(tmp);
                }
            });

            return resp;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "Something went wrong";
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

    private void getSpotifyAcessToken() {
        // POST https://accounts.spotify.com/api/token

        String TOKEN_URL = "https://accounts.spotify.com/api/token";

        HttpURLConnection conn = null;
        try {
            URL url = new URL(TOKEN_URL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            conn.setRequestProperty("Authorization", "Basic YTdhNDY5NDkxNDgzNGY5NDk4YmI1NzU1Zjg3NjA5MDU6ODVjMTljNmFhMzAzNDNhM2E1ZDk5NGUxYzBjYWZhYjI=");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
    //        conn.setRequestProperty();
            conn.setDoOutput(true);
//            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
//            out.writeChars("grant_type=client_credentials");

            OutputStream out = new BufferedOutputStream(conn.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    out, "UTF-8"));
            writer.write("grant_type=client_credentials");
            writer.flush();

            int responseCode = conn.getResponseCode();
            String message = conn.getResponseMessage();
            System.out.println("responseCode: " + responseCode);
            System.out.println(message);
//            conn.setDoInput(true);

            InputStream inputStream = conn.getInputStream();
            final String resp = convertStreamToString(inputStream);

            JSONObject jObject = new JSONObject(resp);
            System.out.println(jObject);
            token = jObject.getString("access_token");

            conn.disconnect();

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
//
//        myURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//        myURLConnection.setRequestProperty("Content-Length", "" + postData.getBytes().length);
//        myURLConnection.setRequestProperty("Content-Language", "en-US");
//
//        conn.connect();
//
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
//        RequestBody body = RequestBody.create(mediaType, "grant_type=client_credentials");
//        Request request = new Request.Builder()
//                .url("https://accounts.spotify.com/api/token")
//                .method("POST", body)
//                .addHeader("Authorization", "Basic YTdhNDY5NDkxNDgzNGY5NDk4YmI1NzU1Zjg3NjA5MDU6ODVjMTljNmFhMzAzNDNhM2E1ZDk5NGUxYzBjYWZhYjI=")
//                .addHeader("Content-Type", "application/x-www-form-urlencoded")
//                .addHeader("Cookie", "__Host-device_id=AQAAjCsEMr2vLNoYc2ugvq4PwGFcs8gW_D_PQDFJIVTrrGwh_P2aNINMFR4T9IJPy1Er2ZD4u67-sH10xMf_iOnJw5R41Nujt2Q")
//                .build();
//        Response response = client.newCall(request).execute();
    }


}

