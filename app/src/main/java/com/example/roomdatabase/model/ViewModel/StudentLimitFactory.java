package com.example.roomdatabase.model.ViewModel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.roomdatabase.model.database.AppDatabase;

public class StudentLimitFactory extends ViewModelProvider.NewInstanceFactory{
    private int mFirst,mLast;
    private AppDatabase mAppDatabase;
    public StudentLimitFactory(AppDatabase appDatabase,int first,int last) {
        mFirst=first;
        mLast=last;
        mAppDatabase=appDatabase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new StudentLimitViewModel(mAppDatabase,mFirst,mLast);
    }
}
