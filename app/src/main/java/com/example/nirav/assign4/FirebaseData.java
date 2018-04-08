package com.example.nirav.assign4;

import android.app.Application;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseData extends Application {
    //firebase instances declaration so that we can use it another part of the project.
    public DatabaseReference firebaseReference;
    public FirebaseDatabase firebaseDBInstance;
}
