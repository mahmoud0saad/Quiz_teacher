package com.example.roomdatabase.model.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.roomdatabase.model.database.AppDatabase;
import com.example.roomdatabase.model.database.Users;

public class LoadStudentIdViewModel extends ViewModel {

    private LiveData<Users> mLiveDataStudent;

    public LoadStudentIdViewModel(AppDatabase appDatabase, int studentId) {
       mLiveDataStudent=appDatabase.studentDao().loadStudentById(studentId);
    }

    public LiveData<Users> getStudent(){
        return mLiveDataStudent;
    }

}
