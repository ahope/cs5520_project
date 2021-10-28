package edu.neu.madcourse.mod7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import edu.neu.madcourse.mod7.mod7.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startLaunchWebActivity(View view){
        startActivity(new Intent(MainActivity.this, LaunchWebActivity.class));
    }

    public void startWebViewActivity(View view){
        startActivity(new Intent(MainActivity.this, WebViewActivity.class));
    }

    public void startWebServiceActivity(View view){
        startActivity(new Intent(MainActivity.this, WebServiceActivity.class));
    }

    public void startNetworkInfoActivity(View view){
        startActivity(new Intent(MainActivity.this, NetworkInfoActivity.class));
    }
}
