package com.garytokman.garyslistandroidapp.update;
// Gary Tokman
// 12/5/16
// GaryslistAndroidApp

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.garytokman.garyslistandroidapp.R;
import com.garytokman.garyslistandroidapp.model.Job;
import com.garytokman.garyslistandroidapp.status.JobsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdateActivity extends AppCompatActivity implements UpdateContract.View {

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.company_text)
    EditText mCompanyText;
    @BindView(R.id.radio_resume)
    RadioButton mRadioResume;
    @BindView(R.id.radio_interview)
    RadioButton mRadioInterview;
    @BindView(R.id.status_radio_group)
    RadioGroup mStatusRadioGroup;
    @BindView(R.id.notification_switch)
    Switch mNotificationSwitch;
    @BindView(R.id.root_view)
    LinearLayout mRootView;

    private UpdatePresenter mUpdatePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ButterKnife.bind(this);

        Job job = getIntent().getParcelableExtra(JobsActivity.EXTRA_JOB);
        mUpdatePresenter = new UpdatePresenter(this, job);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mUpdatePresenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mUpdatePresenter.onStop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.update_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_update) {
            mUpdatePresenter.savePost();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void displayJobName(String name) {
        mCompanyText.setText(name);
    }

    @Override
    public void displayJobStatus(String status) {
        if (mRadioInterview.getText().toString().equals(status)) {
            mRadioInterview.setChecked(true);
        } else {
            mRadioResume.setChecked(true);
        }
    }

    @Override
    public void displayNotification(boolean isOn) {
        mNotificationSwitch.setChecked(isOn);
    }

    @Override
    public String getCompanyName() {
        return mCompanyText.getText().toString();
    }

    @Override
    public String getApplicationStatus() {

        int selected = mStatusRadioGroup.getCheckedRadioButtonId();

        if (selected == mRadioResume.getId()) {
            return mRadioResume.getText().toString();
        }

        return mRadioInterview.getText().toString();
    }

    @Override
    public boolean getNotificationValue() {
        return mNotificationSwitch.isChecked();
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
    public void showEmptyFieldsMessage() {
        Toast.makeText(this, "No empty text!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMessage(String message) {
        Snackbar.make(mRootView, "Error: " + message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void hideKeyboard() {
        InputMethodManager inputMethodManager =
                (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }
}
