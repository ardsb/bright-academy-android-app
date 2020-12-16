package com.example.brightacademy.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.brightacademy.R;
import com.example.brightacademy.adapter.StudentCourseListAdapter;
import com.example.brightacademy.dbconnection.DatabaseHandlerCourse;
import com.example.brightacademy.dbconnection.DatabaseHandlerUser;
import com.example.brightacademy.model.Course;
import com.example.brightacademy.model.User;

import java.util.List;

public class StudentDashboardActivity extends AppCompatActivity {
    DatabaseHandlerCourse db ;
    DatabaseHandlerUser dbUser;
    ListView list;
    TextView name,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        db = new DatabaseHandlerCourse( this);
        dbUser = new DatabaseHandlerUser(this);

        list=findViewById(R.id.listStudentCourse);
        List<Course> allCourse=db.getallcourse();

        name=findViewById(R.id.txtName);
        email=findViewById(R.id.txtEmail1);

        int userId = getIntent().getIntExtra("userId", -1);
        User user = dbUser.getUserById(userId);

        name.setText(user.getFullName());
        email.setText(user.getEmail());



        StudentCourseListAdapter adapter=new StudentCourseListAdapter(this, allCourse,db);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Course selectedCourse = allCourse.get(position);
                Intent addCourseIntent = new Intent(view.getContext(),  StudentCourseDetailActivity.class);
                addCourseIntent.putExtra("coursecode",selectedCourse.getCoursecode());
                startActivity(addCourseIntent);




            }
        });
    }
}