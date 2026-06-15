package com.example.budgettracker.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "BudgetDB";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // USERS
        db.execSQL("CREATE TABLE users(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT," +
                "password TEXT)");

        // CATEGORIES
        db.execSQL("CREATE TABLE categories(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT)");

        // EXPENSES
        db.execSQL("CREATE TABLE expenses(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "date TEXT," +
                "startTime TEXT," +
                "endTime TEXT," +
                "description TEXT," +
                "category TEXT," +
                "amount REAL," +
                "image TEXT)");

        // GOALS
        db.execSQL("CREATE TABLE goals(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "min REAL," +
                "max REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS categories");
        db.execSQL("DROP TABLE IF EXISTS expenses");
        db.execSQL("DROP TABLE IF EXISTS goals");

        onCreate(db);
    }

    // REGISTER USER
    public boolean registerUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        android.content.ContentValues cv = new android.content.ContentValues();
        cv.put("username", username);
        cv.put("password", password);

        long result = db.insert("users", null, cv);
        return result != -1;
    }

    // LOGIN USER
    public boolean loginUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        android.database.Cursor cursor = db.rawQuery(
                "SELECT * FROM users WHERE username=? AND password=?",
                new String[]{username, password});

        return cursor.moveToFirst();
    }

    public boolean insertExpense(String date, String start, String end,
                                 String desc, String category,
                                 double amount, String image) {

        SQLiteDatabase db = this.getWritableDatabase();

        android.content.ContentValues cv = new android.content.ContentValues();
        cv.put("date", date);
        cv.put("startTime", start);
        cv.put("endTime", end);
        cv.put("description", desc);
        cv.put("category", category);
        cv.put("amount", amount);
        cv.put("image", image);

        long result = db.insert("expenses", null, cv);
        return result != -1;
    }

    public boolean insertCategory(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        android.content.ContentValues cv = new android.content.ContentValues();
        cv.put("name", name);
        long result = db.insert("categories", null, cv);
        return result != -1;
    }

    public boolean saveGoal(double min, double max) {

        SQLiteDatabase db = this.getWritableDatabase();

        android.content.ContentValues cv =
                new android.content.ContentValues();

        cv.put("min", min);
        cv.put("max", max);

        long result =
                db.insert("goals", null, cv);

        return result != -1;
    }

    public Cursor getAllExpenses() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM expenses", null);
    }

    public android.database.Cursor getCategoryTotals() {

        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery(
                "SELECT category, SUM(amount) " +
                        "FROM expenses " +
                        "GROUP BY category",
                null
        );
    }

    public android.database.Cursor getExpensesBetweenDates(
            String startDate,
            String endDate) {

        SQLiteDatabase db =
                this.getReadableDatabase();

        return db.rawQuery(
                "SELECT * FROM expenses " +
                        "WHERE date >= ? AND date <= ?",
                new String[]{
                        startDate,
                        endDate
                });
    }

    public android.database.Cursor getCategoryTotalsBetweenDates(
            String startDate,
            String endDate) {

        SQLiteDatabase db =
                this.getReadableDatabase();

        return db.rawQuery(
                "SELECT category, SUM(amount) " +
                        "FROM expenses " +
                        "WHERE date >= ? AND date <= ? " +
                        "GROUP BY category",
                new String[]{
                        startDate,
                        endDate
                });
    }
}
