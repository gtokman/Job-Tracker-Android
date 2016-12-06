package com.garytokman.garyslistandroidapp.update;
// Gary Tokman
// 12/5/16
// GaryslistAndroidApp

import com.garytokman.garyslistandroidapp.post.PostContract;

public interface UpdateContract {

    interface View extends PostContract.View {
        void displayJobName(String name);

        void displayJobStatus(String status);

        void displayNotification(boolean isOn);
    }

    interface Presenter extends PostContract.Presenter {

        void loadUserDetails();

    }

}
