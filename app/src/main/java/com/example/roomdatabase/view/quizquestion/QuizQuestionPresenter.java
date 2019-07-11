package com.example.roomdatabase.view.quizquestion;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;

import com.example.roomdatabase.model.Manager;
import com.example.roomdatabase.model.database.Questions;
import com.example.roomdatabase.model.database.StudentDao;

import java.util.List;

public class QuizQuestionPresenter implements QuizQuestionPresenterMvp {
    private Context mContext;
    private QuizQuestionViewMvp mQuizQuestionView;
    private StudentDao mDatabaseDao;

    public QuizQuestionPresenter(Context mContext, QuizQuestionViewMvp mQuizQuestionView) {
        this.mContext = mContext;
        this.mQuizQuestionView = mQuizQuestionView;
        mDatabaseDao= Manager.getInstance().getDatabaseHelper(mContext).studentDao();
    }

    @Override
    public void getAllQuestionForLesson(int lessonId) {
        final LiveData<List<Questions>> questionLive = mDatabaseDao.getAllQuestionForLesson(lessonId);
        questionLive.observe((LifecycleOwner) mContext, new Observer<List<Questions>>() {
            @Override
            public void onChanged(@Nullable List<Questions> questions) {
                questionLive.removeObserver(this);
                mQuizQuestionView.setAllQuestion(questions);
            }
        });
    }
}
