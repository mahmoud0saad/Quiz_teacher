package com.example.roomdatabase.view.login;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.roomdatabase.R;
import com.example.roomdatabase.model.Manager;
import com.example.roomdatabase.model.database.AppDatabase;
import com.example.roomdatabase.model.database.StudentDao;
import com.example.roomdatabase.model.database.Users;
import com.example.roomdatabase.view.main.MainView;
import com.example.roomdatabase.view.register.RegisterView;
import com.example.roomdatabase.view.studentMain.StudentMainView;
import com.example.roomdatabase.view.teacherMain.TeacherMainView;

public class LoginPresenter  implements  LoginPresenterMvp{
    private LoginViewMvp mLoginView;
    private Context mContext;
    private StudentDao mDatabaseDao;

    public LoginPresenter(Context mContext,LoginViewMvp mLoginView) {
        this.mLoginView = mLoginView;
        this.mContext = mContext;
        mDatabaseDao= Manager.getInstance().getDatabaseHelper(mContext).studentDao();
    }


    @Override
    public void loginButtonClick(String username, String password) {
        if (username==null||username.isEmpty()||password==null||password.isEmpty()){
            return;
        }
        final LiveData<Users> userLive = mDatabaseDao.checkUserAllowed(username, password);
        userLive.observe((LifecycleOwner) mContext, new Observer<Users>() {
            @Override
            public void onChanged(@Nullable Users users) {
                userLive.removeObserver(this);
                if (users!=null){
                    Toast.makeText(mContext, "login pass", Toast.LENGTH_SHORT).show();
                    Intent intent;
                    if (users.getStateUser().equals(mContext.getString(R.string.user_is_student_value))) {
                         intent = new Intent(mContext, StudentMainView.class);
                    }else {
                        intent = new Intent(mContext, TeacherMainView.class);
                    }
                    intent.putExtra(mContext.getString(R.string.user_id_key),users.getId());
                    mContext.startActivity(intent);
                    mLoginView.finishActivity();

                }else {
                    Toast.makeText(mContext, "login fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void registerButtonClick() {
        Intent intent=new Intent(mContext, RegisterView.class);
        mContext.startActivity(intent);
    }
}
