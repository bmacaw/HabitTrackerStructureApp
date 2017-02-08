package com.example.android.habittrackerstructureapp.data;

import android.provider.BaseColumns;

/**
 * API Contract for the Meditation Practice app. Created by Rebecca on 2/7/2017.
 */

public class PracticeContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private PracticeContract() {
    }

    /**
     * Inner class that defines constant values for the database table.
     * Each entry in the table represents a single row of data.
     */
    public static final class PracticeEntry implements BaseColumns {

        /**
         * Name of database table for practice
         */
        public final static String TABLE_NAME = "practice";

        /**
         * Unique ID number for the practice session (only for use in the database table)
         * <p>
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Topic of Practice.
         * <p>
         * Type: TEXT
         */
        public final static String COLUMN_PRACTICE_TOPIC = "topic";

        /**
         * Duration of the practice.
         * <p>
         * Type: INTEGER
         */
        public final static String COLUMN_PRACTICE_DURATION = "duration";

    }


}
