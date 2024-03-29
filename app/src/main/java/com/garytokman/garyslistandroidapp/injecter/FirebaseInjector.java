package com.garytokman.garyslistandroidapp.injecter;
// Gary Tokman
// 11/26/16
// GaryslistAndroidApp

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseInjector {

    public static FirebaseAuth provideFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    public static FirebaseUser provideFireUser() {
        return provideFirebaseAuth().getCurrentUser();
    }

    public static DatabaseReference provideRootDatabaseRef() {
        return provideFirebaseDateBase().getReference();
    }

    private static FirebaseDatabase provideFirebaseDateBase() {
        return FirebaseDatabase.getInstance();
    }
}
