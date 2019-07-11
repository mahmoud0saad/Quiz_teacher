package com.example.roomdatabase.view.createLesson;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.roomdatabase.R;
import com.example.roomdatabase.model.database.Questions;

import java.util.List;

public class LessonQuestionAdapter extends RecyclerView.Adapter<LessonQuestionAdapter.LessonQuestionViewHolder> {
    private Context mContext;
    private List<Questions> mQuestionsList;
    private String mTeacherId;

    public LessonQuestionAdapter(Context mContext, List<Questions> mQuestionsList,String teacherId) {
        this.mContext = mContext;
        this.mQuestionsList = mQuestionsList;
        mTeacherId=teacherId;
    }

    @NonNull
    @Override
    public LessonQuestionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView= LayoutInflater.from(mContext).inflate(R.layout.teacher_question_lesson_recycler_item,viewGroup,false);
        LessonQuestionViewHolder lessonQuestionViewHolder=new LessonQuestionViewHolder(rootView);

        return lessonQuestionViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LessonQuestionViewHolder lessonQuestionViewHolder, int i) {
        lessonQuestionViewHolder.onBind(mQuestionsList.get(i));
    }


    @Override
    public int getItemCount() {
        return mQuestionsList.size();
    }

    public List<Questions> getAllQuestion(){

        return mQuestionsList;
    }

    class LessonQuestionViewHolder extends RecyclerView.ViewHolder{

        private ImageView mQuestionImageView;
        private EditText mQuestionEditText,mAnswerQ1EditText,mAnswerQ2EditText,mAnswerQ3EditText,mAnswerQ4EditText,mCorrectAnswerEditText;
        private Bitmap mImgaeBitmap=null;
        int position;
        public LessonQuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            mQuestionImageView=itemView.findViewById(R.id.teacher_image_question_image_view);
            mQuestionEditText=itemView.findViewById(R.id.quiz_question_name_text_view);
            mAnswerQ1EditText=itemView.findViewById(R.id.teachser_answer_q1_edit_text);
            mAnswerQ2EditText=itemView.findViewById(R.id.teachser_answer_q2_edit_text);
            mAnswerQ3EditText=itemView.findViewById(R.id.teachser_answer_q3_edit_text);
            mAnswerQ4EditText=itemView.findViewById(R.id.teachser_answer_q4_edit_text);
            mCorrectAnswerEditText=itemView.findViewById(R.id.teacher_correct_answer_edit_text);


            mQuestionImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("mnao", "onClick: imgae click ");
                    position=getAdapterPosition();
                    CreateLessonView createLessonView= (CreateLessonView) mContext;
                    createLessonView.getImgaeFromGallery(LessonQuestionViewHolder.this);
                }
            });
            mQuestionEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d("mano", "beforeTextChanged: "+s);
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d("mano", "onTextChanged: "+s);
                }

                @Override
                public void afterTextChanged(Editable s) {
                    mQuestionsList.get(getAdapterPosition()).setTheQuestion(s.toString());
                }
            });

            mAnswerQ1EditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d("mano", "beforeTextChanged: "+s);
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d("mano", "onTextChanged: "+s);
                }

                @Override
                public void afterTextChanged(Editable s) {
                    mQuestionsList.get(getAdapterPosition()).setChooserFirst(s.toString());
                }
            });

            mAnswerQ2EditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d("mano", "beforeTextChanged: "+s);
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d("mano", "onTextChanged: "+s);
                }

                @Override
                public void afterTextChanged(Editable s) {
                    mQuestionsList.get(getAdapterPosition()).setChooserSecond(s.toString());
                }
            });
            mAnswerQ3EditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d("mano", "beforeTextChanged: "+s);
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d("mano", "onTextChanged: "+s);
                }

                @Override
                public void afterTextChanged(Editable s) {
                    mQuestionsList.get(getAdapterPosition()).setChooserThird(s.toString());
                }
            });
            mAnswerQ4EditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d("mano", "beforeTextChanged: "+s);
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d("mano", "onTextChanged: "+s);
                }

                @Override
                public void afterTextChanged(Editable s) {
                    mQuestionsList.get(getAdapterPosition()).setChooserFour(s.toString());
                }
            });


            mCorrectAnswerEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.d("mano", "beforeTextChanged: "+s);
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d("mano", "onTextChanged: "+s);
                }

                @Override
                public void afterTextChanged(Editable s) {
                    mQuestionsList.get(getAdapterPosition()).setTheAnswer(s.toString());
                }
            });







        }
        public void setImageBitmap(Bitmap imageBitmap){
            mImgaeBitmap=imageBitmap;
            mQuestionImageView.setImageBitmap(imageBitmap);
            mQuestionsList.get(position).setQuestionPhotoBitmap(imageBitmap);
        }
        Questions getQuestion(){

            String questionName=mQuestionEditText.getText().toString();
            String answerQ1=mAnswerQ1EditText.getText().toString();
            String answerQ2=mAnswerQ2EditText.getText().toString();
            String answerQ3=mAnswerQ3EditText.getText().toString();
            String answerQ4=mAnswerQ4EditText.getText().toString();
            int correctAnswerId= Integer.valueOf(mCorrectAnswerEditText.getText().toString());

            Questions questions=new Questions(mTeacherId,questionName,answerQ1,answerQ2,answerQ3,answerQ4,String.valueOf(correctAnswerId),mImgaeBitmap);
            return questions;

        }

        void onBind(Questions questions){
            mQuestionEditText.setText(questions.getTheQuestion());
            if (questions.getQuestionPhotoBitmap()!=null) {
                mQuestionImageView.setImageBitmap(questions.getQuestionPhotoBitmap());
            }
            mAnswerQ1EditText.setText(questions.getChooserFirst());
            mAnswerQ2EditText.setText(questions.getChooserSecond());
            mAnswerQ3EditText.setText(questions.getChooserThird());
            mAnswerQ4EditText.setText(questions.getChooserFour());
            mCorrectAnswerEditText.setText(questions.getTheAnswer());

        }
    }

}
