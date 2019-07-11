package com.example.roomdatabase.model.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.roomdatabase.model.database.AppDatabase;
import com.example.roomdatabase.model.database.Users;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private static String TAG=MainViewModel.class.getSimpleName();
    private  LiveData<List<Users>> mListStudentLiveData;

    public MainViewModel(@NonNull Application application) {
        super(application);
        mListStudentLiveData =AppDatabase.getInstance(this.getApplication()).studentDao().getAllStudent();
        Log.d(TAG, "MainViewModel: get dat from data base view model");

    }
    public  LiveData<List<Users>> getAllStudent(){
        return mListStudentLiveData;
    }
}
