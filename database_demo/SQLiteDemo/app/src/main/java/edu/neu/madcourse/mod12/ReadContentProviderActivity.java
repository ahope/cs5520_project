package edu.neu.madcourse.mod12;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.database.Cursor;

/*import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;*/


// Or use these, but don't mix and match
import android.content.CursorLoader;
import android.content.Loader;
import android.app.LoaderManager.LoaderCallbacks;
import android.widget.SimpleCursorAdapter;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import android.net.*;

public class ReadContentProviderActivity extends Activity
        implements LoaderManager.LoaderCallbacks<Cursor>  {

    private static int[] TO = {R.id.friend_list_item_rating,
            R.id.friend_list_item_firstName,
            R.id.friend_list_item_lastName,
            R.id.friend_list_item_rating };

    private static String[] FROM = { "_id",
            MyFriendsData.FR_FIRST_NAME_COL,
            MyFriendsData.FR_LAST_NAME_COL,
            MyFriendsData.FR_HAIR_COLOR };
    private SimpleCursorAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_content_provider);

        ListView myFriendsList = (ListView)findViewById(R.id.friends_cp_listView);

        getLoaderManager().initLoader(0, null, this);

        adapter = new SimpleCursorAdapter(this,
                R.layout.friend_list_item,
                null,
                FROM,
                TO,
                0);

        myFriendsList.setAdapter(adapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // Projection = which columns should be returned
        String[] projection = FROM;
        CursorLoader cursorLoader = new CursorLoader(this,
                MyFriendContentProvider.CONTENT_URI,
                projection, null, null, MyFriendsData.FR_QUALITY_COL);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        adapter.swapCursor(data);

       insertData();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // data is not available anymore, delete reference
        adapter.swapCursor(null);
    }

    private void insertData() {
        // Defines an object to contain the new values to insert
        ContentValues newValues = new ContentValues();

        /*
         * Sets the values of each column and inserts the word. The arguments to the "put"
         * method are "column name" and "value"
         */
        newValues.put(MyFriendsData.FR_FIRST_NAME_COL, "ben");
        newValues.put(MyFriendsData.FR_LAST_NAME_COL, "bitdiddle");
        newValues.put(MyFriendsData.FR_HAIR_COLOR, "purple");
        newValues.put(MyFriendsData.FR_QUALITY_COL, "10");

        Uri newUri = getContentResolver().insert(
                MyFriendContentProvider.CONTENT_URI,   // the user dictionary content URI
                newValues                          // the values to insert
        );

    }
}
