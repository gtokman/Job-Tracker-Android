package com.garytokman.garyslistandroidapp.signup;
// Gary Tokman
// 11/26/16
// GaryslistAndroidApp

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;

import android.text.TextUtils;

import timber.log.Timber;

public class SignUpPresenter implements SignUpContract.Presenter {

    private SignUpContract.View mView;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    public SignUpPresenter(FirebaseAuth auth, FirebaseAuth.AuthStateListener authStateListener) {
        mAuth = auth;
        mAuthStateListener = authStateListener;
    }

    @Override
    public void onStart() {
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onStop() {
        mAuth.removeAuthStateListener(mAuthStateListener);
    }

    @Override
    public void signUpUser() {
        mView.showLoadingIndicator();
        mView.hideKeyBoard();

        if (TextUtils.isEmpty(mView.getEmailAddress()) || TextUtils.isEmpty(mView.getPassword())
                || TextUtils.isEmpty(mView.getUserName())) {
            mView.hideLoadingIndicator();
            mView.showEmptyTextFieldMessage();
        } else {
            mAuth.createUserWithEmailAndPassword(mView.getEmailAddress(), mView.getPassword())
                    .addOnCompleteListener(task -> {

                        if (task.isSuccessful()) {
                            task.getResult().getUser()
                                    .updateProfile(new UserProfileChangeRequest.Builder()
                                            .setDisplayName(mView.getUserName()).build());
                            mView.hideLoadingIndicator();
                            Timber.i("Sign up user successfully!");
                        }

                    }).addOnFailureListener(e -> {
                mView.showErrorMessage(e.getLocalizedMessage());
                mView.hideLoadingIndicator();
            });
        }
    }

    @Override
    public void setView(SignUpContract.View view) {
        mView = view;
    }
}
