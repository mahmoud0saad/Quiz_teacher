package com.example.roomdatabase.view.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.roomdatabase.R;

public class LoginView extends AppCompatActivity implements LoginViewMvp {
    private Button mLoginButton,mRegisterButton;
    private EditText mUsernameEditText,mPasswordEditText;
    private LoginPresenterMvp presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);

        mLoginButton=findViewById(R.id.login_login_button);
        mRegisterButton=findViewById(R.id.login_register_button);
        mUsernameEditText=findViewById(R.id.login_username_edit_text);
        mPasswordEditText=findViewById(R.id.login_password_edit_text);


        presenter=new LoginPresenter(this,this);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loginButtonClick(mUsernameEditText.getText().toString(),mPasswordEditText.getText().toString());
            }
        });

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.registerButtonClick();
            }
        });

    }

    @Override
    public void finishActivity() {
        finish();
    }
}
