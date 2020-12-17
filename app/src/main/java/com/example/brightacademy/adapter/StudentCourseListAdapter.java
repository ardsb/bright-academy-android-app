package com.example.brightacademy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.brightacademy.R;
import com.example.brightacademy.dbconnection.DatabaseHandlerCourse;
import com.example.brightacademy.model.Course;


import java.util.List;

public class StudentCourseListAdapter extends ArrayAdapter<Course> {

    private final Context mContext;
    private final List<Course> contactsList;
    private DatabaseHandlerCourse db;

    public StudentCourseListAdapter(@NonNull Context context, List<Course> list, DatabaseHandlerCourse db) {
        super(context, 0 , list);
        mContext = context;
        contactsList = list;
        this.db = db;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View viewTemplate, @NonNull ViewGroup parent) {
        if (viewTemplate == null)
            viewTemplate = LayoutInflater.from(mContext).inflate(R.layout.student_course_list_item, parent, false);

        Course currentCourse = contactsList.get(position);

        TextView coursename = viewTemplate.findViewById(R.id.txtcoursename);
        TextView description = viewTemplate.findViewById(R.id.txtdescription);

        coursename.setText(currentCourse.getCoursename());
        description.setText(currentCourse.getDescription());

        return viewTemplate;
    }
}
