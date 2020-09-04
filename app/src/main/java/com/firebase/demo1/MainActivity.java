package com.firebase.demo1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    TextView tvUser,tvLocation;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvLocation = findViewById(R.id.location);
        tvUser = findViewById(R.id.user);

        db = FirebaseFirestore.getInstance();
        getFirebaseCollection();
    }

    public void getFirebaseCollection() {
        /*DocumentReference docRef = db.collection("location").document("phlebo2");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });*/

        db.collection("location").document("phlebo1").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null || value == null) {
                    Log.w(TAG, "listen:error", error);
                    return;
                }

                /*for (DocumentChange dc : value.g()) {
                    switch (dc.getType()) {
                        case ADDED:
                            Log.d(TAG, "New user: " + new Gson().toJson(dc.getDocument().toObject(User.class)));
                            break;
                        case MODIFIED:
                            Log.d(TAG, "Modified user: " + dc.getDocument());
                            break;
                        case REMOVED:
                            Log.d(TAG, "Removed user: " + dc.getDocument());
                            break;
                    }
                }*/
                Log.d(TAG, "location" + new Gson().toJson(value.toObject(Location.class)));
                tvLocation.setText(new Gson().toJson(value.toObject(Location.class)));

            }
        });



        db.collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null || value == null) {
                    Log.w(TAG, "listen:error", error);
                    return;
                }

                for (DocumentChange dc : value.getDocumentChanges()) {
                    switch (dc.getType()) {
                        case ADDED:
                            Log.d(TAG, "New user: " + new Gson().toJson(dc.getDocument().toObject(User.class)));
                            tvUser.setText(new Gson().toJson(dc.getDocument().toObject(User.class)));
                            break;
                        case MODIFIED:
                            Log.d(TAG, "Modified user: " + dc.getDocument());
                            break;
                        case REMOVED:
                            Log.d(TAG, "Removed user: " + dc.getDocument());
                            break;
                    }
                }
            }
        });
    }



    public void addUser(View view) {
        User user1 = new User("Shubham", "Joshi", 1996);

        db.collection("users")
                .add(user1)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("home", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("home", "Error adding document", e);
                    }
                });


    }
}