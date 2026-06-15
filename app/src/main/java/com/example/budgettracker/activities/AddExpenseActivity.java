package com.example.budgettracker.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.budgettracker.R;
import com.example.budgettracker.database.DBHelper;

public class AddExpenseActivity extends AppCompatActivity {

    EditText date, start, end, desc, category, amount;

    Button saveBtn;
    Button selectImageBtn;

    ImageView expenseImage;

    DBHelper db;

    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        date = findViewById(R.id.date);
        start = findViewById(R.id.startTime);
        end = findViewById(R.id.endTime);
        desc = findViewById(R.id.description);
        category = findViewById(R.id.category);
        amount = findViewById(R.id.amount);

        saveBtn = findViewById(R.id.saveBtn);
        selectImageBtn = findViewById(R.id.selectImageBtn);

        expenseImage = findViewById(R.id.expenseImage);

        db = new DBHelper(this);

        selectImageBtn.setOnClickListener(v -> {

            Intent intent = new Intent(
                    Intent.ACTION_PICK);

            intent.setType("image/*");

            startActivityForResult(intent, 100);
        });

        saveBtn.setOnClickListener(v -> {

            String imagePath = "";

            if(imageUri != null){
                imagePath = imageUri.toString();
            }

            boolean inserted =
                    db.insertExpense(
                            date.getText().toString(),
                            start.getText().toString(),
                            end.getText().toString(),
                            desc.getText().toString(),
                            category.getText().toString(),
                            Double.parseDouble(
                                    amount.getText().toString()),
                            imagePath
                    );

            if(inserted){

                Toast.makeText(
                        this,
                        "Expense Saved!",
                        Toast.LENGTH_SHORT
                ).show();

                finish();

            }else{

                Toast.makeText(
                        this,
                        "Error saving",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    @Override
    protected void onActivityResult(
            int requestCode,
            int resultCode,
            Intent data) {

        super.onActivityResult(
                requestCode,
                resultCode,
                data);

        if(requestCode == 100 &&
                resultCode == RESULT_OK &&
                data != null){

            imageUri = data.getData();

            expenseImage.setImageURI(imageUri);
        }
    }
}