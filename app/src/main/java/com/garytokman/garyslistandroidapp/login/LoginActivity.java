package com.garytokman.garyslistandroidapp.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.garytokman.garyslistandroidapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.email_text)
    EditText mEmailText;
    @BindView(R.id.password_text)
    EditText mPasswordText;
    @BindView(R.id.login_button)
    Button mLoginButton;
    @BindView(R.id.forgot_password_button)
    Button mForgotPasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_button)
    public void onClickLogin(View view) {
        Timber.i("Login clicked!");
    }

    @OnClick(R.id.forgot_password_button)
    public void onClickForgotPassword(View view) {
        Timber.i("Clicked forgot password!");
    }
}
