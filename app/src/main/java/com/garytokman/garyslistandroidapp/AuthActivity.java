package com.garytokman.garyslistandroidapp;
// Gary Tokman
// 11/22/16
// GaryslistAndroidApp

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.garytokman.garyslistandroidapp.injecter.FirebaseAuthInjector;
import com.garytokman.garyslistandroidapp.login.LoginActivity;
import com.garytokman.garyslistandroidapp.signup.SignUpActivity;
import com.garytokman.garyslistandroidapp.status.JobsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;


public class AuthActivity extends AppCompatActivity {

    @BindView(R.id.logo_image)
    ImageView mLogoImage;
    @BindView(R.id.desc_text)
    TextView mDescText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        ButterKnife.bind(this);

        if (FirebaseAuthInjector.provideFireUser() != null) {
            startActivity(new Intent(this, JobsActivity.class));
            finish();
        }
    }

    @OnClick(R.id.user_login_button)
    public void onClickLogin(View view) {
        Timber.i("Login clicked!");
        startActivity(new Intent(this, LoginActivity.class));
    }

    @OnClick(R.id.user_signup_button)
    public void onClickSignUp(View view) {
        Timber.i("Sign up clicked!");
        startActivity(new Intent(this, SignUpActivity.class));
    }
}
