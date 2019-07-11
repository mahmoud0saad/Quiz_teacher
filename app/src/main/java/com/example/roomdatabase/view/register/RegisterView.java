package com.example.roomdatabase.view.register;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.roomdatabase.R;
import com.example.roomdatabase.Utils;
import com.example.roomdatabase.model.database.Users;

public class RegisterView extends AppCompatActivity implements RegisterViewMvp {
    private static String TAG=RegisterView.class.getSimpleName();
    private RegisterPresenter presenter;
    private Button mRegisterButton;
    private EditText mNameEditText,mUsernameEditText,mPasswordEditText,mAgeEditText;
    private Switch mTeacherSwitch;
    private ImageView mImageView;
    private int PIC_IMGE_REQUEST=12;
    private Bitmap mUserPhotoBitmap;
    private boolean isNameFoundBefore=false;

    private boolean isSwitchActive=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_view);

        mRegisterButton=findViewById(R.id.register_register_button);
        mNameEditText=findViewById(R.id.register_name_edit_text);
        mUsernameEditText=findViewById(R.id.register_username_edit_text);
        mPasswordEditText=findViewById(R.id.register_password_edit_text);
        mAgeEditText=findViewById(R.id.register_age_edit_text);
        mTeacherSwitch=findViewById(R.id.register_teacher_switch);
        mImageView=findViewById(R.id.register_upload_image);
        presenter=new RegisterPresenter(this,this);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utils.isRequestPermission(RegisterView.this)) {
                    getImage();
                }
            }
        });

        mTeacherSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
             isSwitchActive=isChecked;
            }
        });

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (mUserPhotoBitmap==null){
                    mUserPhotoBitmap=presenter.getDefaultImage();
                }

                String name=mNameEditText.getText().toString();
                String username=mUsernameEditText.getText().toString();
                String age=mAgeEditText.getText().toString();
                String password=mPasswordEditText.getText().toString();
                String stateUser=getString(R.string.user_is_student_value);

                if (isNameFoundBefore){
                    Utils.setDialogAlert(RegisterView.this,"username ", "username is found before");
                    return;
                }
                if (isSwitchActive){
                    stateUser=getString(R.string.user_is_teacher_value);
                    Toast.makeText(RegisterView.this, "is activate", Toast.LENGTH_SHORT).show();
                }

                Users user=new Users(name,age,username,password,stateUser,mUserPhotoBitmap);

                presenter.addUser(user);
            }
        });

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

                            mImageView.setImageBitmap(resource);
                            mUserPhotoBitmap =resource;
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {
                            mImageView.setImageDrawable(placeholder);
                        }
                    });
//                mStudentImageBitmap= MediaStore.Images.Media.getBitmap(this.getContentResolver(),data.getData());
//                mStudentImageView.setImageBitmap(mStudentImageBitmap);



        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
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

    private void getImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PIC_IMGE_REQUEST);
    }


    @Override
    public void setIsFound(boolean isFound) {
        this.isNameFoundBefore=isFound;
    }
}
