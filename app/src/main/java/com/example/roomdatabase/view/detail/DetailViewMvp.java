package com.example.roomdatabase.view.detail;

import com.example.roomdatabase.model.database.Users;

public interface DetailViewMvp {
    public void finishedActivity();
    public void setIconButtonFab(int idRes);
    public void putDataStudentToField(Users users);
}
