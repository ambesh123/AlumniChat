package com.example.ambeshtiwari.alumnichat;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class MainChatActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    String ChatRef;
    String username;
    EditText msgBox;
    ListView chatlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat);
        setUpNameAndRef();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child(ChatRef);
        chatlist= (ListView)findViewById(R.id.chat_list_view);
        msgBox= (EditText)findViewById(R.id.messageInput);
        msgBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                sendMessage();
                return true;
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        chatlist.setAdapter(new ChatListAdapter(this,myRef,username));
    }

    public void setUpNameAndRef(){
        SharedPreferences sp= getSharedPreferences("prefs",0);
        username = sp.getString("username",null);
        if(username==null)username="Anonymous";
        ChatRef= getIntent().getExtras().getString("contactname");
        Log.e("msg",ChatRef+" was ref");
        if(ChatRef.compareTo(username)>0)ChatRef=username+ChatRef;
        else ChatRef=ChatRef+username;
    }

    public void sendButtonClicked(View view) {
        sendMessage();
    }
    public void sendMessage(){
        String msg= msgBox.getText().toString();
        if(!msg.equals("")){
           myRef.child("messages").push().setValue(new InstantMessage(msg,username));
            msgBox.setText("");
        }
    }
}
