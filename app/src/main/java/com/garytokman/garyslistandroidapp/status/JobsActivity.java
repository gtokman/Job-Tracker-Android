package com.garytokman.garyslistandroidapp.status;
// Gary Tokman
// 11/26/16
// GaryslistAndroidApp

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.garytokman.garyslistandroidapp.R;
import com.garytokman.garyslistandroidapp.injecter.FirebaseInjector;
import com.garytokman.garyslistandroidapp.auth.login.LoginActivity;
import com.garytokman.garyslistandroidapp.model.Job;
import com.garytokman.garyslistandroidapp.post.PostActivity;
import com.garytokman.garyslistandroidapp.update.UpdateActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class JobsActivity extends AppCompatActivity implements JobsContract.View {

    public static final String EXTRA_JOB = "EXTRA_JOB";

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

        mJobsAdapter = new JobsAdapter(mOnBookListener);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mRecycler.setAdapter(mJobsAdapter);

        mJobsPresenter = new JobsPresenter(FirebaseInjector.provideRootDatabaseRef());
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
        startActivity(new Intent(this, PostActivity.class));
    }

    @Override
    public void addJob(Job job) {
        mJobsAdapter.addJob(job);
    }

    @Override
    public void updateJob(Job job) {
        mJobsAdapter.update(job);
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
        Snackbar.make(mCoorLayout, "Error: " + errorMessage, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.ok_text, v -> Timber.i("Ok clicked after error.")).show();
    }

    @Override
    public void onUserLogout() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    public void showJobDeletedMessage() {
        Toast.makeText(this, "Job successfully deleted!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout_action) {
            mJobsPresenter.logoutUser();
        }
        return super.onOptionsItemSelected(item);
    }

    private JobsAdapter.OnBookListener mOnBookListener = new JobsAdapter.OnBookListener() {
        @Override
        public void onJobSelectedLong(Job job) {
            mJobsPresenter.getJobToDelete(job);
        }

        @Override
        public void onJobSelectedShort(Job job) {
            Intent intent = new Intent(JobsActivity.this, UpdateActivity.class);
            intent.putExtra(EXTRA_JOB, job);
            startActivity(intent);
        }
    };
}
