package com.example.roomdatabase.view.register;

import android.graphics.Bitmap;

import com.example.roomdatabase.model.database.Users;

public interface RegisterPresenterMvp {
    Bitmap getDefaultImage();
    void addUser(Users user);
    void isNameFounder(String name);
}
