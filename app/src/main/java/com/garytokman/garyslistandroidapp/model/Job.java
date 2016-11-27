package com.garytokman.garyslistandroidapp.model;
// Gary Tokman
// 11/26/16
// GaryslistAndroidApp

public class Job {

    private String mName;
    private String mImage;
    private String mStatus;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Job job = (Job) o;

        if (mName != null ? !mName.equals(job.mName) : job.mName != null) return false;
        return mImage != null ? mImage.equals(job.mImage) : job.mImage == null
                && (mStatus != null ? mStatus.equals(job.mStatus) : job.mStatus == null);

    }

    @Override
    public int hashCode() {
        int result = mName != null ? mName.hashCode() : 0;
        result = 31 * result + (mImage != null ? mImage.hashCode() : 0);
        result = 31 * result + (mStatus != null ? mStatus.hashCode() : 0);
        return result;
    }
}
