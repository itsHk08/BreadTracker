package com.example.budgettracker.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.budgettracker.R;
import com.example.budgettracker.database.DBHelper;

public class AddCategoryActivity extends AppCompatActivity {

    EditText categoryName;
    Button saveCategoryBtn;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        categoryName = findViewById(R.id.categoryName);
        saveCategoryBtn = findViewById(R.id.saveCategoryBtn);

        db = new DBHelper(this);

        saveCategoryBtn.setOnClickListener(v -> {

            String category =
                    categoryName.getText().toString().trim();

            if(category.isEmpty()){
                Toast.makeText(
                        this,
                        "Enter category name",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            boolean inserted =
                    db.insertCategory(category);

            if(inserted){
                Toast.makeText(
                        this,
                        "Category Saved",
                        Toast.LENGTH_SHORT
                ).show();

                finish();
            }
            else{
                Toast.makeText(
                        this,
                        "Error Saving Category",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}