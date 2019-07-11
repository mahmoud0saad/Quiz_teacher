package com.example.roomdatabase.view.createLesson;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.roomdatabase.R;
import com.example.roomdatabase.Utils;
import com.example.roomdatabase.model.database.Questions;
import com.example.roomdatabase.view.teacherMain.TeacherMainView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CreateLessonView extends AppCompatActivity  implements CreateLessonViewMvp  {
    private static final int PIC_IMGE_REQUEST = 22;
    private static String TAG=CreateLessonView.class.getSimpleName();
    private CreateLessonPresenter presenter;

    private EditText mNameLessonEditText;
    private RecyclerView mQuestionsRecyclerView;
    private Button mCreateLessonButton;
    private String mTeacherId;
    private List<Questions> mQuestionsList;
    private LessonQuestionAdapter mLessonQuestionAdapter;
    private int mLessonId=-1;
    private Utils.CustomLinearLayoutManager mLinearLayoutManager;
    private int mCount=1;
    private LessonQuestionAdapter.LessonQuestionViewHolder mContextViewHolder;
    private ImageView mNextItemImageView, mBackItemImageView,mRemoveQuestionImageView,mAddQuestionImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_lesson_view);

        Intent intent=getIntent();
        if(intent!=null){
            mTeacherId=intent.getStringExtra(getString(R.string.create_lesson_teacher_id_key));
        }else{
            Toast.makeText(this, "no teacher id ", Toast.LENGTH_SHORT).show();
        }

        presenter=new CreateLessonPresenter(this,this);



        mNameLessonEditText=findViewById(R.id.create_name_lesson_edittext);
        mQuestionsRecyclerView=findViewById(R.id.create_lesson_recycler_view);
        mNextItemImageView =findViewById(R.id.create_lesson_next_imageview);
        mBackItemImageView =findViewById(R.id.create_lesson_back_imageview);
        mRemoveQuestionImageView =findViewById(R.id.create_remover_question_image_view);
        mAddQuestionImageView =findViewById(R.id.create_add_question_image_view);
        mCreateLessonButton=findViewById(R.id.create_lesson_create_button);

        setUpRecycler();

        mCreateLessonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                presenter.createLesson(mTeacherId,mNameLessonEditText.getText().toString());

            }
        });

        mBackItemImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x=mLinearLayoutManager.findLastVisibleItemPosition();
                if (x!=0)
                    mLinearLayoutManager.scrollToPosition(x-1);

            }
        });

        mNextItemImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("mano", "onClick: "+mLinearLayoutManager.findLastVisibleItemPosition());

                int x=mLinearLayoutManager.findLastVisibleItemPosition();
                if (x!=mQuestionsList.size())
                mLinearLayoutManager.scrollToPosition(x+1);



            }
        });

        mAddQuestionImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Questions questions=new Questions();

                questions.setQuestionId(mCount);
                mCount++;

                mQuestionsList.add(questions);
                arragentest();

                Log.d(TAG, "onClick: compare "+questions.getQuestionId());

            }
        });
        mRemoveQuestionImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x=mLinearLayoutManager.findLastVisibleItemPosition();
                mQuestionsList.remove(x);
                arragentest();
            }
        });


    }

    private void setUpRecycler() {
        mQuestionsList=new ArrayList<>();



        Questions questions=new Questions();

        questions.setQuestionId(0);


        questions.setTheQuestion("1");


        mCount++;
        mQuestionsList.add(questions);

        mLessonQuestionAdapter=new LessonQuestionAdapter(this,mQuestionsList,mTeacherId);
        arragentest();

         mLinearLayoutManager=new Utils.CustomLinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mQuestionsRecyclerView.setAdapter(mLessonQuestionAdapter);
        mQuestionsRecyclerView.setLayoutManager(mLinearLayoutManager);
        mQuestionsRecyclerView.setNestedScrollingEnabled(false);

    }

    @Override
    public void setLessonId(int lessonId) {
        mLessonId=lessonId;
        Log.d(TAG, "setLessonId: "+mLessonId);
        presenter.insertAllQuestion(mLessonId, mLessonQuestionAdapter.getAllQuestion());
        Intent intent=new Intent(this, TeacherMainView.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.putExtra(String.valueOf(R.string.create_lesson_teacher_id_key),mTeacherId);
        startActivity(intent);
    }

    public void getImgaeFromGallery(LessonQuestionAdapter.LessonQuestionViewHolder context){
        mContextViewHolder=context;
        getImage();

    }

    private void getImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PIC_IMGE_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK&&requestCode==PIC_IMGE_REQUEST){

            Glide.with(this)
                    .asBitmap()
                    .load(data.getData())
                    .override(200, 200)
                    .into(new CustomTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {

                          mContextViewHolder.setImageBitmap(resource);
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {
                        }
                    });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void arragentest(){
        Collections.sort(mQuestionsList, new Comparator<Questions>() {
            @Override
            public int compare(Questions o1, Questions o2) {
                return o1.getQuestionId()- o2.getQuestionId();
            }
        });
        mLessonQuestionAdapter.notifyDataSetChanged();
        Log.d(TAG, "onClick:                                             f");
        for (Questions q :mQuestionsList) {
            Log.d(TAG, "onClick: question id "+"   "+q.getQuestionId());

        }
    }



}
