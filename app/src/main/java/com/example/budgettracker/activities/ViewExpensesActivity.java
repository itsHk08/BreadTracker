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

public class ViewExpensesActivity extends AppCompatActivity {

    EditText startDate, endDate;
    Button filterBtn;
    ListView expenseList;

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_expenses);

        startDate = findViewById(R.id.startDate);
        endDate = findViewById(R.id.endDate);
        filterBtn = findViewById(R.id.filterBtn);
        expenseList = findViewById(R.id.expenseList);

        db = new DBHelper(this);

        filterBtn.setOnClickListener(v -> {

            ArrayList<String> expenses =
                    new ArrayList<>();

            Cursor cursor =
                    db.getExpensesBetweenDates(
                            startDate.getText().toString(),
                            endDate.getText().toString()
                    );

            while(cursor.moveToNext()){

                expenses.add(
                        "Date: " +
                                cursor.getString(1) +
                                "\nCategory: " +
                                cursor.getString(5) +
                                "\nAmount: R" +
                                cursor.getDouble(6)
                );
            }

            cursor.close();

            ArrayAdapter<String> adapter =
                    new ArrayAdapter<>(
                            this,
                            android.R.layout.simple_list_item_1,
                            expenses
                    );

            expenseList.setAdapter(adapter);
        });
    }
}