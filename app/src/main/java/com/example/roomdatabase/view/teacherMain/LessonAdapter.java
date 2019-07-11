package com.example.roomdatabase.view.teacherMain;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.roomdatabase.R;
import com.example.roomdatabase.model.database.Lesson;
import com.example.roomdatabase.view.quizquestion.QuizQuestionView;

import java.util.List;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.LessonViewHolder> {
    private Context mContext;
    private List<Lesson> lessonList;
    private boolean isTeacher=true;

    public LessonAdapter(Context mContext, List<Lesson> lessonList,boolean isTeacher) {
        this.mContext = mContext;
        this.lessonList=lessonList;
        this.isTeacher=isTeacher;
    }

    @NonNull
    @Override
    public LessonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView= LayoutInflater.from(mContext).inflate(R.layout.teacher_lesson_recycler_item,viewGroup,false);
        return new LessonViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonViewHolder lessonViewHolder, int i) {
            Lesson lesson=lessonList.get(i);
            lessonViewHolder.onBind(lesson.getLessonName(),lesson.getLessonId());
    }

    @Override
    public int getItemCount() {
        return lessonList.size();
    }

    class LessonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextView;
        public LessonViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView=itemView.findViewById(R.id.teacher_recycler_lesson_name_text_view);

            mTextView.setOnClickListener(this);
        }
        public void onBind(String name,int lessonId){
            mTextView.setText(name);
            mTextView.setTag(lessonId);
        }

        @Override
        public void onClick(View v) {
            //get result for all student it solve it lesson
            int lessonId=(int)mTextView.getTag();
            Log.d("mano", "onClick: "+lessonId);


                //student
                Intent intent=new Intent(mContext, QuizQuestionView.class);
                intent.putExtra(mContext.getString(R.string.intent_quiz_lesson_id_key),String.valueOf(lessonId));
                mContext.startActivity(intent);


        }
    }
}
