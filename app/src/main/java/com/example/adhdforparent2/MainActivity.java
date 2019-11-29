package com.example.adhdforparent2;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayoutXml);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.AppBarXml);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPagerXml);


        viewPagerAdapter adapter = new viewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ADHD_info(),"ADHD?");
        adapter.addFragment(new ADHD_test(),"ADHD test");
        adapter.addFragment(new ADHD_treatment(),"Treatment");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_about) {
            return true;
        }

        if (id == R.id.menu_user) {
            Intent intent = new Intent(this, User.class);
            startActivity(intent);
        }

        if (id == R.id.menu_logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(),Login.class));
            Toast.makeText(MainActivity.this,"Logout Success",Toast.LENGTH_SHORT).show();
        }

        if (id == R.id.menu_language) {
        }

        return super.onOptionsItemSelected(item);
    }

    public void testMethod(View view) {
        Intent intentTest = new Intent(MainActivity.this, child_info.class);
        startActivity(intentTest);
    }

    public void childInformationButton(View view) {
        Intent intentLookChild = new Intent(MainActivity.this, look_child_info.class);
        startActivity(intentLookChild);
    }

}