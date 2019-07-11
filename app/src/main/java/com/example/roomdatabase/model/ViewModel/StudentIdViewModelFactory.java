package com.example.roomdatabase.model.ViewModel;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.roomdatabase.model.database.AppDatabase;

public class StudentIdViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private AppDatabase mAppDatabase;
    private int mStudentId;

    public StudentIdViewModelFactory(AppDatabase appDatabase , int studentId) {
        mAppDatabase=appDatabase;
        mStudentId=studentId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LoadStudentIdViewModel(mAppDatabase,mStudentId);
    }
}
