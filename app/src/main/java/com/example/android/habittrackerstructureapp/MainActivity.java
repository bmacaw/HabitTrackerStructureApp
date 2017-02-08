package com.example.android.habittrackerstructureapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.habittrackerstructureapp.data.PracticeContract.PracticeEntry;
import com.example.android.habittrackerstructureapp.data.PracticeDbHelper;

public class MainActivity extends AppCompatActivity {

    private PracticeDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up Button to open Editor Activity
        Button addDataButton = (Button) findViewById(R.id.add_new_practice_button);
        addDataButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        mDbHelper = new PracticeDbHelper(this);

        // Set up Add Sample Practice Button
        Button addSampleDataButton = (Button) findViewById(R.id.add_sample_practice_button);
        addSampleDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertPractice();
                Intent addToView = getIntent();
                finish();
                startActivity(addToView);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the practice database.
     */
    private void displayDatabaseInfo() {
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        PracticeDbHelper mDbHelper = new PracticeDbHelper(this);

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                PracticeEntry._ID,
                PracticeEntry.COLUMN_PRACTICE_TOPIC,
                PracticeEntry.COLUMN_PRACTICE_DURATION

        };

        Cursor cursor = db.query(PracticeEntry.TABLE_NAME, projection, null, null, null, null, null);

        TextView displayView = (TextView) findViewById(R.id.text_view_practice);

        try {

            // Create a header in the Text View that looks like this:
            //
            // The pets table contains <number of rows in Cursor> pets.
            // _id - topic - duration
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("The practice table contains " + cursor.getCount() + " practice items.\n\n");
            displayView.append(PracticeEntry._ID + " - "
                    + PracticeEntry.COLUMN_PRACTICE_TOPIC + " - "
                    + PracticeEntry.COLUMN_PRACTICE_DURATION + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(PracticeEntry._ID);
            int topicColumnIndex = cursor.getColumnIndex(PracticeEntry.COLUMN_PRACTICE_TOPIC);
            int durationColumnIndex = cursor.getColumnIndex(PracticeEntry.COLUMN_PRACTICE_DURATION);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentTopic = cursor.getString(topicColumnIndex);
                int currentDuration = cursor.getInt(durationColumnIndex);

                // display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " + currentTopic + " - " + currentDuration));

            }

        } finally {

            // Close the cursor when finished reading from it to release all its
            // resources and make it invalid.
            cursor.close();
        }
    }

    private void insertPractice() {
        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(PracticeEntry.COLUMN_PRACTICE_TOPIC, "Meditation");
        values.put(PracticeEntry.COLUMN_PRACTICE_DURATION, 30);

        // Insert a new row for Piano in the database, returning the ID of that new row.
        // The first argument for db.insert() is the practice table name.
        // The second argument provides the name of a column in which the framework
        // can insert NULL in the event that the ContentValues is empty (if
        // this is set to "null", then the framework will not insert a row when
        // there are no values).
        // The third argument is the ContentValues object containing the info for Piano.
        long newRowId = db.insert(PracticeEntry.TABLE_NAME, null, values);

    }

}
