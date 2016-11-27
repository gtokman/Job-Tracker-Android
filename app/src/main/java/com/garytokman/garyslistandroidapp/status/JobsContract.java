package com.garytokman.garyslistandroidapp.status;
// Gary Tokman
// 11/26/16
// GaryslistAndroidApp

import com.garytokman.garyslistandroidapp.model.Job;

public interface JobsContract {

    interface View {
        void addJob(Job job);

        void removeJob(Job job);

        void showEmptyText();

        void hideEmptyText();

        void showLoadingIndicator();

        void hideLoadingIndicator();

        void showErrorMessage(String errorMessage);
    }

    interface Presenter {

        void loadAllJobs();

        void removeJob();

        void setView(View view);

        void onStart();

        void onStop();
    }
}
