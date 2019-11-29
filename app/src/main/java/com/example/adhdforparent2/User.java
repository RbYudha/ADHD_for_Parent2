package com.example.adhdforparent2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class User extends AppCompatActivity {

    TextView mName,xEmail;
    String userID;

    FirebaseAuth fAuth;
    FirebaseFirestore FSdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mName = findViewById(R.id.textView_userInfoName);
        xEmail = findViewById(R.id.textView_userInfoEmail);
        fAuth = FirebaseAuth.getInstance();
        FSdatabase = FirebaseFirestore.getInstance();

        userID = fAuth.getCurrentUser().getUid();

        DocumentReference documentUserInfo = FSdatabase.collection("UserInfo").document(userID);

        documentUserInfo.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                mName.setText(documentSnapshot.getString("Name"));
                xEmail.setText(documentSnapshot.getString("Email"));
            }
        });

    }
}
