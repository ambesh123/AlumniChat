package com.example.ambeshtiwari.alumnichat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toContactActivity(View view) {
        startActivity(new Intent(MainActivity.this,ContactActivity.class));
    }

    public void logOut(View view) {
        //System.exit(0);
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        finish();
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
    }

    public void feedback(View view) {
        startActivity(new Intent(MainActivity.this,Feedback.class));
    }
}
