package com.example.roomdatabase.model;

import android.content.Context;

import com.example.roomdatabase.model.database.AppDatabase;
import com.example.roomdatabase.model.database.Users;

import java.util.ArrayList;
import java.util.List;

public class Manager {
    List<Users> mStudentsList;
    private static Manager instance=null;
    private static Object lock=new Object();

    public Manager() {
        this.mStudentsList = new ArrayList<>();
    }

    public void addStudent(Users users){
        mStudentsList.add(users);
    }
    public List<Users> getAllStudent(){
       return mStudentsList;
    }

    public static Manager getInstance(){
        if (instance==null){
            synchronized (lock){
                if (instance==null){
                    instance=new Manager();
                    return instance;
                }
            }
        }
        return instance;
    }
    public AppDatabase getDatabaseHelper(Context context){
        return AppDatabase.getInstance(context);
    }
}
