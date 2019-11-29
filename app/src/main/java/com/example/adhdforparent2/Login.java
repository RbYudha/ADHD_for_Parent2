package com.example.adhdforparent2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText xEmail,mPassword;
    Button mLoginBtn;
    ProgressBar mProgressBar;

    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        xEmail = findViewById(R.id.editText_userEmail);
        mPassword = findViewById(R.id.editText_userPassword);
        mLoginBtn = findViewById(R.id.button_loginUser);
        mProgressBar = findViewById(R.id.progressBar_login);

        fAuth = FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

    }

    public void btn_loginUser(View view) {
        String email = xEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            xEmail.setError("Email is Required.");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            mPassword.setError("Password is required");
            return;
        }

        if (password.length() < 6) {
            mPassword.setError("Password must be >= 6 characters");
        }

        mProgressBar.setVisibility(View.VISIBLE);

        fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Login.this, "Logged in succes", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                } else {
                    Toast.makeText(Login.this,"Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show() ;
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    public void goToRegister(View view) {
        startActivity(new Intent(getApplicationContext(),Register.class));
    }

}
