package com.example.roomdatabase.model.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.roomdatabase.model.database.AppDatabase;
import com.example.roomdatabase.model.database.Users;

import java.util.List;

public class StudentLimitViewModel extends ViewModel {
   private LiveData<List<Users>> mListLiveData;
    public StudentLimitViewModel(AppDatabase appDatabase,int first,int count){
        mListLiveData=appDatabase.studentDao().getstudentBetween(first,count);
    }

    public LiveData<List<Users>> getsStudent(){
        return mListLiveData;
    }

}

