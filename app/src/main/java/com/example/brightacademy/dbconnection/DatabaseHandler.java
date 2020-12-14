package com.example.brightacademy.dbconnection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.brightacademy.model.User;


import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "brightAcademy";

    private static final String TABLE_USER = "user";
    private static final String KEY_ID = "id";
    private static final String KEY_USR_NAME = "username";
    private static final String KEY_FULL_NAME = "fullname";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_USER_ROLE = "role";

    private static final String TAG = "DatabaseHandler";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_StudentsS_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_USR_NAME  + " TEXT,"
                + KEY_FULL_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_PASSWORD + " TEXT,"
                + KEY_USER_ROLE + " TEXT"
                + ")";
        db.execSQL(CREATE_StudentsS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        // Create tables again
        onCreate(db);
    }

    // code to add the new Students
    public void addUser(User details) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USR_NAME, details.getUsername());
        values.put(KEY_FULL_NAME, details.getFullName());
        values.put(KEY_EMAIL, details.getEmail());
        values.put(KEY_PASSWORD, details.getPassword());
        values.put(KEY_USER_ROLE, details.getUserRole().name());


        // Inserting Row
        try{
            db.insert(TABLE_USER, null, values);
        }catch (Exception e){
            Log.d(TAG, "addStudents: " + e.getMessage());
        }
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get all Studentss in a list view
    public List<User> getUserList() {
        List<User> userList = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User User = new User();
                User.setId(Integer.parseInt(cursor.getString(0)));
                User.setUsername(cursor.getString(1));
                User.setFullName(cursor.getString(2));
                User.setEmail(cursor.getString(3));
                User.setPassword(cursor.getString(4));
                // Adding Students to list
                userList.add(User);
            } while (cursor.moveToNext());
        }

        // return Students list
        return userList;
    }
    public boolean deleteUser(int id) {
        boolean result = false;
        String query = "Select * FROM " + TABLE_USER + " WHERE " + KEY_ID + " =  \"" + id + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User User = new User();
        if (cursor.moveToFirst()) {
            User.setId(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_USER, KEY_ID + " = ?",
                    new String[] { String.valueOf(User.getId()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

}