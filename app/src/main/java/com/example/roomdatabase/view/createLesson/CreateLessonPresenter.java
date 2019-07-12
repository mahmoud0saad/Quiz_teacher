package com.example.roomdatabase.view.createLesson;

import android.content.Context;
import android.util.Log;

import com.example.roomdatabase.model.AppExecutors;
import com.example.roomdatabase.model.Manager;
import com.example.roomdatabase.model.database.Lesson;
import com.example.roomdatabase.model.database.Questions;
import com.example.roomdatabase.model.database.StudentDao;

import java.util.List;

public class CreateLessonPresenter implements CreateLessonPresenterMvp{
    private Context mContext;
    private CreateLessonViewMvp createLessonView;
    private StudentDao mDatabaseDao;

    public CreateLessonPresenter(Context mContext, CreateLessonViewMvp createLessonView) {
        this.mContext = mContext;
        this.createLessonView = createLessonView;
        mDatabaseDao= Manager.getInstance().getDatabaseHelper(mContext).studentDao();
    }

    @Override
    public void createLesson(final String teacerId, final String lessonName) {

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                Log.d("mano", "run: teacher id insert lesson "+teacerId);
                Lesson lesson=new Lesson(teacerId,lessonName);
                createLessonView.setLessonId((int) mDatabaseDao.insertLesson(lesson));
            }
        });


    }

    @Override
    public void insertAllQuestion(int lessonId, List<Questions> questionsList) {
        for (Questions question:questionsList) {
            question.setLessonId(String.valueOf(lessonId));
            mDatabaseDao.insertQuestion(question);
            Log.d("mano", "insertAllQuestion: "+question.getTheQuestion());
        }
    }
}
