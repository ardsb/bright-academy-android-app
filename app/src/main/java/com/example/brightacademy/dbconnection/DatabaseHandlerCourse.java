package com.example.brightacademy.dbconnection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.brightacademy.model.Courses;

import java.util.ArrayList;
import java.util.List;

import static com.example.brightacademy.dbconnection.fields.Common.DATABASE_NAME;
import static com.example.brightacademy.dbconnection.fields.Common.DATABASE_VERSION;
import static com.example.brightacademy.dbconnection.fields.Common.TABLE_COURSES;
import static com.example.brightacademy.dbconnection.fields.CourseFields.KEY_COURSE_NAME;
import static com.example.brightacademy.dbconnection.fields.CourseFields.KEY_DATE;
import static com.example.brightacademy.dbconnection.fields.CourseFields.KEY_DESCRIPTION;
import static com.example.brightacademy.dbconnection.fields.CourseFields.KEY_DURATION;
import static com.example.brightacademy.dbconnection.fields.CourseFields.KEY_FEE;

public class DatabaseHandlerCourse extends SQLiteOpenHelper {


    public DatabaseHandlerCourse(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSES);

        // Create tables again
        onCreate(db);
    }

    // code to add the new contact
    public void addCourses(Courses details) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_COURSE_NAME, details.getCoursename());
        values.put(KEY_DESCRIPTION, details.getDescription());
        values.put(KEY_DURATION , details.getDuration());
        values.put(KEY_FEE, details.getFee());
        values.put(KEY_DATE, details.getDate());

        // Inserting Row
        db.insert(TABLE_COURSES, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get all contacts in a list view
    public List<Courses> addingCourses() {
        List<Courses> contactList = new ArrayList<Courses>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_COURSES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Courses Courses = new Courses();
                Courses.setCoursecode(Integer.parseInt(cursor.getString(0)));
                Courses.setCoursename(cursor.getString(1));
                Courses.setDuration(cursor.getString(2));
                Courses.setFee(cursor.getString(3));
                Courses.setDescription(cursor.getString(4));
                Courses.setDate(cursor.getString(4));
                // Adding contact to list
                contactList.add(Courses);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }
//    public boolean deleteContact(int id) {
//        boolean result = false;
//        String query = "Select * FROM " + TABLE_CONTACTS + " WHERE " + KEY_ID + " =  \"" + id + "\"";
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(query, null);
//        Contact contact = new Contact();
//        if (cursor.moveToFirst()) {
//            contact.setId(Integer.parseInt(cursor.getString(0)));
//            db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
//                    new String[] { String.valueOf(contact.getId()) });
//            cursor.close();
//            result = true;
//        }
//        db.close();
//        return result;
//    }

}