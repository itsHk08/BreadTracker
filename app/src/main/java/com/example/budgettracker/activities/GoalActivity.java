package com.example.budgettracker.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.budgettracker.R;
import com.example.budgettracker.database.DBHelper;

public class GoalActivity extends AppCompatActivity {

    EditText minGoal, maxGoal;
    Button saveGoalBtn;

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        minGoal = findViewById(R.id.minGoal);
        maxGoal = findViewById(R.id.maxGoal);
        saveGoalBtn = findViewById(R.id.saveGoalBtn);

        db = new DBHelper(this);

        saveGoalBtn.setOnClickListener(v -> {

            String minText =
                    minGoal.getText().toString();

            String maxText =
                    maxGoal.getText().toString();

            if(minText.isEmpty() || maxText.isEmpty()){

                Toast.makeText(
                        this,
                        "Enter both goals",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            boolean saved =
                    db.saveGoal(
                            Double.parseDouble(minText),
                            Double.parseDouble(maxText)
                    );

            if(saved){

                Toast.makeText(
                        this,
                        "Goals Saved",
                        Toast.LENGTH_SHORT
                ).show();

                finish();
            }
        });
    }
}