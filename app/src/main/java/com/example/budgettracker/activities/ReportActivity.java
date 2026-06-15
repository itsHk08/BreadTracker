package com.example.budgettracker.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.budgettracker.R;
import com.example.budgettracker.database.DBHelper;

import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {

    EditText startDate, endDate;
    Button generateBtn;
    ListView reportList;

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        startDate = findViewById(R.id.startDate);
        endDate = findViewById(R.id.endDate);
        generateBtn = findViewById(R.id.generateBtn);
        reportList = findViewById(R.id.reportList);

        db = new DBHelper(this);

        generateBtn.setOnClickListener(v -> {

            ArrayList<String> report =
                    new ArrayList<>();

            Cursor cursor =
                    db.getCategoryTotalsBetweenDates(
                            startDate.getText().toString(),
                            endDate.getText().toString()
                    );

            while(cursor.moveToNext()){

                report.add(
                        cursor.getString(0)
                                + " : R"
                                + cursor.getDouble(1)
                );
            }

            cursor.close();

            ArrayAdapter<String> adapter =
                    new ArrayAdapter<>(
                            this,
                            android.R.layout.simple_list_item_1,
                            report
                    );

            reportList.setAdapter(adapter);
        });
    }
}