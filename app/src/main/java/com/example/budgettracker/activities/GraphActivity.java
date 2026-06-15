package com.example.budgettracker.activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.budgettracker.R;
import com.example.budgettracker.database.DBHelper;

public class GraphActivity extends AppCompatActivity {

    ProgressBar progressBar;

    TextView currentSpentTxt;
    TextView minGoalTxt;
    TextView maxGoalTxt;
    TextView statusTxt;

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        progressBar = findViewById(R.id.progressBar);

        currentSpentTxt = findViewById(R.id.currentSpentTxt);
        minGoalTxt = findViewById(R.id.minGoalTxt);
        maxGoalTxt = findViewById(R.id.maxGoalTxt);
        statusTxt = findViewById(R.id.statusTxt);

        db = new DBHelper(this);

        SQLiteDatabase database =
                db.getReadableDatabase();

        double totalSpent = 0;
        double minGoal = 0;
        double maxGoal = 100;

        Cursor expenseCursor =
                database.rawQuery(
                        "SELECT SUM(amount) FROM expenses",
                        null);

        if (expenseCursor.moveToFirst()) {

            if (!expenseCursor.isNull(0)) {
                totalSpent =
                        expenseCursor.getDouble(0);
            }
        }

        expenseCursor.close();

        Cursor goalCursor =
                database.rawQuery(
                        "SELECT min,max FROM goals LIMIT 1",
                        null);

        if (goalCursor.moveToFirst()) {

            minGoal = goalCursor.getDouble(0);
            maxGoal = goalCursor.getDouble(1);
        }

        goalCursor.close();

        currentSpentTxt.setText(
                "Current Spending: R" + totalSpent);

        minGoalTxt.setText(
                "Minimum Goal: R" + minGoal);

        maxGoalTxt.setText(
                "Maximum Goal: R" + maxGoal);

        progressBar.setMax((int) maxGoal);
        progressBar.setProgress((int) totalSpent);

        if (totalSpent < minGoal) {

            statusTxt.setText(
                    "🟡 Below Minimum Goal");

            statusTxt.setTextColor(
                    Color.parseColor("#FFA500"));

        } else if (totalSpent <= maxGoal) {

            statusTxt.setText(
                    "🟢 Within Budget");

            statusTxt.setTextColor(
                    Color.GREEN);

        } else {

            statusTxt.setText(
                    "🔴 Over Budget");

            statusTxt.setTextColor(
                    Color.RED);
        }
    }
}