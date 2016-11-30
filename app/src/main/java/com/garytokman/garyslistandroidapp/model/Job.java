package com.garytokman.garyslistandroidapp.model;
// Gary Tokman
// 11/26/16
// GaryslistAndroidApp

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Job {

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

        if (notification != job.notification) return false;
        if (id != null ? !id.equals(job.id) : job.id != null) return false;
        return name != null ? name.equals(job.name) : job.name == null &&
                (status != null ? status.equals(job.status) : job.status == null);

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
}
