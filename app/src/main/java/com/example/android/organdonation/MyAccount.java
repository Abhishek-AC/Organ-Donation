package com.example.android.organdonation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

public class MyAccount extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    public static final String PREFS = "storedData";
    private TextView displayN;
    private TextView emailID;
    private ImageView profileP;

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() == null )
                {
                    startActivity(new Intent(MyAccount.this,MainActivity.class));
                }
            }
        };

        displayN = (TextView) findViewById(R.id.userName);
        emailID = (TextView) findViewById(R.id.userEmail);
        profileP = (ImageView) findViewById(R.id.userPic);

        SharedPreferences received = getSharedPreferences(PREFS,0);

        String userName = received.getString("userName","NA");
        displayN.setText(userName);

        String userEmail = received.getString("userEmail","NA");
        emailID.setText(userEmail);

        String userPhoto = received.getString("userProfile","NA");
        Glide.with(this).load(userPhoto).into(profileP);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.nav_logout:
                        Log.i("hey","logout clicked" );
                        mAuth.signOut();
                        break;
                    case R.id.nav_account:
                        startActivity(new Intent(MyAccount.this,MyAccount.class));
                        break;
                }
                return true;
            }
        });

    }

}
