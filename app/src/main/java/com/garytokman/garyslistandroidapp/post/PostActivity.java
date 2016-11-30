package com.garytokman.garyslistandroidapp.post;
// Gary Tokman
// 11/26/16
// GaryslistAndroidApp

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.garytokman.garyslistandroidapp.R;
import com.garytokman.garyslistandroidapp.injecter.FirebaseInjector;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostActivity extends AppCompatActivity implements PostContract.View {

    @BindView(R.id.radio_resume)
    RadioButton mRadioResume;
    @BindView(R.id.radio_interview)
    RadioButton mRadioInterview;
    @BindView(R.id.status_radio_group)
    RadioGroup mStatusRadioGroup;
    @BindView(R.id.company_text)
    EditText mCompanyText;
    @BindView(R.id.notification_switch)
    Switch mNotificationSwitch;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.root_view)
    LinearLayout mRootView;

    private PostPresenter mPostPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ButterKnife.bind(this);

        mPostPresenter = new PostPresenter(FirebaseInjector.provideRootDatabaseRef());
        mPostPresenter.onSetView(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.post_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.save_action) {

            mPostPresenter.savePost();

            return true;
        }

        return super.onOptionsItemSelected(item);
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
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyFieldsMessage() {
        Toast.makeText(this, "No empty fields", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMessage(String message) {
        Snackbar.make(mRootView, "Error: " + message, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.ok_text, v -> {
                    // Do something
                }).show();
    }

    @Override
    public void finishActivity() {
        finish();
    }
}
