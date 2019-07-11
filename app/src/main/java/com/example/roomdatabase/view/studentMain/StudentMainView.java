package com.example.roomdatabase.view.studentMain;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.roomdatabase.R;
import com.example.roomdatabase.model.database.Lesson;
import com.example.roomdatabase.view.teacherMain.LessonAdapter;
import com.example.roomdatabase.view.teacherMain.TeacherMainPresenter;
import com.example.roomdatabase.view.teacherMain.TeacherMainView;

import java.util.ArrayList;
import java.util.List;

public class StudentMainView extends AppCompatActivity implements StudentMainViewMvp{
    private static String TAG= StudentMainView.class.getSimpleName();
    private RecyclerView mLessonRecyclerView;;
    private List<Lesson> mLessonList;
    private LessonAdapter mLessonAdapter;
    public static int USER_ID;
    private RelativeLayout mTeacherRelativeLayout,mStudentRelativeLayout;
    private StudentMainPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_view);

        mTeacherRelativeLayout = findViewById(R.id.container_teacher_control);
        mStudentRelativeLayout=findViewById(R.id.container_student_control);
        mLessonRecyclerView=findViewById(R.id.teacher_lesson_recycler_view);



        presenter=new StudentMainPresenter(this,this);

        Intent intent=getIntent();
        if (intent!=null){
            USER_ID=intent.getIntExtra(getString(R.string.user_id_key),-1);
            mTeacherRelativeLayout.setVisibility(RelativeLayout.GONE);
            mStudentRelativeLayout.setVisibility(RelativeLayout.GONE);
        }else{
            Toast.makeText(this, "no id for user", Toast.LENGTH_SHORT).show();
        }

        setUpRecycler();
        presenter.getAllLesson();



    }

    private void setUpRecycler() {
        mLessonList=new ArrayList<>();
        mLessonAdapter=new LessonAdapter(this,mLessonList,false);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mLessonRecyclerView.setAdapter(mLessonAdapter);
        mLessonRecyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void setLessonToRecycler(List<Lesson> lessonList) {
        mLessonList.clear();
        mLessonList.addAll(lessonList);
        mLessonAdapter.notifyDataSetChanged();
    }
}
