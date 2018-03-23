package com.example.ambeshtiwari.alumnichat;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
    }

    public void signUp(View view) {

        String email= ((AutoCompleteTextView) findViewById(R.id.register_email)).getText().toString();
        String password=((EditText)findViewById(R.id.register_password)).getText().toString();
        String confirm_password=((EditText)findViewById(R.id.register_confirm_password)).getText().toString();
        if(password.equals(confirm_password) && password.length()>5 && email.contains("@")){
            ProgressBar pg= (ProgressBar)findViewById(R.id.progressbar1);
            pg.setVisibility(ProgressBar.VISIBLE);
            createAccount(email,password);
        }
        else {
            new AlertDialog.Builder(RegisterActivity.this)
                    .setTitle("Invalid Entries")
                    .setMessage("Check Your Entries again...")
                    .setPositiveButton("OK",null)
                    .show();
        }
    }

    void createAccount(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            saveName();
                            Toast.makeText(RegisterActivity.this, "User Added",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));

                        } else {
                            // If sign in fails, display a message to the user.
                           // Log.w("msg", "createUserWithEmail:failure", task.getException());
                            new AlertDialog.Builder(RegisterActivity.this)
                                    .setTitle("Failed")
                                    .setMessage("Something went wrong!")
                                    .setPositiveButton("ok",null)
                                    .show();
                        }

                        // ...
                    }
                });
    }

    void saveName(){
        String username= ((EditText)findViewById(R.id.register_username)).getText().toString();
        SharedPreferences sp= getSharedPreferences("prefs",0);
        sp.edit().putString("username",username).apply();
    }
}
