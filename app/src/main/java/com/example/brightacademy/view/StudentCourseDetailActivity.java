package com.example.brightacademy.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brightacademy.R;
import com.example.brightacademy.dbconnection.DatabaseHandlerCourse;
import com.example.brightacademy.model.Course;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class StudentCourseDetailActivity extends AppCompatActivity {

    DatabaseHandlerCourse db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_course_detail);

        db = new DatabaseHandlerCourse( this);

        TextView coursecodeText = findViewById(R.id.txtcoursecode);
        TextView coursenameText = findViewById(R.id.txtcoursename);
        TextView durationText = findViewById(R.id.txtduration);
        TextView feeText  = findViewById(R.id.txtfee);
        TextView dateText = findViewById(R.id.txtdate);
        TextView descriptionText = findViewById(R.id.txtdescription);



        Intent intent = getIntent();
        int coursecode  = intent.getIntExtra("coursecode",-1);


        if(coursecode!=-1) {
            //course code is not -1 this is called when edit button is clicked from the admincourselistadapter
            Course course = db.getCourseByCourseCode(coursecode);

            coursecodeText.setText(String.valueOf(course.getCoursecode()));
            coursenameText.setText(course.getCoursename());
            durationText.setText(course.getDuration());
            feeText.setText(course.getFee());
            dateText.setText(course.getDescription());
            descriptionText.setText(course.getDate());

        }




    }
}