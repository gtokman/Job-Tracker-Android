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

    public interface OnBookListener {
        void onJobSelectedLong(Job job);

        void onJobSelectedShort(Job job);

    }

    private List<Job> mJobs;
    private OnBookListener mOnBookListener;

    public JobsAdapter(OnBookListener onBookListener) {
        mOnBookListener = onBookListener;
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

    public void update(Job job) {
        for (Job j : mJobs) {
            if (j.equals(job)) {
                j.setNotification(job.isNotification());
                j.setStatus(job.getStatus());
                j.setName(job.getName());
                int position = mJobs.indexOf(j);
                notifyItemChanged(position);
            }
        }
    }

    @Override
    public JobsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_item, parent, false);

        return new JobsViewHolder(view, mOnBookListener);
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

    public  class JobsViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener,
    View.OnClickListener {


        @BindView(R.id.company_name)
        TextView mCompanyName;
        @BindView(R.id.job_status)
        TextView mJobStatus;
        @BindView(R.id.notif_image)
        ImageView mImageView;

        private OnBookListener mOnBookListener;

        public JobsViewHolder(View itemView, OnBookListener onBookListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mOnBookListener = onBookListener;
            itemView.setOnLongClickListener(this);
            itemView.setOnClickListener(this);
        }


        @Override
        public boolean onLongClick(View view) {

            Job job = getJob(getAdapterPosition());
            mOnBookListener.onJobSelectedLong(job);

            return true;
        }

        @Override
        public void onClick(View view) {
            Job job = getJob(getAdapterPosition());
            mOnBookListener.onJobSelectedShort(job);
        }
    }

    private Job getJob(int itemIndex) {
        return mJobs.get(itemIndex);
    }
}
