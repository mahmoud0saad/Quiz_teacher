package com.example.roomdatabase.view.teacherMain;

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

import java.util.ArrayList;
import java.util.List;

public class TeacherMainView extends AppCompatActivity  implements TeacherMainViewMvp{
    private static String TAG=TeacherMainView.class.getSimpleName();
    private RecyclerView mLessonRecyclerView;
    private FloatingActionButton mAddLessonFab;
    private TeacherMainPresenter presenter;
    private List<Lesson> mLessonList;
    private LessonAdapter mLessonAdapter;
    public static int USER_ID;
    private RelativeLayout mTeacherRelativeLayout,mStudentRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_view);

        mLessonRecyclerView=findViewById(R.id.teacher_lesson_recycler_view);
        mAddLessonFab=findViewById(R.id.teacher_add_lesson_fab_button);
        mTeacherRelativeLayout=findViewById(R.id.container_teacher_control);
        mStudentRelativeLayout=findViewById(R.id.container_student_control);

        Intent intent=getIntent();
        if (intent!=null){
            USER_ID=intent.getIntExtra(getString(R.string.user_id_key),-1);
            mTeacherRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
            mStudentRelativeLayout.setVisibility(RelativeLayout.GONE);
        }else{
            Toast.makeText(this, "no id for user", Toast.LENGTH_SHORT).show();
        }
        presenter =new TeacherMainPresenter(this,this);



        setUpRecycler();

        presenter.getLessonTeacher(USER_ID);

        mAddLessonFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.insertLesson(USER_ID);
            }
        });

    }

    private void setUpRecycler() {
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mLessonList =new ArrayList<>();
        mLessonAdapter=new LessonAdapter(this, mLessonList,true);

        mLessonRecyclerView.setAdapter(mLessonAdapter);
        mLessonRecyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public void setAllLesson(List<Lesson> lessonList) {
        mLessonList.clear();
        mLessonList.addAll(lessonList);
        mLessonAdapter.notifyDataSetChanged();
    }
}
