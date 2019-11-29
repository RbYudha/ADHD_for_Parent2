package com.example.adhdforparent2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class look_child_info extends AppCompatActivity  {

    private FirebaseAuth.AuthStateListener mAuthStateListener;
    FirebaseAuth mFirebaseAuth;

    ListView listViewChild;
    DatabaseReference databaseChild;
    List<child_data> childDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_child_info);

        databaseChild = FirebaseDatabase.getInstance().getReference("children");
        listViewChild = (ListView) findViewById(R.id.listView_child);
        childDataList = new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseChild.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

              childDataList.clear();

                for(DataSnapshot childSnapshot : dataSnapshot.getChildren() ){

                    child_data childData = childSnapshot.getValue(child_data.class);

                    childDataList.add(childData);
                }

                child_list adapter = new child_list(look_child_info.this, childDataList);
                listViewChild.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}