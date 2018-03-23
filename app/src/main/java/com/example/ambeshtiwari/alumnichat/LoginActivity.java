package com.example.ambeshtiwari.alumnichat;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser!=null){
            finish();
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }
    }

    public void registerNewUser(View view) {
        startActivity(new Intent(this,RegisterActivity.class));
    }

    public void signInExistingUser(View view) {
        String email = ((AutoCompleteTextView)findViewById(R.id.login_email)).getText().toString();
        String password= ((EditText)findViewById(R.id.login_password)).getText().toString();
        if(!email.equals("") && !password.equals("")){
            ProgressBar pg= (ProgressBar)findViewById(R.id.progressbar2);
            pg.setVisibility(ProgressBar.VISIBLE);
            signIn(email,password);
        }
        else Toast.makeText(this, "Enter Something", Toast.LENGTH_SHORT).show();
    }

    public void signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("msg", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(LoginActivity.this, "Access Granted.",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            new AlertDialog.Builder(LoginActivity.this)
                                    .setTitle("Error")
                                    .setMessage("Wrong email Id or password")
                                    .setPositiveButton("OK",null)
                                    .show();
                        }

                        // ...
                    }
                });
    }



}
