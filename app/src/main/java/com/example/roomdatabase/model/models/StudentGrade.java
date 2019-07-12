package com.example.roomdatabase.model.models;

import android.graphics.Bitmap;

public class StudentGrade {
    Bitmap  Photo;
    String name, degree;

    public StudentGrade() {
    }

    public StudentGrade(Bitmap photo, String name, String grade) {
        Photo = photo;
        this.name = name;
        this.degree = grade;
    }

    public StudentGrade(String name, String grade) {
        this.name = name;
        this.degree = grade;
    }

    public Bitmap getPhoto() {
        return Photo;
    }

    public void setPhoto(Bitmap photo) {
        Photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }


}
