package com.example.brightacademy.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.brightacademy.view.fragments.AllCourseFragment;
import com.example.brightacademy.view.fragments.EnrolledCourseFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private int userId;

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior, int userId) {
        super(fm, behavior);
        this.userId = userId;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return AllCourseFragment.newInstance(userId);
            case 1:
                return EnrolledCourseFragment.newInstance(userId);
            default:
                return AllCourseFragment.newInstance(userId);
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "All Course";
            case 1:
                return "Enrolled Course";
            default:return "";
        }
    }
}
