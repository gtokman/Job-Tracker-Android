package com.garytokman.garyslistandroidapp.login;
// Gary Tokman
// 11/22/16
// GaryslistAndroidApp

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.garytokman.garyslistandroidapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class SignUpActivity extends AppCompatActivity {

    @BindView(R.id.email_text)
    EditText mEmailText;
    @BindView(R.id.username_text)
    EditText mUsernameText;
    @BindView(R.id.password_text)
    EditText mPasswordText;
    @BindView(R.id.signup_button)
    Button mSignupButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.signup_button)
    public void onClickSignUp(View view) {
        Timber.i("On click sign up!");
    }
}
