package edu.neu.madcourse.mod12;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

public class EnterDataActivity extends AppCompatActivity {

    private MyFriendsData friendsDb ;

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText hairColorEditText;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_data);

        friendsDb = new MyFriendsData(this);

        firstNameEditText = (EditText)findViewById(R.id.first_name_editText);
        lastNameEditText = (EditText)findViewById(R.id.last_name_editText);
        ratingBar = (RatingBar)findViewById(R.id.rating);
        hairColorEditText = findViewById(R.id.hair_color_editText);
    }

    public void addNewEntry(View view){
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String hairColor = hairColorEditText.getText().toString();
        float rating = ratingBar.getRating();

        addFriend(firstName, lastName, hairColor, (int)rating);

        firstNameEditText.setText("");
        lastNameEditText.setText("");
        hairColorEditText.setText("");
        ratingBar.setRating(2.5f);

    }

    public void addFriend(String fName, String lName, String hairColor, int rating) {
        // Insert a new record into the Events data source.
        // You would do something similar for delete and update.
        SQLiteDatabase db = friendsDb.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MyFriendsData.FR_FIRST_NAME_COL, fName);
        values.put(MyFriendsData.FR_LAST_NAME_COL, lName);
        values.put(MyFriendsData.FR_QUALITY_COL, rating);
        values.put(MyFriendsData.FR_HAIR_COLOR, hairColor);

        db.insertOrThrow(MyFriendsData.FRIENDS_TABLE, null, values);


    }


}
