package com.example.brightacademy.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.brightacademy.R;
import com.example.brightacademy.adapter.StudentCourseListAdapter;
import com.example.brightacademy.adapter.ViewPagerAdapter;
import com.example.brightacademy.dbconnection.DatabaseHandlerCourse;
import com.example.brightacademy.dbconnection.DatabaseHandlerUser;
import com.example.brightacademy.model.Course;
import com.example.brightacademy.model.User;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class StudentDashboardActivity extends AppCompatActivity {
    DatabaseHandlerUser dbUser;
    TextView name,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        dbUser = new DatabaseHandlerUser(this);
        name=findViewById(R.id.txtName);
        email=findViewById(R.id.txtEmail1);

        int userId = getIntent().getIntExtra("userId", -1);
        User user = dbUser.getUserById(userId);

        name.setText(user.getFullName());
        email.setText(user.getEmail());

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(
                getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT,
                userId
        );
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}