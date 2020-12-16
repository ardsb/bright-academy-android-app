package com.example.brightacademy.model;

public class Course {
    private int coursecode;
    private String coursename;
    private String description;
    private String duration;
    private String fee;
    private String date;

    public Course(String coursename, String duration, String fee, String description, String date) {

        this.description = description;
        this.duration = duration;
        this.fee = fee;
        this.coursename = coursename;
        this.date = date;

    }

    public Course() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public int getCoursecode() {
        return coursecode;
    }

    public void setCoursecode(int coursecode) {
        this.coursecode = coursecode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }
}
