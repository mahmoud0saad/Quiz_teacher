package com.example.roomdatabase.view.LessonResults;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.roomdatabase.model.Manager;
import com.example.roomdatabase.model.database.ResultAnswerLesson;
import com.example.roomdatabase.model.database.StudentDao;
import com.example.roomdatabase.model.database.Users;
import com.example.roomdatabase.model.models.StudentGrade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LessonResultPresenter implements LessonResultPresenterMvp {
    private Context mContext;
    private LessonResultViewMvp mLessonResultView;
    private StudentDao mDatabaseDao;

    public LessonResultPresenter(Context mContext, LessonResultViewMvp mLessonResultView) {
        this.mContext = mContext;
        this.mLessonResultView = mLessonResultView;
        mDatabaseDao= Manager.getInstance().getDatabaseHelper(mContext).studentDao();
    }

    @Override
    public void getAllResultsForLesson(int lessonId) {
        mDatabaseDao.getAllResultForLesson(String.valueOf(lessonId)).observe((LifecycleOwner) mContext, new Observer<List<ResultAnswerLesson>>() {
            @Override
            public void onChanged(@Nullable List<ResultAnswerLesson> resultAnswerLessons) {

                for (ResultAnswerLesson resultStudentLesson:resultAnswerLessons) {

                    int userId= Integer.valueOf(resultStudentLesson.getStudentId());
                    final String result=resultStudentLesson.getResult();
                    Log.d("mano", "onChanged: the fddffddfddf   "+result+"   "+userId );
                    final LiveData<Users> studentLive = mDatabaseDao.getStudentById(userId);

                    studentLive.observe((LifecycleOwner) mContext, new Observer<Users>() {
                        @Override
                        public void onChanged(@Nullable Users users) {
                            if (users!=null) {
                                studentLive.removeObserver(this);
                                StudentGrade studentGrade = new StudentGrade();
                                studentGrade.setName(users.getName());
                                studentGrade.setDegree(result);
                                if (users.getImage() != null) {
                                    studentGrade.setPhoto(users.getImage());
                                }
                                Log.d("mano", "onChanged: the user add result " + result + "  name" + users.getName());
                                mLessonResultView.setOnlyResultToRecycler(studentGrade);
                            }
                        }
                    });

                }
            }
        });
    }


}
