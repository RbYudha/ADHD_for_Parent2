package com.example.adhdforparent2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class child_info extends AppCompatActivity {

    EditText editTextChildName, editTextChildAge;
    Button buttonNext;
    Spinner spinnerGender;

    DatabaseReference databaseChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_info);

        databaseChild = FirebaseDatabase.getInstance().getReference("children");

        editTextChildName = findViewById(R.id.editText_child_name);
        editTextChildAge = findViewById(R.id.editText_child_age);
        buttonNext = findViewById(R.id.button_next);
        spinnerGender = findViewById(R.id.spinner_child_gender);
    }

    private void addChild(){
        String childName = editTextChildName.getText().toString().trim();
        String childAge = editTextChildAge.getText().toString().trim();
        String childGender = spinnerGender.getSelectedItem().toString();

        if(childName.isEmpty()){
            editTextChildName.setError("Please enter your child name");
            editTextChildName.requestFocus();

        } else if (childAge.isEmpty()) {
            editTextChildAge.setError("Please enter your child age");
            editTextChildAge.requestFocus();
        } else {
            String id = databaseChild.push().getKey();
            child_data childData = new child_data (id, childName, childGender, childAge);
            databaseChild.child(id).setValue(childData);
            Toast.makeText(this,"Child has been added",Toast.LENGTH_SHORT).show();
        }
    }

    public void nextMethod(View view) {
        addChild();
        Intent intentNext = new Intent(this, MainActivity.class);
        startActivity(intentNext);
    }


}
