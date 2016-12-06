package com.garytokman.garyslistandroidapp.model;
// Gary Tokman
// 11/26/16
// GaryslistAndroidApp

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Job implements Parcelable {

    private String id;
    private String name;
    private String status;
    private boolean notification;

    public Job() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isNotification() {
        return notification;
    }

    public void setNotification(boolean notification) {
        this.notification = notification;
    }

    @Exclude
    public Map<String, Object> toJson() {
        Map<String, Object> data = new HashMap<>();

        data.put("id", id);
        data.put("name", name);
        data.put("status", status);
        data.put("notification", notification);

        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Job job = (Job) o;

        return id != null ? id.equals(job.id) : job.id == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (notification ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return name + " " + status + " " + notification;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.status);
        dest.writeByte(this.notification ? (byte) 1 : (byte) 0);
    }

    protected Job(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.status = in.readString();
        this.notification = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Job> CREATOR = new Parcelable.Creator<Job>() {
        @Override
        public Job createFromParcel(Parcel source) {
            return new Job(source);
        }

        @Override
        public Job[] newArray(int size) {
            return new Job[size];
        }
    };
}
