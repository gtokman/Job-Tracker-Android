package com.garytokman.garyslistandroidapp.status;
// Gary Tokman
// 11/26/16
// GaryslistAndroidApp

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.garytokman.garyslistandroidapp.R;
import com.garytokman.garyslistandroidapp.injecter.FirebaseAuthInjector;
import com.garytokman.garyslistandroidapp.model.Job;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class JobsActivity extends AppCompatActivity implements JobsContract.View {

    @BindView(R.id.emptyText)
    TextView mEmptyText;
    @BindView(R.id.recycler)
    RecyclerView mRecycler;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.coor_layout)
    CoordinatorLayout mCoorLayout;

    private JobsPresenter mJobsPresenter;
    private JobsAdapter mJobsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_jobs);
        ButterKnife.bind(this);

        mJobsAdapter = new JobsAdapter();
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapter(mJobsAdapter);

        mJobsPresenter = new JobsPresenter(FirebaseAuthInjector.provideRootDatabaseRef());
        mJobsPresenter.setView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mJobsPresenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mJobsPresenter.onStop();
    }

    @OnClick(R.id.add_fab)
    public void onClickFab() {
        Timber.i("OnClick fab");
    }

    @Override
    public void addJob(Job job) {
        mJobsAdapter.addJob(job);
    }

    @Override
    public void removeJob(Job job) {
        mJobsAdapter.removeJob(job);
    }

    @Override
    public void showEmptyText() {
        mEmptyText.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyText() {
        mEmptyText.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLoadingIndicator() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingIndicator() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Snackbar.make(mCoorLayout, "Error: " + errorMessage, Snackbar.LENGTH_INDEFINITE).setAction(R.string.ok_text, v -> {
            Timber.i("Ok clicked after error.");
        }).show();
    }
}
