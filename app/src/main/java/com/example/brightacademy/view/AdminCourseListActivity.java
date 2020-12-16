package com.example.brightacademy.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.brightacademy.R;
import com.example.brightacademy.adapter.AdminCourseListAdapter;
import com.example.brightacademy.dbconnection.DatabaseHandlerCourse;
import com.example.brightacademy.model.Course;

import java.util.List;

public class AdminCourseListActivity extends AppCompatActivity {

    private static final String TAG = "AdminCourseListActivity";
    DatabaseHandlerCourse db;
    ListView list;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_admin_course_list);

        getSupportActionBar().setTitle("Course List");
        db = new DatabaseHandlerCourse(AdminCourseListActivity .this);

        list=findViewById(R.id.listAdminCourse);


    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        final List<Course> data = db.getallcourse();
        AdminCourseListAdapter adapter=new AdminCourseListAdapter(AdminCourseListActivity.this, data,db);
        list.setAdapter(adapter);
        Log.d(TAG, "onResume:");
    }




//
    public void showAllRecords(){

        List<Course> data = db.getallcourse();

        for (Course contact:data){

            Log.d("Contact  Name :", contact.getCoursename());
            Log.d("Contact Number :", contact.getDuration() );
        }


    }

    public void onAddCourseclick(View view) {
        Intent mainActivityIntent = new Intent(AdminCourseListActivity.this,  AddCourseActivity.class);
        startActivity(mainActivityIntent);
    }
}