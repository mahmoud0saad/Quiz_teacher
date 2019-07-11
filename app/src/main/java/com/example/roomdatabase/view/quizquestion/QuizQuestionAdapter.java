package com.example.roomdatabase.view.quizquestion;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.roomdatabase.R;
import com.example.roomdatabase.model.database.Questions;

import java.util.ArrayList;
import java.util.List;

public class QuizQuestionAdapter extends RecyclerView.Adapter<QuizQuestionAdapter.QuizViewHolder> {
    private Context mContext;
    private List<Questions> mQuestionsList;
    private List<QuizViewHolder> mQuizViewHolderList;

    public QuizQuestionAdapter(Context mContext, List<Questions> mQuestionsList) {
        this.mContext = mContext;
        this.mQuestionsList = mQuestionsList;
        mQuizViewHolderList =new ArrayList<>();
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView= LayoutInflater.from(mContext).inflate(R.layout.quiz_question_recycler_item,viewGroup,false);
        QuizViewHolder quizViewHolder=new QuizViewHolder(rootView);
        mQuizViewHolderList.add(quizViewHolder);
        return quizViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder quizViewHolder, int i) {
        quizViewHolder.onBind(mQuestionsList.get(i));
    }

    @Override
    public int getItemCount() {
        return mQuestionsList.size();
    }

    public int getFinalResult(){
        int correctAnswer=0;
        String result=""+mQuizViewHolderList.size();
        for (QuizViewHolder quizViewHolder: mQuizViewHolderList) {
            if (quizViewHolder.getAnswerUser()){
                correctAnswer++;
            }
        }

        Log.d("mano", "getFinalResult: the final result "+correctAnswer);
        return correctAnswer;
    }


    class QuizViewHolder extends RecyclerView.ViewHolder{

        private ImageView mQuestionImageView;
        private TextView mQuestionTextView;
        private RadioButton mAnswerQ1RadioButton, mAnswerQ2RadioButton, mAnswerQ3RadioButton, mAnswerQ4RadioButton;
        private RadioGroup mAnswersRadioGroup;
        private int mCorrectResult=-1,mUserAnwser=-1;

        public QuizViewHolder(@NonNull View itemView) {
            super(itemView);

            mQuestionImageView=itemView.findViewById(R.id.quiz_image_question_image_view);
            mQuestionTextView=itemView.findViewById(R.id.quiz_question_name_text_view);
            mAnswerQ1RadioButton =itemView.findViewById(R.id.quiz_answer_one_radio_button);
            mAnswerQ2RadioButton =itemView.findViewById(R.id.quiz_answer_two_radio_button);
            mAnswerQ3RadioButton =itemView.findViewById(R.id.quiz_answer_three_radio_button);
            mAnswerQ4RadioButton =itemView.findViewById(R.id.quiz_answer_four_radio_button);
            mAnswersRadioGroup=itemView.findViewById(R.id.quiz_question_answer_radio_group);

            mAnswersRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    int id=group.getCheckedRadioButtonId();
                    switch (id){
                        case R.id.quiz_answer_one_radio_button:
                            mUserAnwser=1;
                            break;
                        case R.id.quiz_answer_two_radio_button:
                            mUserAnwser=2;
                            break;
                        case R.id.quiz_answer_three_radio_button:
                            mUserAnwser=3;
                            break;
                        case R.id.quiz_answer_four_radio_button:
                            mUserAnwser=4;
                            break;

                    }
                }
            });

        }
        void onBind(Questions question){
            if (question.getQuestionPhotoBitmap()!=null){
                mQuestionImageView.setVisibility(ImageView.VISIBLE);
                mQuestionImageView.setImageBitmap(question.getQuestionPhotoBitmap());
            }else {
                mQuestionImageView.setVisibility(ImageView.GONE);
            }
            mQuestionTextView.setText(question.getTheQuestion());
            mAnswerQ1RadioButton.setText(question.getChooserFirst());
            mAnswerQ2RadioButton.setText(question.getChooserSecond());
            mAnswerQ3RadioButton.setText(question.getChooserThird());
            mAnswerQ4RadioButton.setText(question.getChooserFour());
            mCorrectResult= Integer.valueOf(question.getTheAnswer());

        }
        public boolean getAnswerUser(){
            Log.d("mano", "getAnswerUser: user answer is "+mUserAnwser+"   correct "+mCorrectResult);
            return mUserAnwser==mCorrectResult;
        }
    }
}
