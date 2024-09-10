package com.example.tdma;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry1 = "CREATE TABLE users(username TEXT, email TEXT, password TEXT)";
        db.execSQL(qry1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrade as needed
    }

    // Method to register a new user
    public void register(String username, String email, String password) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);
        SQLiteDatabase database = getWritableDatabase();
        database.insert("users", null, cv);
        database.close();
    }

    // Method to login a user
    public int login(String email, String password) {
        int result = 0;
        String[] str = {email, password};
        SQLiteDatabase database = getWritableDatabase();
        Cursor c = database.rawQuery("SELECT * FROM users WHERE email=? AND password=?", str);
        if (c.moveToFirst()) {
            result = 1;
        }
        c.close();
        database.close();
        return result;
    }

    // Method to get user data by username
    public User getUserData(String username) {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM users WHERE username=?", new String[]{username});
        User user = null;
        if (cursor.moveToFirst()) {
            String email = cursor.getString(1); // Assuming email is the second column
            String password = cursor.getString(2); // Assuming password is the third column
            user = new User(username, email, password);
        }
        cursor.close();
        database.close();
        return user;
    }

    // Method to get username by email
    public String getUsernameByEmail(String email) {
        SQLiteDatabase database = getReadableDatabase();
        String username = null;

        // Define the query to get the username by email
        Cursor cursor = database.rawQuery("SELECT username FROM users WHERE email=?", new String[]{email});

        // Check if a result was returned
        if (cursor.moveToFirst()) {
            // Extract the username from the cursor
            username = cursor.getString(cursor.getColumnIndexOrThrow("username"));
        }

        // Close the cursor and database
        cursor.close();
        database.close();

        return username;
    }
}
