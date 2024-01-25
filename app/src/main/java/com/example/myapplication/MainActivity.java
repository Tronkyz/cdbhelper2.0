package com.example.myapplication;

        import android.content.ContentValues;
        import android.content.Intent;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.TextView;
        import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText number1EditText, number2EditText;
    private TextView resultTextView;
    private CDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number1EditText = findViewById(R.id.number1EditText);
        number2EditText = findViewById(R.id.number2EditText);
        resultTextView = findViewById(R.id.resultTextView);

        dbHelper = new CDBHelper(this);
    }

    public void onOperationClick(View view) {
        try {
            double num1 = Double.parseDouble(number1EditText.getText().toString());
            double num2 = Double.parseDouble(number2EditText.getText().toString());

            double result = 0;

            int viewId = view.getId();

            if (viewId == R.id.addButton) {
                result = num1 + num2;
            } else if (viewId == R.id.subtractButton) {
                result = num1 - num2;
            } else if (viewId == R.id.multiplyButton) {
                result = num1 * num2;
            } else if (viewId == R.id.divideButton) {
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    resultTextView.setText("Errore: Divisione per zero");
                    return;
                }
            }

            resultTextView.setText("Risultato: " + result);
            saveResultToDatabase(result);

        } catch (NumberFormatException e) {
            resultTextView.setText("Errore: Inserisci numeri validi");
        }
    }

    public void onSaveClick(View view) {
        try {
            double result = Double.parseDouble(resultTextView.getText().toString().substring(10));
            saveResultToDatabase(result);

        } catch (NumberFormatException e) {
            resultTextView.setText("Errore: Risultato non valido");
        }
    }

    public void onShowHistoryClick(View view) {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    private void saveResultToDatabase(double result) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CDBHelper.COLUMN_RESULT, result);

        db.insert(CDBHelper.TABLE_NAME, null, values);
        db.close();
    }
}

