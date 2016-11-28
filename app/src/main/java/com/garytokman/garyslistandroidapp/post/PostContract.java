package com.garytokman.garyslistandroidapp.post;
// Gary Tokman
// 11/27/16
// GaryslistAndroidApp

public interface PostContract {

    interface View {

        String getCompanyName();

        String getApplicationStatus();

        boolean getNotificationValue();

        void showLoadingIndicator();

        void hideLoadingIndicator();

        void showEmptyFieldsMessage();

        void showErrorMessage(String message);

        void finishActivity();

    }

    interface Presenter {

        void onStart();

        void onStop();

        void onSetView(View view);

        void savePost();
    }

}
