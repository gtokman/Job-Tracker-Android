package com.garytokman.garyslistandroidapp.model;
// Gary Tokman
// 11/26/16
// GaryslistAndroidApp

public class User {

    private String mId;
    private String mEmail;
    private String mUserName;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }
}
