package com.example.ambeshtiwari.alumnichat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
    }

    public void sendFeedback(View view) {
        DatabaseReference myRef = (FirebaseDatabase.getInstance()).getReference();
        String fdbk =  ((EditText)findViewById(R.id.feedback)).getText().toString();
        myRef.child("feedback").push().setValue(fdbk);
        Toast.makeText(this,"Request Sent!\nWe will respond shortly",Toast.LENGTH_SHORT).show();
        finish();
        startActivity(new Intent(Feedback.this,MainActivity.class));
    }
}
