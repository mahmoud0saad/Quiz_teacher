package com.example.roomdatabase.view.teacherMain;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.roomdatabase.R;
import com.example.roomdatabase.model.AppExecutors;
import com.example.roomdatabase.model.Manager;
import com.example.roomdatabase.model.database.Lesson;
import com.example.roomdatabase.model.database.StudentDao;
import com.example.roomdatabase.view.createLesson.CreateLessonPresenter;
import com.example.roomdatabase.view.createLesson.CreateLessonView;

import java.util.List;

public class TeacherMainPresenter  implements TeacherMainPresenterMvp{
    private Context mContext;
    private TeacherMainViewMvp mTeacherView;
    private StudentDao mDatabaseDao;

    public TeacherMainPresenter(Context mContext, TeacherMainViewMvp mTeacherView) {
        this.mContext = mContext;
        this.mTeacherView = mTeacherView;
        mDatabaseDao= Manager.getInstance().getDatabaseHelper(mContext).studentDao();
    }

    @Override
    public void getLessonTeacher(int userId) {
        LiveData<List<Lesson>> lessonLive = mDatabaseDao.getAllLessonForTeacher(userId);
        lessonLive.observe((LifecycleOwner) mContext, new Observer<List<Lesson>>() {
            @Override
            public void onChanged(@Nullable List<Lesson> lessons) {
                mTeacherView.setAllLesson(lessons);
            }
        });

    }

    @Override
    public void insertLesson(final int teacherid) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(mContext, CreateLessonView.class);
                intent.putExtra(mContext.getString(R.string.create_lesson_teacher_id_key),String.valueOf(teacherid));
                mContext.startActivity(intent);
            }
        });
    }
}
