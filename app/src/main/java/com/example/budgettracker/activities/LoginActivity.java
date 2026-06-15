package com.example.budgettracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.example.budgettracker.R;
import com.example.budgettracker.database.DBHelper;
import com.example.budgettracker.MainActivity;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button loginBtn, registerBtn;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        registerBtn = findViewById(R.id.registerBtn);

        db = new DBHelper(this);

        loginBtn.setOnClickListener(v -> {
            if (db.loginUser(
                    username.getText().toString(),
                    password.getText().toString())) {

                startActivity(new Intent(this, MainActivity.class));
                Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Invalid Login", Toast.LENGTH_SHORT).show();
            }
        });

        registerBtn.setOnClickListener(v ->
                startActivity(new Intent(this, RegisterActivity.class))
        );
    }
}
