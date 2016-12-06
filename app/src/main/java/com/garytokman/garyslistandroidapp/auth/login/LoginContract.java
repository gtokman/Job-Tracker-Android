package com.garytokman.garyslistandroidapp.auth.login;
// Gary Tokman
// 11/26/16
// GaryslistAndroidApp

public interface LoginContract {

    interface View {
        String getEmailAddress();

        String getPassword();

        void showLoadingIndicator();

        void hideLoadingIndicator();

        void showEmptyTextFieldMessage();

        void showNoNetworkError();

        void showErrorMessage(String errorMessage);

        void hideKeyBoard();
    }

    interface Presenter {
        void loginUser();

        void userForgotPassword();

        void setView(View view);

        void onStart();

        void onStop();
    }
}
