package edu.neu.madcourse.mod12;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
//import androidx.core.widget.SimpleCursorAdapter;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class ReadDataActivity extends AppCompatActivity {

    private static int[] TO = {R.id.friend_list_item_id,
            R.id.friend_list_item_lastName,
            R.id.friend_list_item_firstName,
            R.id.friend_list_item_rating,
            R.id.friend_list_item_color };

    private static String[] FROM = { "_id",
            MyFriendsData.FR_FIRST_NAME_COL,
            MyFriendsData.FR_LAST_NAME_COL,
            MyFriendsData.FR_QUALITY_COL,
            MyFriendsData.FR_HAIR_COLOR};

    private MyFriendsData friendsDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_data);

        ListView myFriendsList = (ListView)findViewById(R.id.friends_listView);

        friendsDb = new MyFriendsData(this);
        SQLiteDatabase db = friendsDb.getReadableDatabase();
        /*Cursor query (String table,
                String[] columns,
                String selection,
                String[] selectionArgs,
                String groupBy,
                String having,
                String orderBy)*/
        Cursor cursor = db.query(MyFriendsData.FRIENDS_TABLE,
                FROM,
                null,
                null,
                null,
                null,
                "_id");
//                MyFriendsData.FR_QUALITY_COL);

        // SELECT * FROM friends;

        startManagingCursor(cursor);

        // Set up data binding
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.friend_list_item, cursor, FROM, TO);

        myFriendsList.setAdapter(adapter);  //setListAdapter(adapter);



    }


}
