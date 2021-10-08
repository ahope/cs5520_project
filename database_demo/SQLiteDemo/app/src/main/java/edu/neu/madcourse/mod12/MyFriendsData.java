package edu.neu.madcourse.mod12;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ahslaughter on 3/30/17.
 */

public class MyFriendsData extends SQLiteOpenHelper {

    private static String DB_NAME = "friends.db";

    private static int DB_VERSION = 5;

    public static String FRIENDS_TABLE = "friends";

    public static String FR_FIRST_NAME_COL = "firstName";

    public static String FR_LAST_NAME_COL = "lastName";

    public static String FR_QUALITY_COL = "rating";

    public static String FR_HAIR_COLOR = "hairColor";

    // Wrap the constructor with values we know relevant to this dbΩ
    public MyFriendsData(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ FRIENDS_TABLE + "(" +
            "_id INTEGER PRIMARY KEY AUTOINCREMENT,  " +
            FR_FIRST_NAME_COL + " TEXT, " +
            FR_LAST_NAME_COL + " TEXT, " +
            FR_QUALITY_COL + " INTEGER, " +
                FR_HAIR_COLOR + " TEXT );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Default; Could do something more interesting.
        db.execSQL("DROP TABLE IF EXISTS " + FRIENDS_TABLE);
        onCreate(db);
    }


}
