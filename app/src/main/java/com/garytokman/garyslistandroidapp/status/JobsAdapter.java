package com.garytokman.garyslistandroidapp.status;
// Gary Tokman
// 11/26/16
// GaryslistAndroidApp

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.garytokman.garyslistandroidapp.model.Job;

import java.util.ArrayList;
import java.util.List;

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.JobsViewHolder> {

    private List<Job> mJobs;

    public JobsAdapter() {
        mJobs = new ArrayList<>();
    }

    public void addJob(Job job) {
        mJobs.add(job);
        notifyDataSetChanged();
    }

    public void removeJob(Job job) {
        mJobs.remove(job);
        notifyDataSetChanged();
    }

    @Override
    public JobsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

//        View view = LayoutInflater.from(parent.getContext()).inflate()

        return new JobsViewHolder(null);
    }

    @Override
    public void onBindViewHolder(JobsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mJobs.size();
    }

    public static class JobsViewHolder extends RecyclerView.ViewHolder {
        public JobsViewHolder(View itemView) {
            super(itemView);
        }
    }


}
