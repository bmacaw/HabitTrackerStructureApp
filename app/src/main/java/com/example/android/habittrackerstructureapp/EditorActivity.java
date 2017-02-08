package com.example.android.habittrackerstructureapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.habittrackerstructureapp.data.PracticeContract.PracticeEntry;
import com.example.android.habittrackerstructureapp.data.PracticeDbHelper;

/**
 * Allows user to create a new practice session or edit an existing one.
 */

public class EditorActivity extends AppCompatActivity {

    /**
     * EditText field to enter the practice topic
     */
    private EditText mTopicEditText;

    /**
     * EditText field to enter the practice duration
     */
    private EditText mDurationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        // Find all relevant views that we will need to read user input from
        mTopicEditText = (EditText) findViewById(R.id.edit_topic);
        mDurationEditText = (EditText) findViewById(R.id.edit_duration);

        // Set up click listener on Submit Practice button.
        Button submitPracticeButton = (Button) findViewById(R.id.submit_practice_button);
        submitPracticeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertPractice();
                NavUtils.navigateUpFromSameTask(EditorActivity.this);
            }
        });

    }

    /**
     * Get user input from editor and save new practice session into database
     */
    private void insertPractice() {
        String topicString = mTopicEditText.getText().toString().trim();
        String durationString = mDurationEditText.getText().toString().trim();
        int duration = Integer.parseInt(durationString);

        // Create database helper
        PracticeDbHelper mDbHelper = new PracticeDbHelper(this);

        // gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a Content Values object where column names are the keys
        // and practice attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(PracticeEntry.COLUMN_PRACTICE_TOPIC, topicString);
        values.put(PracticeEntry.COLUMN_PRACTICE_DURATION, duration);

        // Insert a new row for practice session in the database, returning the ID of that new row.
        long newRowId = db.insert(PracticeEntry.TABLE_NAME, null, values);

        // Show a toast message depending on whether or not there was an error with insertion.
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving practice session", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Practice session saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }


}
