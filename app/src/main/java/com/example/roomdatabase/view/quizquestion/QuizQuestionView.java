package com.example.roomdatabase.view.quizquestion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roomdatabase.R;
import com.example.roomdatabase.Utils;
import com.example.roomdatabase.model.database.Questions;
import com.example.roomdatabase.model.database.ResultAnswerLesson;

import java.util.ArrayList;
import java.util.List;

public class QuizQuestionView extends AppCompatActivity implements QuizQuestionViewMvp{
    private static String TAG=QuizQuestionView.class.getSimpleName();
    private QuizQuestionPresenter presenter;
    private static int LESSON_ID;
    private static String  LESSON_NAME;
    private RecyclerView mQuizQuestionRecyclerView;
    private ImageView mNextQuestionImagmeView,mbackQuestionImagmeView;
    private List<Questions> mQuestionsList;
    private QuizQuestionAdapter mQuizAdapter;
    private Button mQuizFinishButton;
    private Utils.CustomLinearLayoutManager mLinearLayoutManager;
    private RelativeLayout mQuizRelativeLayout,mResultRelativeLayout;
    private TextView mQuizeLessonNameTextView,mQuizResultLessonNameTextView,mQuizResultValueTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_question_view);

        presenter=new QuizQuestionPresenter(this,this);

        mQuizQuestionRecyclerView=findViewById(R.id.quiz_lesson_recycler_view);
        mNextQuestionImagmeView=findViewById(R.id.quiz_lesson_next_imageview);
        mbackQuestionImagmeView=findViewById(R.id.quiz_lesson_back_imageview);
        mQuizFinishButton=findViewById(R.id.quiz_lesson_finish_button);
        mQuizRelativeLayout=findViewById(R.id.quiz_question_relative_layout);
        mResultRelativeLayout=findViewById(R.id.quiz_result_relative_layout);
        mQuizeLessonNameTextView=findViewById(R.id.quiz_name_lesson_text_view);
        mQuizResultLessonNameTextView=findViewById(R.id.quiz_result_lesson_name_text_view);
        mQuizResultValueTextView=findViewById(R.id.quiz_result_value_text_view);


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


        //check if user is test this exam before or not
        presenter.checkQuizOrResult(LESSON_ID);




        mQuizFinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result=mQuizAdapter.getFinalResult()+" "+mQuestionsList.size();
                presenter.insertResult(LESSON_ID,result);
            }
        });

        mNextQuestionImagmeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("mano", "onClick: "+mLinearLayoutManager.findLastVisibleItemPosition()+"    "+mQuestionsList.size());

                int x=mLinearLayoutManager.findLastVisibleItemPosition();
                if (x!=mQuestionsList.size()) {
                    mLinearLayoutManager.scrollToPosition( x + 1);
                }
            }
        });
        mbackQuestionImagmeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x=mLinearLayoutManager.findLastVisibleItemPosition();
                if (x!=0)
                    mLinearLayoutManager.scrollToPosition(x-1);

            }
        });




    }

    private void setUpRecycler() {

        mQuizeLessonNameTextView.setText(LESSON_NAME);

        mQuestionsList =new ArrayList<>();
         mLinearLayoutManager=new Utils.CustomLinearLayoutManager(this);
        mQuizAdapter=new QuizQuestionAdapter(this, mQuestionsList);
        mLinearLayoutManager.setOrientation(LinearLayout.HORIZONTAL);

        mQuizQuestionRecyclerView.setLayoutManager(mLinearLayoutManager);
        mQuizQuestionRecyclerView.setAdapter(mQuizAdapter);
    }


    @Override
    public void setAllQuestion(List<Questions> questionsList) {
        mQuestionsList.clear();
        mQuestionsList.addAll(questionsList);
        mQuizAdapter.notifyDataSetChanged();
    }

    @Override
    public void setUpViewQuiz() {
        setUpRecycler();
        presenter.getAllQuestionForLesson(LESSON_ID);
        mQuizRelativeLayout.setVisibility(View.VISIBLE);
        mResultRelativeLayout.setVisibility(View.GONE);
    }

    @Override
    public void setUpViewResult(int correctQuestion, int finalDegreeQuiz) {
        mQuizResultLessonNameTextView.setText(LESSON_NAME);
        mQuizResultValueTextView.setText("Your score : "+correctQuestion+" / "+finalDegreeQuiz+"\n Good luck");
        mQuizRelativeLayout.setVisibility(View.GONE);
        mResultRelativeLayout.setVisibility(View.VISIBLE);
    }

}