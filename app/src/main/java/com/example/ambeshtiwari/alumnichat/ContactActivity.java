package com.example.ambeshtiwari.alumnichat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ContactActivity extends AppCompatActivity {

    ArrayList<String> contacts;
    ListView contactList;
    ArrayAdapter<String> adapter;
    MyDatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        contacts= new ArrayList<>();
        databaseHelper= new MyDatabaseHelper(this);
        contactList=(ListView)findViewById(R.id.contacts);
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contacts);
        contactList.setAdapter(adapter);
        loadContacts();
        contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ContactActivity.this,MainChatActivity.class);
                intent.putExtra("contactname",contacts.get(i));
                startActivity(intent);
            }
        });
    }

    public void addContact(View view) {
        EditText editText = (EditText)findViewById(R.id.add_contact_input);
        String newContact= (editText).getText().toString();
        contacts.add(newContact);
        editText.setText("");
        adapter.notifyDataSetChanged();
        databaseHelper.addContact(newContact);
    }

    public void loadContacts(){
        for(int i=0;databaseHelper.getContactAt(i)!=null;i++)contacts.add(databaseHelper.getContactAt(i));
        adapter.notifyDataSetChanged();
    }
}
