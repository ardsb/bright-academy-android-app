package com.example.brightacademy.dbconnection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.brightacademy.model.User;
import com.example.brightacademy.model.UserRole;


import java.util.ArrayList;
import java.util.List;

import static com.example.brightacademy.dbconnection.fields.Common.DATABASE_NAME;
import static com.example.brightacademy.dbconnection.fields.Common.DATABASE_VERSION;
import static com.example.brightacademy.dbconnection.fields.Common.TABLE_COURSE;
import static com.example.brightacademy.dbconnection.fields.Common.TABLE_USER;
import static com.example.brightacademy.dbconnection.fields.Common.TABLE_USER_COURSE;
import static com.example.brightacademy.dbconnection.fields.CourseFields.KEY_COURSECODE;
import static com.example.brightacademy.dbconnection.fields.CourseFields.KEY_COURSE_NAME;
import static com.example.brightacademy.dbconnection.fields.CourseFields.KEY_DATE;
import static com.example.brightacademy.dbconnection.fields.CourseFields.KEY_DESCRIPTION;
import static com.example.brightacademy.dbconnection.fields.CourseFields.KEY_DURATION;
import static com.example.brightacademy.dbconnection.fields.CourseFields.KEY_FEE;
import static com.example.brightacademy.dbconnection.fields.UserCourseFields.KEY_COURSE_CODE;
import static com.example.brightacademy.dbconnection.fields.UserCourseFields.KEY_USER_ID;
import static com.example.brightacademy.dbconnection.fields.UserFields.KEY_EMAIL;
import static com.example.brightacademy.dbconnection.fields.UserFields.KEY_FULL_NAME;
import static com.example.brightacademy.dbconnection.fields.UserFields.KEY_ID;
import static com.example.brightacademy.dbconnection.fields.UserFields.KEY_PASSWORD;
import static com.example.brightacademy.dbconnection.fields.UserFields.KEY_USER_ROLE;

public class DatabaseHandlerUser extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHandler";

    public DatabaseHandlerUser(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_Students_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_FULL_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_PASSWORD + " TEXT,"
                + KEY_USER_ROLE + " TEXT"
                + ")";
        db.execSQL(CREATE_Students_TABLE);

        String CREATE_Course_TABLE = "CREATE TABLE " + TABLE_COURSE + "("
                + KEY_COURSECODE + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_COURSE_NAME + " TEXT,"
                + KEY_DESCRIPTION + " TEXT,"
                + KEY_DURATION + " TEXT,"
                + KEY_FEE + " TEXT,"
                + KEY_DATE + " TEXT"
                + ")";
        db.execSQL(CREATE_Course_TABLE);

        String CREATE_UserCourse_TABLE = "CREATE TABLE " + TABLE_USER_COURSE + "("
                + KEY_USER_ID + " INTEGER,"
                + KEY_COURSE_CODE + " INTEGER"
                + ")";
        db.execSQL(CREATE_UserCourse_TABLE );
    }


    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_COURSE);

        // Create tables again
        onCreate(db);
    }

    // code to add the new Students
    public void addUser(User details) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FULL_NAME, details.getFullName());
        values.put(KEY_EMAIL, details.getEmail());
        values.put(KEY_PASSWORD, details.getPassword());
        values.put(KEY_USER_ROLE, details.getUserRole().toString());


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

    public User validateUser(String email, String password) {
        String query = "Select * FROM " + TABLE_USER + " WHERE "
                + KEY_EMAIL + " =  \"" + email + "\"" + " AND "
                + KEY_PASSWORD + " =  \"" + password + "\"";
        Log.d(TAG, "validateUser: SQL QUERY >>>>> " + query);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User outsiderUser = new User();

        if (cursor.moveToFirst()){
            //User is found with validate username and password
            Log.d(TAG, "validateUser: insideIfUser with username: " + email + " passwrord: " + password + "IS VALID" );
            outsiderUser.setId(Integer.parseInt(cursor.getString(0)));
            outsiderUser.setFullName(cursor.getString(1));
            outsiderUser.setEmail(cursor.getString(2));
            outsiderUser.setPassword(cursor.getString(3));
            outsiderUser.setUserRole(UserRole.valueOf(cursor.getString(4)));
            // Adding Students to list
            cursor.close();
        }else{
            //user is not found
            outsiderUser = null;
            Log.d(TAG, "validateUser: user with username: " + email + " passwrord: " + password + "IS NOT VALID" );
        }
        db.close();
        return outsiderUser;
    }

    public User getUserById(int userId) {
        String query = "Select * FROM " + TABLE_USER + " WHERE " + KEY_ID + " =  \"" + userId + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        User user = new User();
        if (cursor.moveToFirst()) {
            user.setId(Integer.parseInt(cursor.getString(0)));
            user.setId(Integer.parseInt(cursor.getString(0)));
            user.setFullName(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            user.setPassword(cursor.getString(3));
            user.setUserRole(UserRole.valueOf(cursor.getString(4)));
            cursor.close();

        }
        db.close();
        return user;
    }
}