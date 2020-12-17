package com.example.brightacademy.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brightacademy.R;
import com.example.brightacademy.dbconnection.DatabaseHandlerCourse;
import com.example.brightacademy.model.Course;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textfield.TextInputLayout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddCourseActivity extends AppCompatActivity {

    EditText coursenameEditText, durationEditText, feeEditText, descriptionEditText, dateEditText,courseCodeEditView;
    Button add, update;
    TextInputLayout courseCodeLayout;
    
    DatabaseHandlerCourse db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        db=new DatabaseHandlerCourse(this);

      coursenameEditText =findViewById(R.id.input_CourseName);
      durationEditText =findViewById(R.id.input_Duration);
      feeEditText = findViewById(R.id.input_Fee);
      descriptionEditText =findViewById(R.id.input_Description);
      dateEditText =findViewById(R.id.date);
      add=findViewById(R.id.btnAdd);
      update=findViewById(R.id.btnUpdate);
      courseCodeEditView=findViewById(R.id.input_CoursCode);
      courseCodeLayout=findViewById(R.id.input_layout_CourseCode);

        Intent intent = getIntent();
        int coursecode  = intent.getIntExtra("coursecode",-1);

        Toast.makeText(this, String.format("Coursecode from intent extra : %d", coursecode),Toast.LENGTH_SHORT).show();

        if(coursecode!=-1){
            //course code is not -1 this is called when edit button is clicked from the admincourselistadapter
            Course course =  db.getCourseByCourseCode(coursecode);

            coursenameEditText.setText(course.getCoursename());
            durationEditText.setText(course.getDuration());
            feeEditText.setText(course.getFee());
            descriptionEditText.setText(course.getDescription());
            dateEditText.setText(course.getDate());
            add.setVisibility(View.GONE);
            update.setVisibility(View.VISIBLE);
            courseCodeLayout.setVisibility(View.VISIBLE);
            courseCodeEditView.setText(String.valueOf(coursecode));
        }else {
            //course code is -1  this is called when add button is clicked from admincourselistactivity
            add.setVisibility(View.VISIBLE);
            update.setVisibility(View.GONE);
            courseCodeLayout.setVisibility(View.GONE);
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courseName = coursenameEditText.getText().toString().trim();
                String duration = AddCourseActivity.this.durationEditText.getText().toString().trim();
                String fee = feeEditText.getText().toString().trim();
                String description = descriptionEditText.getText().toString().trim();
                String date= dateEditText.getText().toString().trim();
                if(!TextUtils.isEmpty(courseName) && !TextUtils.isEmpty(duration) && !TextUtils.isEmpty(fee) && !TextUtils.isEmpty(description) && !TextUtils.isEmpty(date) ){

                    Course course = new Course(courseName,duration,fee,description,date);
                    boolean updateSuccess = db.updateCourseForCourseCode(coursecode, course);
                    if(updateSuccess){
                        Toast.makeText(AddCourseActivity.this, "Course update successful",Toast.LENGTH_SHORT ).show();
                    }else {
                        Toast.makeText(AddCourseActivity.this, "Unable to update course please try again",Toast.LENGTH_SHORT ).show();
                    }
                    finish();
                }else {

                    Toast.makeText(AddCourseActivity.this, "Enter valid input",Toast.LENGTH_SHORT ).show();
                }
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String courseName = coursenameEditText.getText().toString().trim();
                String duration = AddCourseActivity.this.durationEditText.getText().toString().trim();
                String fee = feeEditText.getText().toString().trim();
                String description = descriptionEditText.getText().toString().trim();
                String date= dateEditText.getText().toString().trim();

                if(!TextUtils.isEmpty(courseName) && !TextUtils.isEmpty(duration) && !TextUtils.isEmpty(fee) && !TextUtils.isEmpty(description) && !TextUtils.isEmpty(date) ){

                    db.addCourses(new Course(courseName,duration,fee,description,date));

                    coursenameEditText.setText("");
                    durationEditText.setText("");
                    feeEditText.setText("");
                    descriptionEditText.setText("");
                    dateEditText.setText("");

                    Toast.makeText(AddCourseActivity.this, "You have successfully added a course",Toast.LENGTH_SHORT ).show();
                    finish();

                }else {

                    Toast.makeText(AddCourseActivity.this, "Enter valid input",Toast.LENGTH_SHORT ).show();
                }






            }
        });


    }


    public void onDataSelectionClick(View view) {
        MaterialDatePicker.Builder<Pair<Long,Long>> builder =
                MaterialDatePicker.Builder.dateRangePicker();
        MaterialDatePicker<Pair<Long,Long>> picker = builder.build();
        picker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Pair<Long,Long>>() {
            @Override
            public void onPositiveButtonClick(Pair<Long,Long> selection) {
                Date startDate = new Date(selection.first);
                Date endDate = new Date(selection.second);
                DateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
                String formattedStartDate = formater.format(startDate);
                String formattedEndDate = formater.format(endDate);
                dateEditText.setText(String.format("Starts On : %s Ends At : %s", formattedStartDate,formattedEndDate));
            }
        });

        picker.show(getSupportFragmentManager(), picker.toString());

    }
}