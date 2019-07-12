package com.example.roomdatabase.view.LessonResults;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.roomdatabase.R;
import com.example.roomdatabase.model.models.StudentGrade;

import java.util.List;

public class LessonResultsAdapter extends RecyclerView.Adapter<LessonResultsAdapter.LessonResultsViewHolder> {
    private Context mContext;
    private List<StudentGrade> gradeList;

    public LessonResultsAdapter(Context mContext,  List<StudentGrade> gradeList) {
        this.mContext = mContext;
        this.gradeList = gradeList;
    }

    @NonNull
    @Override
    public LessonResultsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootView= LayoutInflater.from(mContext).inflate(R.layout.lesson_results_students_item,viewGroup,false);
        return new LessonResultsViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonResultsViewHolder lessonResultsViewHolder, int i) {
        lessonResultsViewHolder.onBind(gradeList.get(i));

    }


    @Override
    public int getItemCount() {
        return gradeList.size();
    }

    class LessonResultsViewHolder extends RecyclerView.ViewHolder{
        private TextView mStudentNameTextView, mStudentDegreeTextView;
        private ImageView mStudentImageView;
        public LessonResultsViewHolder(@NonNull View itemView) {
            super(itemView);
            mStudentNameTextView=itemView.findViewById(R.id.lesson_results_name_student_text_view);
            mStudentDegreeTextView =itemView.findViewById(R.id.lesson_results_degree_student_text_view);
            mStudentImageView=itemView.findViewById(R.id.lesson_results_image_student_image_view);

        }
        void onBind(StudentGrade studentGrade){
            mStudentNameTextView.setText(studentGrade.getName());
            mStudentDegreeTextView.setText(studentGrade.getDegree().replace(" ","/"));
            if (studentGrade.getPhoto()!=null){
                mStudentImageView.setImageBitmap(studentGrade.getPhoto());
            }
        }
    }
}
