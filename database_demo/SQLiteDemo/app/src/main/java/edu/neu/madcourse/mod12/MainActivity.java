package edu.neu.madcourse.mod12;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showDbEnter(View view){
        startActivity(new Intent(this, EnterDataActivity.class));
    }

    public void showDbRead(View view){
        startActivity(new Intent(this, ReadDataActivity.class));
    }

    public void showDbCPRead(View view){
        startActivity(new Intent(this, ReadContentProviderActivity.class));
    }
}
