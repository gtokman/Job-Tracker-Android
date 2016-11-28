package com.garytokman.garyslistandroidapp.status;
// Gary Tokman
// 11/26/16
// GaryslistAndroidApp

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.garytokman.garyslistandroidapp.R;
import com.garytokman.garyslistandroidapp.model.Job;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.JobsViewHolder> {

    private List<Job> mJobs;

    public JobsAdapter() {
        mJobs = new ArrayList<>();
    }

    public void addJob(Job job) {
        if (!mJobs.contains(job)) {
            mJobs.add(job);
            notifyDataSetChanged();
        }
    }

    public void removeJob(Job job) {
        mJobs.remove(job);
        notifyDataSetChanged();
    }

    @Override
    public JobsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_item, parent, false);

        return new JobsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JobsViewHolder holder, int position) {

        Job job = mJobs.get(position);
        holder.mCompanyName.setText("Company name: " + job.getName());
        holder.mJobStatus.setText("Status: " + job.getStatus());
        holder.mImageView.setVisibility(job.isNotification() ? View.VISIBLE : View.INVISIBLE);

    }

    @Override
    public int getItemCount() {
        return mJobs.size();
    }

    public static class JobsViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.company_name)
        TextView mCompanyName;
        @BindView(R.id.job_status)
        TextView mJobStatus;
        @BindView(R.id.notif_image)
        ImageView mImageView;

        public JobsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
