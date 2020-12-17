package com.example.brightacademy.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.brightacademy.R;
import com.example.brightacademy.adapter.StudentCourseListAdapter;
import com.example.brightacademy.dbconnection.DatabaseHandlerCourse;
import com.example.brightacademy.model.Course;
import com.example.brightacademy.view.StudentCourseDetailActivity;

import java.util.List;

public class EnrolledCourseFragment extends Fragment {

    static String INTENT_USER_ID = "INTENT_USER_ID";
    int userId;
    ListView list;
    DatabaseHandlerCourse db ;

    public EnrolledCourseFragment() {

    }

    public static EnrolledCourseFragment newInstance(int userId) {
        EnrolledCourseFragment fragment = new EnrolledCourseFragment();
        Bundle args = new Bundle();
        args.putInt(INTENT_USER_ID,userId);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        db = new DatabaseHandlerCourse( context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userId = getArguments().getInt(INTENT_USER_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_enrolled_course, container, false);
        list=rootView.findViewById(R.id.listStudentCourse);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Course> allCourse=db.getCourseEnrolledByUser(userId);

        StudentCourseListAdapter adapter=new StudentCourseListAdapter(getContext(), allCourse,db);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Course selectedCourse = allCourse.get(position);
                Intent addCourseIntent = new Intent(view.getContext(),  StudentCourseDetailActivity.class);
                addCourseIntent.putExtra("coursecode",selectedCourse.getCoursecode());
                addCourseIntent.putExtra("userId",userId);
                startActivity(addCourseIntent);

            }
        });
    }
}