package com.garytokman.garyslistandroidapp.injecter;
// Gary Tokman
// 11/26/16
// GaryslistAndroidApp

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseAuthInjector {

    public static FirebaseAuth provideFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    public static FirebaseUser provideFireUser() {
        return provideFirebaseAuth().getCurrentUser();
    }

}
