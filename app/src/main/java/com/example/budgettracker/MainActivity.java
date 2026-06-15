package com.example.budgettracker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.budgettracker.activities.AddCategoryActivity;
import com.example.budgettracker.activities.AddExpenseActivity;
import com.example.budgettracker.activities.AchievementActivity;
import com.example.budgettracker.activities.GoalActivity;
import com.example.budgettracker.activities.GraphActivity;
import com.example.budgettracker.activities.ReportActivity;
import com.example.budgettracker.activities.ViewExpensesActivity;

public class MainActivity extends AppCompatActivity {

    Button addCategoryBtn;
    Button addExpenseBtn;
    Button viewExpenseBtn;
    Button goalBtn;
    Button reportBtn;
    Button achievementBtn;
    Button graphBtn;

    Switch darkModeSwitch;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        preferences = getSharedPreferences(
                "BreadTrackerSettings",
                MODE_PRIVATE);

        boolean darkMode =
                preferences.getBoolean(
                        "dark_mode",
                        false);

        if (darkMode) {

            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);

        } else {

            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        darkModeSwitch =
                findViewById(R.id.darkModeSwitch);

        darkModeSwitch.setChecked(darkMode);

        darkModeSwitch.setOnCheckedChangeListener(
                (buttonView, isChecked) -> {

                    SharedPreferences.Editor editor =
                            preferences.edit();

                    editor.putBoolean(
                            "dark_mode",
                            isChecked);

                    editor.apply();

                    if (isChecked) {

                        AppCompatDelegate.setDefaultNightMode(
                                AppCompatDelegate.MODE_NIGHT_YES);

                    } else {

                        AppCompatDelegate.setDefaultNightMode(
                                AppCompatDelegate.MODE_NIGHT_NO);
                    }

                    recreate();
                });

        addCategoryBtn =
                findViewById(R.id.addCategoryBtn);

        addExpenseBtn =
                findViewById(R.id.addExpenseBtn);

        viewExpenseBtn =
                findViewById(R.id.viewExpenseBtn);

        goalBtn =
                findViewById(R.id.goalBtn);

        reportBtn =
                findViewById(R.id.reportBtn);

        achievementBtn =
                findViewById(R.id.achievementBtn);

        graphBtn =
                findViewById(R.id.graphBtn);

        addCategoryBtn.setOnClickListener(v ->
                startActivity(new Intent(
                        MainActivity.this,
                        AddCategoryActivity.class)));

        addExpenseBtn.setOnClickListener(v ->
                startActivity(new Intent(
                        MainActivity.this,
                        AddExpenseActivity.class)));

        viewExpenseBtn.setOnClickListener(v ->
                startActivity(new Intent(
                        MainActivity.this,
                        ViewExpensesActivity.class)));

        goalBtn.setOnClickListener(v ->
                startActivity(new Intent(
                        MainActivity.this,
                        GoalActivity.class)));

        reportBtn.setOnClickListener(v ->
                startActivity(new Intent(
                        MainActivity.this,
                        ReportActivity.class)));

        achievementBtn.setOnClickListener(v ->
                startActivity(new Intent(
                        MainActivity.this,
                        AchievementActivity.class)));

        graphBtn.setOnClickListener(v ->
                startActivity(new Intent(
                        MainActivity.this,
                        GraphActivity.class)));
    }
}