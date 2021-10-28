package edu.neu.madcourse.mod7;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import edu.neu.madcourse.mod7.mod7.R;

public class LaunchWebActivity extends AppCompatActivity {

    private EditText mUrlEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_web);

        mUrlEditText = (EditText)findViewById(R.id.web_dest_edit_text);
    }

    public void launchWeb(View view){
        // Get the URL from the EditText
        String url = mUrlEditText.getText().toString();

        // Create a URI from the URL
        Uri uri = Uri.parse(url);

        // Build the intent
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        // Start the activity
        startActivity(intent);

    }
}
