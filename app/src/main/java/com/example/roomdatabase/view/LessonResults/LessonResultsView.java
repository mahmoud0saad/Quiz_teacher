package com.example.roomdatabase.view.LessonResults;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.roomdatabase.R;
import com.example.roomdatabase.model.database.ResultAnswerLesson;
import com.example.roomdatabase.model.models.StudentGrade;
import com.example.roomdatabase.view.quizquestion.QuizQuestionPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LessonResultsView extends AppCompatActivity implements LessonResultViewMvp {
    private static String TAG= LessonResultsView.class.getSimpleName();
    private LessonResultPresenter presenter;
    private static int LESSON_ID;
    private static String  LESSON_NAME;
    private RecyclerView mResultsRecyclerView;
    private LessonResultsAdapter mResultsAdapter;
    private List<StudentGrade> mGradeList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results_view);
        mResultsRecyclerView=findViewById(R.id.quiz_lesson_result_recycler_view);

        presenter=new LessonResultPresenter(this,this);

        Intent intent=getIntent();
        if (intent!=null){
            if (intent.hasExtra(getString(R.string.intent_quiz_lesson_id_key))){
                String lessson=intent.getStringExtra(getString(R.string.intent_quiz_lesson_id_key));
                LESSON_NAME=intent.getStringExtra(getString(R.string.intent_quiz_lesson_name_key));
                Log.d(TAG, "onCreate: "+lessson);
                LESSON_ID=Integer.valueOf(lessson );
            }else {
                Toast.makeText(this, "not have intetnt ", Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(this, "no id lesson", Toast.LENGTH_SHORT).show();
        }

        setUpRecycler();

        presenter.getAllResultsForLesson(LESSON_ID);


    }

    private void setUpRecycler() {
        mGradeList=new ArrayList<>();

        mResultsAdapter=new LessonResultsAdapter(this,mGradeList);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        mResultsRecyclerView.setAdapter(mResultsAdapter);
        mResultsRecyclerView.setLayoutManager(linearLayoutManager);


    }



    @Override
    public void setOnlyResultToRecycler(StudentGrade studentGrade) {
        mGradeList.add(studentGrade);
        mResultsAdapter.notifyDataSetChanged();
    }


}
