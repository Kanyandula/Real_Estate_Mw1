package com.example.realestatemw;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



import java.util.ArrayList;

public class FirebaseUtil {
    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mDatabaseReference;
    private static FirebaseUtil mFirebaseUtil;
    // creating a FirebaseAuth reference so we ca authenticate to our firebase database
    public static FirebaseAuth mFirebaseAuth;
    // create the FirebaseAuth.AuthStateListener so we can see if the user is already logged in or not
    public static FirebaseAuth.AuthStateListener mAuthStateListener;
    // to be able to connect to the Firebase Storage we will create new property
  //  public static FirebaseStorage mFirebaseStorage;
    // and like for the database we will also need a reference for our storage
   // public static StorageReference mStorageReference;

    public static ArrayList<Agent> mAgent;

    private FirebaseUtil() {
    }

    public static void openFirebaseReference(String ref){
        if (mFirebaseUtil == null){

            mFirebaseUtil = new FirebaseUtil();
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mAgent = new ArrayList<Agent>();

        }
        mDatabaseReference = mFirebaseDatabase.getReference().child(ref);

    }
}
