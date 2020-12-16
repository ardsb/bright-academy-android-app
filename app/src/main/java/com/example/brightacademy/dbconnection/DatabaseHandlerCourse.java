package com.example.brightacademy.dbconnection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.brightacademy.model.Course;

import java.util.ArrayList;
import java.util.List;

import static com.example.brightacademy.dbconnection.fields.Common.DATABASE_NAME;
import static com.example.brightacademy.dbconnection.fields.Common.DATABASE_VERSION;
import static com.example.brightacademy.dbconnection.fields.Common.TABLE_COURSE;
import static com.example.brightacademy.dbconnection.fields.CourseFields.KEY_COURSECODE;
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
    }

    // code to add the new contact
    public void addCourses(Course details) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_COURSE_NAME, details.getCoursename());
        values.put(KEY_DURATION , details.getDuration());
        values.put(KEY_FEE, details.getFee());
        values.put(KEY_DESCRIPTION, details.getDescription());
        values.put(KEY_DATE, details.getDate());

        // Inserting Row
        db.insert(TABLE_COURSE, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get all contacts in a list view
    public List<Course> getallcourse() {
        List<Course> courseList = new ArrayList<Course>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_COURSE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Course Course = new Course();
                Course.setCoursecode(Integer.parseInt(cursor.getString(0)));
                Course.setCoursename(cursor.getString(1));
                Course.setDescription(cursor.getString(2));
                Course.setDuration(cursor.getString(3));
                Course.setFee(cursor.getString(4));
                Course.setDate(cursor.getString(5));

                // Adding contact to lis
                courseList.add(Course);
            } while (cursor.moveToNext());
        }

        // return contact list
        return courseList;
    }
    public boolean deleteCourse(int id) {
        boolean result = false;
        String query = "Select * FROM " + TABLE_COURSE + " WHERE " + KEY_COURSECODE + " =  \"" + id + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Course course = new Course();
        if (cursor.moveToFirst()) {
            course.setCoursecode(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_COURSE, KEY_COURSECODE + " = ?",
                    new String[] { String.valueOf(course.getCoursecode()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public Course getCourseByCourseCode(int coursecode) {
        String query = "Select * FROM " + TABLE_COURSE + " WHERE " + KEY_COURSECODE + " =  \"" + coursecode + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Course course = new Course();
        if (cursor.moveToFirst()) {
            course.setCoursecode(Integer.parseInt(cursor.getString(0)));
            course.setCoursename(cursor.getString(1));
            course.setDescription(cursor.getString(2));
            course.setDuration(cursor.getString(3));
            course.setFee(cursor.getString(4));
            course.setDate(cursor.getString(5));
            cursor.close();
        }
        db.close();

        return course;


    }

    public boolean updateCourseForCourseCode(int coursecode, Course course) {
        boolean result = false;

        ContentValues values = new ContentValues();
        values.put(KEY_COURSE_NAME, course.getCoursename());
        values.put(KEY_DURATION , course.getDuration());
        values.put(KEY_FEE, course.getFee());
        values.put(KEY_DESCRIPTION, course.getDescription());
        values.put(KEY_DATE, course.getDate());


        String query = "Select * FROM " + TABLE_COURSE + " WHERE " + KEY_COURSECODE + " =  \"" + coursecode + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            db.update(TABLE_COURSE, values,KEY_COURSECODE + " = ?",
                    new String[] { String.valueOf(coursecode) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;

    }
}