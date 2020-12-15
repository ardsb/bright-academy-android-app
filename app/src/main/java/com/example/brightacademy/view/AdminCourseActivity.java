package com.example.brightacademy.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.brightacademy.R;
import com.example.brightacademy.dbconnection.DatabaseHandlerCourse;
import com.example.brightacademy.model.Courses;

public class AdminCourseActivity extends AppCompatActivity {

    EditText coursename,duration,fee,description,date;
    Button add;
    
    DatabaseHandlerCourse db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_course);

        db=new DatabaseHandlerCourse(this);

      coursename=findViewById(R.id.input_CourseName);
      duration=findViewById(R.id.input_Duration);
      fee=findViewById(R.id.input_Fee);
      description=findViewById(R.id.input_Description);
        date=findViewById(R.id.date);


        add=findViewById(R.id.btnAdd);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Coursename = coursename.getText().toString().trim();
                String Duration = duration.getText().toString().trim();
                String Fee = fee.getText().toString().trim();
                String Description = description.getText().toString().trim();



                if(!TextUtils.isEmpty(Coursename) && !TextUtils.isEmpty(Duration) && !TextUtils.isEmpty(Fee) && !TextUtils.isEmpty(Description)){


                    db.addCourses(new Courses(Coursename,Duration,Fee,Description));

                    coursename.setText("");
                    duration.setText("");
                    fee.setText("");
                    description.setText("");
                    Toast.makeText(AdminCourseActivity.this, "You have successfully registered",Toast.LENGTH_SHORT ).show();

                }else {

                    Toast.makeText(AdminCourseActivity.this, "Enter valid input",Toast.LENGTH_SHORT ).show();
                }






            }
        });


    }


    public void onDataSelectionClick(View view) {
        DatePickerDialog datePickerDialog = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            datePickerDialog = new DatePickerDialog(this);
            datePickerDialog.show();
            datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    Toast.makeText(view.getContext(), String.format("date : %d, month :%d, year: %d", dayOfMonth,month,year),Toast.LENGTH_SHORT).show();
                    date.setText(String.format("%d/%d/%d", dayOfMonth,month,year));
                }
            });
        }
    }
}