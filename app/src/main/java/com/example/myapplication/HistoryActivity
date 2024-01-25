package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HistoryActivity extends AppCompatActivity {

    private TextView historyTextView;
    private CDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historyTextView = findViewById(R.id.historyTextView);
        dbHelper = new CDBHelper(this);

        displayHistory();
    }

    private void displayHistory() {
        StringBuilder history = new StringBuilder("Storia dei risultati:\n");

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(CDBHelper.TABLE_NAME, null, null, null, null, null, null);

        int columnIndex = cursor.getColumnIndex(CDBHelper.COLUMN_RESULT);

        while (cursor.moveToNext()) {
            if (columnIndex >= 0) {
                double result = cursor.getDouble(columnIndex);
                history.append(result).append("\n");
            }
        }

        cursor.close();
        db.close();

        historyTextView.setText(history.toString());
    }
}

