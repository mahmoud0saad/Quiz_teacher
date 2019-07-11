package com.example.roomdatabase.view.main;

import com.example.roomdatabase.model.database.Users;

public interface MainPresenterMVP {
    public void refreshAllData();
    void refreshLimitData(int first ,int last);
    void deleteStudent(Users users);
}
