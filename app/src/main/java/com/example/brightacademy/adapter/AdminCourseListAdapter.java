package com.example.brightacademy.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.brightacademy.R;
import com.example.brightacademy.dbconnection.DatabaseHandlerCourse;
import com.example.brightacademy.model.Course;
import com.example.brightacademy.view.AddCourseActivity;
import com.example.brightacademy.view.AdminCourseListActivity;

import java.util.List;


public class AdminCourseListAdapter extends ArrayAdapter<Course> {

        private final Context mContext;
        private final List<Course> contactsList;
        private DatabaseHandlerCourse db;


    public AdminCourseListAdapter(@NonNull Context context, List<Course> list, DatabaseHandlerCourse db) {
            super(context, 0 , list);
            mContext = context;
            contactsList = list;
            this.db = db;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View viewTemplate, @NonNull ViewGroup parent) {
        if(viewTemplate == null)
            viewTemplate = LayoutInflater.from(mContext).inflate(R.layout.admin_course_list_item,parent,false);

        Course currentCourse = contactsList.get(position);
        final Context context = viewTemplate.getContext();


        TextView coursecode = viewTemplate.findViewById(R.id.txtcoursecode);
        TextView coursename = viewTemplate.findViewById(R.id.txtcoursename);
        TextView duration = viewTemplate.findViewById(R.id.txtduration);
        TextView fee  = viewTemplate.findViewById(R.id.txtfee);
        TextView date = viewTemplate.findViewById(R.id.txtdate);
        TextView description = viewTemplate.findViewById(R.id.txtdescription);
        Button delete = viewTemplate.findViewById(R.id.btnDelete);
        Button edit = viewTemplate.findViewById(R.id.btnEdit);

        coursecode.setText(String.valueOf(currentCourse.getCoursecode()));
        coursename.setText(currentCourse.getCoursename());
        duration.setText(String.valueOf(currentCourse.getDuration()));
        fee.setText(String.valueOf(currentCourse.getFee()));
        description.setText(currentCourse.getDescription());
        date.setText(currentCourse.getDate());
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(context)
                        .setTitle("Warning!")
                        .setMessage(String.format("Are you sure you want to delete %s?",currentCourse.getCoursename()))
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (db.deleteCourse(currentCourse.getCoursecode())) {
                                    remove(currentCourse);
                                }
                            }
                        })
                        .setNegativeButton("No", null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }

        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addCourseIntent = new Intent(mContext,  AddCourseActivity.class);
                addCourseIntent.putExtra("coursecode",currentCourse.getCoursecode());
                context.startActivity(addCourseIntent);

            }
        });


        return viewTemplate;
    }

}
