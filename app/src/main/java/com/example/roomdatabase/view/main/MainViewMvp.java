package com.example.roomdatabase.view.main;

import com.example.roomdatabase.model.database.Users;

import java.util.List;

public interface MainViewMvp {
    public void addStudent(Users users);
    public void addListStudent(List<Users> usersList);
    public void clearAllStudent();
}
