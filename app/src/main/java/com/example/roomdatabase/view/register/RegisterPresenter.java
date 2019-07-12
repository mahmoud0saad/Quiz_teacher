package com.example.roomdatabase.view.register;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.roomdatabase.R;
import com.example.roomdatabase.model.AppExecutors;
import com.example.roomdatabase.model.Manager;
import com.example.roomdatabase.model.database.StudentDao;
import com.example.roomdatabase.model.database.Users;
import com.example.roomdatabase.model.preference.AppPreference;
import com.example.roomdatabase.view.login.LoginViewMvp;
import com.example.roomdatabase.view.main.MainView;
import com.example.roomdatabase.view.studentMain.StudentMainView;
import com.example.roomdatabase.view.teacherMain.TeacherMainView;

import java.util.concurrent.ExecutionException;

public class RegisterPresenter implements RegisterPresenterMvp {
    private RegisterView mRegisterView;
    private Context mContext;
    private StudentDao mDatabaseDao;

    public RegisterPresenter(Context mContext,RegisterView mRegisterView) {
        this.mRegisterView = mRegisterView;
        this.mContext = mContext;
        mDatabaseDao= Manager.getInstance().getDatabaseHelper(mContext).studentDao();
    }


    @Override
    public Bitmap getDefaultImage() {
        Bitmap icon = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.default_imge_profile);
        return icon;

    }

    @Override
    public void addUser(final Users user) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        int userId= (int) mDatabaseDao.insertStudent(user);
                        Intent intent;
                        if (user.getStateUser().equals(mContext.getString(R.string.user_is_student_value))){
                            intent= new Intent(mContext, StudentMainView.class);
                        }else {
                            intent= new Intent(mContext, TeacherMainView.class);
                        }
                        intent.putExtra(mContext.getString(R.string.user_id_key),userId);
                        AppPreference appPreference=new AppPreference(mContext);
                        appPreference.setUserId(userId);
                        mContext.startActivity(intent);
                    }
                });


            }
        });
    }

    @Override
    public void isNameFounder(String name) {
        boolean isFound=false;
        mDatabaseDao.checkUsernameFound(name).observe((LifecycleOwner) mContext, new Observer<Users>() {
            @Override
            public void onChanged(@Nullable Users users) {
                if (users!=null){
                    mRegisterView.setIsFound(true);
                }else {
                    mRegisterView.setIsFound(false);
                }
            }
        });

    }
}
