package com.example.android.habittrackerstructureapp.data;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.habittrackerstructureapp.data.PracticeContract.PracticeEntry;

/**
 * Database helper for Pets app. Created by Rebecca Macaw on 2/7/2017.
 * Manages database creation and version management.
 */

public class PracticeDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = PracticeDbHelper.class.getSimpleName();

    /* Name of the database file */
    private static final String DATABASE_NAME = "habits.db";

    /**
     * Database version. If you change the database scheme, you ust increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link PracticeDbHelper}.
     *
     * @param context of the app
     */
    public PracticeDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the practice table
        String SQL_CREATE_PRACTICE_TABLE = "CREATE TABLE " + PracticeEntry.TABLE_NAME + " ("
                + PracticeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PracticeEntry.COLUMN_PRACTICE_TOPIC + " TEXT NOT NULL, "
                + PracticeEntry.COLUMN_PRACTICE_DURATION + " INTEGER NOT NULL DEFAULT 0);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_PRACTICE_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
