package com.example.adhdforparent2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Register extends AppCompatActivity {

    public static final String TAG = "TAG";
    EditText mFullname,xEmail,mPassword;
    Button mRegisterBtn;
    ProgressBar mProgressBar;

    FirebaseAuth fAuth;
    FirebaseFirestore FSdatabase;

    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFullname = findViewById(R.id.editText_userName);
        xEmail = findViewById(R.id.editText_userEmail);
        mPassword = findViewById(R.id.editText_userPassword);
        mRegisterBtn = findViewById(R.id.button_createUser);
        mProgressBar = findViewById(R.id.progressBar_register);

        fAuth = FirebaseAuth.getInstance();
        FSdatabase = FirebaseFirestore.getInstance();

        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

    }


    public void btn_createUser(View view) {
        final String userName = mFullname.getText().toString();
        final String email = xEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        if (TextUtils.isEmpty(userName)) {
            mFullname.setError("Name is Required.");
            return;
        }

        if (TextUtils.isEmpty(email)) {
            xEmail.setError("Email is Required.");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            mPassword.setError("Password is required");
            return;
        }

        if (password.length()<6) {
            mPassword.setError("Password must be >= 6 characters");
            return;
        }

        mProgressBar.setVisibility(View.VISIBLE);
        fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Register.this, "user is succesfully created", Toast.LENGTH_SHORT).show();

                    userID = fAuth.getCurrentUser().getUid();
                    DocumentReference documentUser = FSdatabase.collection("UserInfo").document(userID);
                    final Map<String, Object> user = new HashMap<>();
                    user.put("Name", userName);
                    user.put("Email", email);
                    documentUser.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "OnSuccess: user profile is created for" + userID);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: "+e.toString());
                        }
                    });

                    startActivity(new Intent(getApplicationContext(),MainActivity.class));

                } else {
                    Toast.makeText(Register.this,"Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show() ;
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    public void goToLogin(View view) {
        startActivity(new Intent(getApplicationContext(),Login.class));
    }

}
