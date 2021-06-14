package com.pedroapp.labassingment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.pedroapp.labassingment.room.Contact;
import com.pedroapp.labassingment.room.ContactRoomDb;
import com.pedroapp.labassingment.util.DatabaseHelper;

public class AddContact extends AppCompatActivity {

    DatabaseHelper sqLiteDatabase;

    private ContactRoomDb contactRoomDb;

    EditText etNewFirstName, etNewLastName, etNewEmail, etNewPhoneNumber, etNewAddress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        etNewFirstName = findViewById(R.id.editNewTextFirstName);
        etNewLastName = findViewById(R.id.editNewTextLastName);
        etNewEmail = findViewById(R.id.editNewTextEmail);
        etNewPhoneNumber = findViewById(R.id.editNewTextPhoneNumber);
        etNewAddress = findViewById(R.id.editNewTextAddress);

        contactRoomDb = ContactRoomDb.getInstance(this);

    }

    public void addContact(View view) {

        String newFirstName = etNewFirstName.getText().toString().trim();
        String newLastName = etNewLastName.getText().toString().trim();
        String newEmail = etNewEmail.getText().toString().trim();
        String newPhoneNumber = etNewPhoneNumber.getText().toString().trim();
        String newAddress = etNewAddress.getText().toString().trim();

        if (newFirstName.isEmpty()) {
            etNewFirstName.setError("This field cannot be empty");
            etNewFirstName.requestFocus();
            return;
        }
        if (newLastName.isEmpty()) {
            etNewLastName.setError("This field cannot be empty");
            etNewLastName.requestFocus();
            return;
        }
        if (newEmail.isEmpty()) {
            etNewEmail.setError("This field cannot be empty");
            etNewEmail.requestFocus();
            return;
        }
        if (newPhoneNumber.isEmpty()) {
            etNewPhoneNumber.setError("This field cannot be empty");
            etNewPhoneNumber.requestFocus();
            return;
        }
        if (newAddress.isEmpty()) {
            etNewAddress.setError("This field cannot be empty");
            etNewAddress.requestFocus();
            return;
        }


        Contact contact = new Contact(newFirstName,newLastName,newEmail,newPhoneNumber,newAddress);
        contactRoomDb.contactDao().insertContact(contact);
        Toast.makeText(AddContact.this,"Contact Added",Toast.LENGTH_SHORT).show();
        clearFields();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        clearFields();
    }

    private void clearFields() {
        etNewFirstName.setText("");
        etNewLastName.setText("");
        etNewEmail.setText("");
        etNewPhoneNumber.setText("");
        etNewAddress.setText("");

    }



}