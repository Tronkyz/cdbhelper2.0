package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CDBHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "calculator.db";
        private static final int DATABASE_VERSION = 1;
        static final String TABLE_NAME = "calculation_history";
        static final String COLUMN_RESULT = "result";

        private static final String TABLE_CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_RESULT + " REAL);";

        CDBHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
                db.execSQL(TABLE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
                onCreate(db);
        }

        public SQLiteDatabase openReadableDatabase() {
                return getReadableDatabase();
        }

        public SQLiteDatabase openWritableDatabase() {
                return getWritableDatabase();
        }
}