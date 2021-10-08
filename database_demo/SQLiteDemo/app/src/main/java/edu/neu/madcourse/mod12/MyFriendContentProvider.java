package edu.neu.madcourse.mod12;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class MyFriendContentProvider extends ContentProvider {

    private static final String AUTHORITY = "edu.neu.madcourse.mod12";

    private static final String BASE_PATH = "friends";

    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
            + "/" + BASE_PATH);


    private MyFriendsData friendsDb;


    @Override
    public boolean onCreate() {
        friendsDb = new MyFriendsData(getContext());
        return true;
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
       // throw new UnsupportedOperationException("Not yet implemented");
        System.out.println("We're inserting data!!@!");
        return null;
    }



    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

/*      This is what we had in our orignal "read database" code:
        SQLiteDatabase db = friendsDb.getReadableDatabase();
        Cursor cursor = db.query(MyFriendsData.FRIENDS_TABLE, FROM, null, null, null,
                null, null);
        return cursor;*/

        // Uisng SQLiteQueryBuilder instead of query() method
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        // Should check if the caller has requested a column which does not exists

        // Set the table
        queryBuilder.setTables(MyFriendsData.FRIENDS_TABLE);


        SQLiteDatabase db = friendsDb.getReadableDatabase();

        Cursor cursor = queryBuilder.query(db,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

        // make sure that potential listeners are getting notified
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;


        /*
        Can do different things for different URIs here:
        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case TODOS:
                break;
            case TODO_ID:
                // adding the ID to the original query
                queryBuilder.appendWhere(TodoTable.COLUMN_ID + "="
                        + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }*/

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }


}
