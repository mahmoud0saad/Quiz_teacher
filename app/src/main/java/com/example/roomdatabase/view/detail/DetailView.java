package com.example.roomdatabase.view.detail;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.roomdatabase.R;
import com.example.roomdatabase.Utils;
import com.example.roomdatabase.model.database.Users;

public class DetailView extends AppCompatActivity implements DetailViewMvp{
    private static String TAG=DetailView.class.getSimpleName();
    private static int PIC_IMGE_REQUEST=12;
    private ImageView mStudentImageView;
    private EditText mStudentNameEditText, mStudentAgeEditText, mStudentYearEditText;
    private FloatingActionButton mCreateFab;
    private  Bitmap mStudentImageBitmap=null;
    private DetatilPresenter presenter;
    private String mActionIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        presenter=new DetatilPresenter(this,this);
        setUp();

        Intent intent=getIntent();
        mActionIntent=intent.getAction();
        presenter.preapareDetailView(intent);
    }

    private void setUp() {
        mStudentImageView=findViewById(R.id.upload_photo_student_image_view);
        mStudentNameEditText =findViewById(R.id.student_name_edit_text);
        mStudentAgeEditText =findViewById(R.id.student_age_edit_text);
        mStudentYearEditText =findViewById(R.id.student_year_edit_text);
        mCreateFab=findViewById(R.id.create_student_fab);

        mCreateFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mStudentNameEditText.getText().toString();
                String age = mStudentAgeEditText.getText().toString();
                String year = mStudentYearEditText.getText().toString();
                Users users =new Users(name, age, year,"","", mStudentImageBitmap);
                if (mActionIntent.equals(getString(R.string.intent_action_add_student))) {
                    presenter.addStudent(users);
                }else if (mActionIntent.equals(getString(R.string.intent_action_update_student))){
                    Log.d(TAG, "onClick: is update");
                    presenter.updateStudent(users);
                }

            }
        });

        mStudentImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.isRequestPermission(DetailView.this);

            }
        });

    }

    @Override
    public void finishedActivity() {
        finish();
    }

    @Override
    public void setIconButtonFab(int idRes) {
        mCreateFab.setImageResource(idRes);
    }

    @Override
    public void putDataStudentToField(Users users) {
        mStudentNameEditText.setText(users.getName());
        mStudentAgeEditText.setText(users.getAge());
        mStudentYearEditText.setText(users.getUserName());
        mStudentImageBitmap= users.getImage();
        mStudentImageView.setImageBitmap(users.getImage());
        Log.d(TAG, "putDataStudentToField:  the id is "+ users.getId());
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

                            mStudentImageView.setImageBitmap(resource);
                            mStudentImageBitmap=resource;
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {
                            mStudentImageView.setImageDrawable(placeholder);
                        }
                    });
//                mStudentImageBitmap= MediaStore.Images.Media.getBitmap(this.getContentResolver(),data.getData());
//                mStudentImageView.setImageBitmap(mStudentImageBitmap);



        }
    }



    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted.
                getImage();
            } else {
                // User refused to grant permission.
            }
        }
    }
    public void getImage(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PIC_IMGE_REQUEST);
    }
}
