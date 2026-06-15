package com.example.budgettracker.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.budgettracker.R;
import com.example.budgettracker.database.DBHelper;

import java.util.ArrayList;

public class AchievementActivity extends AppCompatActivity {

    ListView achievementList;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);

        achievementList = findViewById(R.id.achievementList);

        db = new DBHelper(this);

        ArrayList<String> achievements =
                new ArrayList<>();

        Cursor cursor =
                db.getReadableDatabase().rawQuery(
                        "SELECT COUNT(*) FROM expenses",
                        null);

        int expenseCount = 0;

        if(cursor.moveToFirst()){
            expenseCount = cursor.getInt(0);
        }

        cursor.close();

        if(expenseCount >= 1){
            achievements.add("🏅 First Expense Added");
        }

        if(expenseCount >= 5){
            achievements.add("🥈 Expense Tracker");
        }

        if(expenseCount >= 10){
            achievements.add("🥇 Budget Master");
        }

        if(expenseCount >= 20){
            achievements.add("💎 BreadTracker Champion");
        }

        if(achievements.isEmpty()){
            achievements.add(
                    "No achievements unlocked yet");
        }

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_list_item_1,
                        achievements
                );

        achievementList.setAdapter(adapter);
    }
}