package com.garytokman.garyslistandroidapp.signup;
// Gary Tokman
// 11/22/16
// GaryslistAndroidApp

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.garytokman.garyslistandroidapp.R;
import com.garytokman.garyslistandroidapp.injecter.FirebaseAuthInjector;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class SignUpActivity extends AppCompatActivity implements SignUpContract.View, FirebaseAuth.AuthStateListener {

    @BindView(R.id.email_text)
    EditText mEmailText;
    @BindView(R.id.username_text)
    EditText mUsernameText;
    @BindView(R.id.password_text)
    EditText mPasswordText;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.activity_signup)
    LinearLayout mActivitySignup;

    private SignUpPresenter mSignUpPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        mSignUpPresenter = new SignUpPresenter(FirebaseAuthInjector.provideFirebaseAuth(), this);
        mSignUpPresenter.setView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mSignUpPresenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSignUpPresenter.onStop();
    }

    @OnClick(R.id.signup_button)
    public void onClickSignUp(View view) {
        Timber.i("On click sign up!");
        mSignUpPresenter.signUpUser();
    }

    @Override
    public String getEmailAddress() {
        return mEmailText.getText().toString();
    }

    @Override
    public String getUserName() {
        return mUsernameText.getText().toString();
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
                .setAction(R.string.ok_text, v -> {
                    Timber.i("OK hit");
                }).show();
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
            Timber.i("User is null.");
        } else {
            Timber.i("User is %s %s %s", user.getUid(), user.getEmail(), user.getDisplayName());
        }
    }
}
