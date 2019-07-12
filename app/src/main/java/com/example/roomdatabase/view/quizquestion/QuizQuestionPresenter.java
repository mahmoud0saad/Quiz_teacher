package com.example.roomdatabase.view.quizquestion;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.roomdatabase.Utils;
import com.example.roomdatabase.model.AppExecutors;
import com.example.roomdatabase.model.Manager;
import com.example.roomdatabase.model.database.Questions;
import com.example.roomdatabase.model.database.ResultAnswerLesson;
import com.example.roomdatabase.model.database.StudentDao;
import com.example.roomdatabase.model.preference.AppPreference;

import java.util.List;
import java.util.Scanner;

public class QuizQuestionPresenter implements QuizQuestionPresenterMvp {
    private Context mContext;
    private QuizQuestionViewMvp mQuizQuestionView;
    private StudentDao mDatabaseDao;
    private AppPreference mAppPreference;

    public QuizQuestionPresenter(Context mContext, QuizQuestionViewMvp mQuizQuestionView) {
        this.mContext = mContext;
        this.mQuizQuestionView = mQuizQuestionView;
        mDatabaseDao= Manager.getInstance().getDatabaseHelper(mContext).studentDao();
        mAppPreference=new AppPreference(mContext);
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

    @Override
    public void checkQuizOrResult(int lessonId) {
        int userId=mAppPreference.getUserId();
        LiveData<ResultAnswerLesson> resultLiveData = mDatabaseDao.getAllStudentResultForlesson(lessonId, userId);
        resultLiveData.observe((LifecycleOwner) mContext, new Observer<ResultAnswerLesson>() {
            @Override
            public void onChanged(@Nullable ResultAnswerLesson resultAnswerLesson) {
                if (resultAnswerLesson==null){
                    Log.d("mano", "onChanged: the result is null");
                    mQuizQuestionView.setUpViewQuiz();
                }else {
                    Log.d("mano", "onChanged: it have reslut");
                    Scanner l=new Scanner(resultAnswerLesson.getResult());
                    int questionCorrect=l.nextInt();
                    int finalQuiz=l.nextInt();
                    mQuizQuestionView.setUpViewResult(questionCorrect,finalQuiz);
                }
            }
        });
    }

    @Override
    public void insertResult(final int lessonId, final String result) {
        Utils.setDialogAlert(mContext,"result","the result is "+result);
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                String  userId= String.valueOf(mAppPreference.getUserId());
                ResultAnswerLesson resultAnswerLesson=new ResultAnswerLesson(userId,String.valueOf(lessonId),result);
                mDatabaseDao.insertResultAnswerLesson(resultAnswerLesson);
                Log.d("mano", "onClick: the result is insert  "+userId);

            }
        });

    }
}
