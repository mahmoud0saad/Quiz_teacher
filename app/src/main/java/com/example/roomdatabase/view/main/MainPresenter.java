package com.example.roomdatabase.view.main;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.roomdatabase.model.AppExecutors;
import com.example.roomdatabase.model.Manager;
import com.example.roomdatabase.model.ViewModel.MainViewModel;
import com.example.roomdatabase.model.ViewModel.StudentLimitFactory;
import com.example.roomdatabase.model.ViewModel.StudentLimitViewModel;
import com.example.roomdatabase.model.database.AppDatabase;
import com.example.roomdatabase.model.database.Users;

import java.util.List;

public class MainPresenter implements MainPresenterMVP{
    private static String TAG=MainPresenter.class.getSimpleName();
    private MainViewMvp mMainView;
    private Context mContext;
    private Manager mManager;
    public MainPresenter(Context context,MainViewMvp mainViewMvp) {
        mContext=context;
        mMainView=mainViewMvp;
        mManager=Manager.getInstance();
    }



    @Override
    public void refreshAllData() {
        MainViewModel mainViewModel= ViewModelProviders.of((FragmentActivity) mContext).get(MainViewModel.class);
        mainViewModel.getAllStudent().observe((LifecycleOwner) mContext, new Observer<List<Users>>() {
           @Override
           public void onChanged(@Nullable List<Users> users) {
               mMainView.clearAllStudent();
               Log.d(TAG, "onChanged: "+"is in database refresh data view model"+ users.get(0).getId());
               mMainView.addListStudent(users);
           }
       });
    }

    @Override
    public void refreshLimitData(int first, int count) {
        final StudentLimitFactory factory=new StudentLimitFactory(mManager.getDatabaseHelper(mContext),first,count);
        StudentLimitViewModel viewModel=ViewModelProviders.of((FragmentActivity) mContext,factory).get(StudentLimitViewModel.class);
        final LiveData<List<Users>> listLiveData = mManager.getDatabaseHelper(mContext).studentDao().getstudentBetween(first,count);
        listLiveData.observe((LifecycleOwner) mContext, new Observer<List<Users>>() {
            @Override
            public void onChanged(@Nullable List<Users> users) {
                listLiveData.removeObserver(this);
                mMainView.addListStudent(users);
            }
        });

    }

    @Override
    public void deleteStudent(final Users users) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase.getInstance(mContext).studentDao().deleteStudent(users);
            }
        });
    }
}
