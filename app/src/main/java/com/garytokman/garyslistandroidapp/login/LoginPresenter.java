package com.garytokman.garyslistandroidapp.login;
// Gary Tokman
// 11/26/16
// GaryslistAndroidApp

import com.google.firebase.auth.FirebaseAuth;

import android.text.TextUtils;

import timber.log.Timber;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;
    private final FirebaseAuth mAuth;
    private final FirebaseAuth.AuthStateListener mAuthStateListener;

    public LoginPresenter(FirebaseAuth auth, FirebaseAuth.AuthStateListener authStateListener) {
        mAuth = auth;
        mAuthStateListener = authStateListener;
    }

    @Override
    public void loginUser() {
        mView.hideKeyBoard();
        mView.showLoadingIndicator();

        if (TextUtils.isEmpty(mView.getEmailAddress()) || TextUtils.isEmpty(mView.getPassword())) {
            mView.showEmptyTextFieldMessage();
            mView.hideLoadingIndicator();
        } else {
            mAuth.signInWithEmailAndPassword(mView.getEmailAddress(), mView.getPassword())
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            mView.hideLoadingIndicator();
                        }
                    })
                    .addOnFailureListener(e -> {
                        mView.hideLoadingIndicator();
                        mView.showErrorMessage(e.getLocalizedMessage());
                    });
        }
    }

    @Override
    public void userForgotPassword() {
        Timber.i("Forgot password");
    }

    @Override
    public void setView(LoginContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onStop() {
        mAuth.removeAuthStateListener(mAuthStateListener);
    }
}
