package com.pedroapp.labassingment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.pedroapp.labassingment.room.Contact;
import com.pedroapp.labassingment.room.ContactRoomDb;
import com.pedroapp.labassingment.util.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {


    //Database Helper
    DatabaseHelper sqLiteDatabase;

    // Room database
    private ContactRoomDb contactRoomDb;

    List<Contact> contactList;
    ListView contactsListView;
    TextView TVcontactCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactsListView = findViewById(R.id.contacts_lv);
        contactList = new ArrayList<>();
        TVcontactCounter = (TextView) findViewById(R.id.contactNum);

        contactRoomDb = ContactRoomDb.getInstance(this);
        loadContacts();

    }

    @Override
    protected void onResume() {
        super.onResume();
        contactsListView = findViewById(R.id.contacts_lv);
        contactList = new ArrayList<>();

        contactRoomDb = ContactRoomDb.getInstance(this);
        loadContacts();

    }

    private void loadContacts(){

        contactList = contactRoomDb.contactDao().getAllContacts();
        //Log.e("DATABASE", String.valueOf(contactList.size()));
        ContactAdapter contactAdapter = new ContactAdapter(this,R.layout.list_layout_contact,contactList);
        contactsListView.setAdapter(contactAdapter);

        String numberLoaded = String.valueOf(contactList.size());
        String numberCont = contactList.size()+" contacts found";
        TVcontactCounter.setText(numberCont);

    }

    public void goToAddContact(View view) {
        Intent i = new Intent(MainActivity.this, AddContact.class);
        startActivity(i);
    }

}