package com.example.roomdatabase.view.detail;

import android.content.Intent;

import com.example.roomdatabase.model.database.Users;

public interface DetailPresenterMvp {
    public void addStudent(Users users);

    public void updateStudent(Users users);

    void preapareDetailView(Intent intent);
}
