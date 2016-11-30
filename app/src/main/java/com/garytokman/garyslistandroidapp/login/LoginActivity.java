package com.garytokman.garyslistandroidapp.login;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.garytokman.garyslistandroidapp.R;
import com.garytokman.garyslistandroidapp.injecter.FirebaseInjector;
import com.garytokman.garyslistandroidapp.status.JobsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class LoginActivity extends AppCompatActivity implements LoginContract.View, FirebaseAuth.AuthStateListener {

    @BindView(R.id.email_text)
    EditText mEmailText;
    @BindView(R.id.password_text)
    EditText mPasswordText;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.activity_signup)
    LinearLayout mActivitySignup;

    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mLoginPresenter = new LoginPresenter(FirebaseInjector.provideFirebaseAuth(), this);
        mLoginPresenter.setView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mLoginPresenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mLoginPresenter.onStop();
    }

    @OnClick(R.id.login_button)
    public void onClickLogin(View view) {
        mLoginPresenter.loginUser();
    }

    @OnClick(R.id.forgot_password_button)
    public void onClickForgotPassword(View view) {
        mLoginPresenter.userForgotPassword();
    }

    @Override
    public String getEmailAddress() {
        return mEmailText.getText().toString();
    }

    @Override
    public String getPassword() {
        return mPasswordText.getText().toString();
    }

    @Override
    public void showLoadingIndicator() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingIndicator() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showEmptyTextFieldMessage() {
        Snackbar.make(mActivitySignup, R.string.empty_fields, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Snackbar.make(mActivitySignup, "Error: " + errorMessage, Snackbar.LENGTH_INDEFINITE)
        .setAction(R.string.ok_text, v -> Timber.i("OK clicked")).show();
    }

    @Override
    public void hideKeyBoard() {
        InputMethodManager inputMethodManager =
                (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user == null) {
            Timber.i("User is null");
        } else {
            Timber.i("Start activity we have a user %s %s", user.getUid(), user.getEmail());
            startActivity(new Intent(this, JobsActivity.class));
            finish();
        }
    }
}
