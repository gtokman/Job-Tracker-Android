package com.garytokman.garyslistandroidapp.update;
// Gary Tokman
// 12/5/16
// GaryslistAndroidApp

import com.google.firebase.database.DatabaseReference;

import android.text.TextUtils;

import com.garytokman.garyslistandroidapp.GarysListApplication;
import com.garytokman.garyslistandroidapp.injecter.FirebaseInjector;
import com.garytokman.garyslistandroidapp.model.Job;
import com.garytokman.garyslistandroidapp.post.PostContract;

public class UpdatePresenter implements UpdateContract.Presenter {

    private UpdateContract.View mView;
    private Job mJob;

    public UpdatePresenter(UpdateContract.View view, Job job) {
        mView = view;
        mJob = job;
    }

    @Override
    public void onStart() {
        loadUserDetails();
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onSetView(PostContract.View view) {

    }

    @Override
    public void savePost() {
        mView.showLoadingIndicator();

        if (TextUtils.isEmpty(mView.getCompanyName())) {
            mView.showEmptyFieldsMessage();
            mView.hideLoadingIndicator();
        } else {

            DatabaseReference ref = FirebaseInjector.provideRootDatabaseRef().getDatabase().getReference();
            DatabaseReference jobRef = ref.child("users").child(FirebaseInjector.provideFireUser().getUid())
                    .child("jobs").child(mJob.getId());

            mJob.setName(mView.getCompanyName());
            mJob.setStatus(mView.getApplicationStatus());
            mJob.setNotification(mView.getNotificationValue());

            jobRef.updateChildren(mJob.toJson()).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    mView.hideLoadingIndicator();
                    mView.finishActivity();
                }
            }).addOnFailureListener( e -> {
                mView.hideLoadingIndicator();
                mView.showErrorMessage(e.getLocalizedMessage());
            });

            if (!GarysListApplication.getApplication().hasNetwork()) {
                mView.hideLoadingIndicator();
                mView.hideKeyboard();
                mView.finishActivity();
            }
        }
    }

    @Override
    public void loadUserDetails() {
        if (mJob != null) {
            mView.displayJobName(mJob.getName());
            mView.displayNotification(mJob.isNotification());
            mView.displayJobStatus(mJob.getStatus());
        }
    }
}
