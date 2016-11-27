package com.garytokman.garyslistandroidapp.status;
// Gary Tokman
// 11/26/16
// GaryslistAndroidApp

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import com.garytokman.garyslistandroidapp.model.Job;

import timber.log.Timber;

import static com.garytokman.garyslistandroidapp.injecter.FirebaseAuthInjector.provideFireUser;

public class JobsPresenter implements JobsContract.Presenter, ChildEventListener {

    private JobsContract.View mView;
    private DatabaseReference mDatabaseReference;

    public JobsPresenter(DatabaseReference databaseReference) {
        mDatabaseReference = databaseReference;
    }

    @Override
    public void loadAllJobs() {
        mView.showLoadingIndicator();
        mDatabaseReference.child(provideFireUser().getUid()).child("jobs").addChildEventListener(this);
    }

    @Override
    public void removeJob() {
        mView.showLoadingIndicator();
        mDatabaseReference.child(provideFireUser().getUid()).child("jobs").addChildEventListener(this);
    }

    @Override
    public void setView(JobsContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {
        loadAllJobs();
    }

    @Override
    public void onStop() {
        mDatabaseReference.removeEventListener(this);
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
        if (dataSnapshot.exists()) {
            mView.hideLoadingIndicator();
            mView.hideEmptyText();
            Job job = dataSnapshot.getValue(Job.class);
            mView.addJob(job);
            Timber.i("dataSnapshot exists.");
        } else {
            mView.showEmptyText();
            mView.hideLoadingIndicator();
        }
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {
        mView.hideLoadingIndicator();
        Job job = dataSnapshot.getValue(Job.class);
        mView.removeJob(job);
    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        mView.showErrorMessage(databaseError.getMessage());
        mView.hideLoadingIndicator();
    }
}
