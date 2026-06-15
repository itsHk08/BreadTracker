package com.example.budgettracker.activities;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.example.budgettracker.R;
import com.example.budgettracker.database.DBHelper;

public class RegisterActivity extends AppCompatActivity {

    EditText username, password;
    Button registerBtn;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        registerBtn = findViewById(R.id.registerBtn);

        db = new DBHelper(this);

        registerBtn.setOnClickListener(v -> {
            if (db.registerUser(
                    username.getText().toString(),
                    password.getText().toString())) {

                Toast.makeText(this, "Registered!", Toast.LENGTH_SHORT).show();
                finish();

            } else {
                Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}