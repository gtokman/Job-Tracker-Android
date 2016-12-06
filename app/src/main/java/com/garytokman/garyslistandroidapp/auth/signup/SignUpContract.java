package com.garytokman.garyslistandroidapp.auth.signup;
// Gary Tokman
// 11/26/16
// GaryslistAndroidApp

import com.garytokman.garyslistandroidapp.auth.login.LoginContract;

public interface SignUpContract {

    interface View extends LoginContract.View {
        String getUserName();
    }

    interface Presenter {

        void onStart();

        void onStop();

        void signUpUser();

        void setView(View view);
    }

}
