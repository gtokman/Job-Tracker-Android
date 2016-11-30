package com.garytokman.garyslistandroidapp.post;
// Gary Tokman
// 11/27/16
// GaryslistAndroidApp

import com.google.firebase.database.DatabaseReference;

import android.text.TextUtils;

import com.garytokman.garyslistandroidapp.injecter.FirebaseInjector;
import com.garytokman.garyslistandroidapp.model.Job;

public class PostPresenter implements PostContract.Presenter {

    private PostContract.View mView;
    private DatabaseReference mReference;

    public PostPresenter(DatabaseReference reference) {
        mReference = reference;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onSetView(PostContract.View view) {
        mView = view;
    }

    @Override
    public void savePost() {
        mView.showLoadingIndicator();
        if (TextUtils.isEmpty(mView.getCompanyName())) {
            mView.showEmptyFieldsMessage();
            mView.hideLoadingIndicator();
        } else {
            DatabaseReference reference = mReference.child("users")
                    .child(FirebaseInjector.provideFireUser()
                            .getUid()).child("jobs").push();

            Job job = new Job();
            job.setName(mView.getCompanyName());
            job.setStatus(mView.getApplicationStatus());
            job.setNotification(mView.getNotificationValue());
            job.setId(reference.getKey());

            reference.updateChildren(job.toJson())
                    .addOnCompleteListener(task -> {

                        if (task.isSuccessful()) {
                            mView.hideLoadingIndicator();
                            mView.finishActivity();
                        }

                    }).addOnFailureListener(e -> {
                mView.hideLoadingIndicator();
                mView.showErrorMessage(e.getLocalizedMessage());
            });
        }
    }
}
