package com.example.roomdatabase.view.studentMain;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;

import com.example.roomdatabase.model.Manager;
import com.example.roomdatabase.model.database.Lesson;
import com.example.roomdatabase.model.database.StudentDao;

import java.util.List;

public class StudentMainPresenter implements StudentMainPresenterMvp{
    private Context mContext;
    private StudentMainViewMvp mStudentMainView;
    private StudentDao mDatabaseDao;

    public StudentMainPresenter(Context mContext, StudentMainViewMvp mStudentMainView) {
        this.mContext = mContext;
        this.mStudentMainView = mStudentMainView;
        mDatabaseDao= Manager.getInstance().getDatabaseHelper(mContext).studentDao();
    }

    @Override
    public void getAllLesson() {
        LiveData<List<Lesson>> lessonLive = mDatabaseDao.getAllLessonForStudent();
        lessonLive.observe((LifecycleOwner) mContext, new Observer<List<Lesson>>() {
            @Override
            public void onChanged(@Nullable List<Lesson> lessons) {
                mStudentMainView.setLessonToRecycler(lessons);
            }
        });

    }
}
