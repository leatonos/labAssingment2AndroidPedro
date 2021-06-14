package com.pedroapp.labassingment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pedroapp.labassingment.room.Contact;
import com.pedroapp.labassingment.room.ContactRoomDb;
import com.pedroapp.labassingment.util.DatabaseHelper;
import com.pedroapp.labassingment.util.ContactLongClickHelper;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.List;

public class ContactAdapter extends ArrayAdapter {

    private static final String TAG = "ContactAdapter";

    Context context;
    int layoutRes;
    List<Contact> contactList;
    DatabaseHelper sqLiteDatabase;
    ContactRoomDb contactRoomDb;


    public ContactAdapter(@NonNull Context context, int resource, List<Contact> contactList) {

        super(context, resource, contactList);
        this.contactList = contactList;
        this.context = context;
        this.layoutRes = resource;
        contactRoomDb = ContactRoomDb.getInstance(context);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = convertView;
        if (v == null) v = inflater.inflate(layoutRes, null);
        TextView nameTv = v.findViewById(R.id.contactName);
        TextView emailTv = v.findViewById(R.id.email);
        TextView phoneNumberTv = v.findViewById(R.id.phoneNumber);
        TextView addressTv = v.findViewById(R.id.address);


        final Contact contact = contactList.get(position);
        nameTv.setText(contact.getFirst_name());
        emailTv.setText(contact.getEmail());

        phoneNumberTv.setText(contact.getPhone_number());
        addressTv.setText(contact.getAddress());

                v.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        ContactLongClickHelper.firstNameClicked = contact.getFirst_name();
                        ContactLongClickHelper.lastNameClicked = contact.getLast_name();
                        ContactLongClickHelper.emailClicked = contact.getEmail();
                        ContactLongClickHelper.phoneNumberClicked = contact.getPhone_number();
                        ContactLongClickHelper.addressClicked = contact.getAddress();

                        Intent i = new Intent(context, ContactInteraction.class);
                        context.startActivity(i);

                        return false;
                    }
                }
                );

                v.findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updateContact(contact);
                    }

                    private void updateContact(final Contact contact) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        LayoutInflater layoutInflater = LayoutInflater.from(context);
                        View view = layoutInflater.inflate(R.layout.dialog_update_contact, null);
                        builder.setView(view);
                        final AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                        final EditText et_firstName = view.findViewById(R.id.editTextFirstName);
                        final EditText et_lastName = view.findViewById(R.id.editTextLastName);
                        final EditText et_email = view.findViewById(R.id.editTextEmail);
                        final EditText et_phoneNumber = view.findViewById(R.id.editTextPhoneNumber);
                        final EditText et_Address = view.findViewById(R.id.editTextAddress);


                        et_firstName.setText(contact.getFirst_name());
                        et_lastName.setText(contact.getLast_name());
                        et_email.setText(contact.getEmail());
                        et_phoneNumber.setText(contact.getPhone_number());
                        et_Address.setText(contact.getAddress());


                        view.findViewById(R.id.update_btn).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String firstName = et_firstName.getText().toString().trim();
                                String lastName = et_lastName.getText().toString().trim();
                                String email = et_email.getText().toString().trim();
                                String phoneNumber = et_phoneNumber.getText().toString().trim();
                                String Address = et_Address.getText().toString().trim();


                                if (firstName.isEmpty()) {
                                    et_firstName.setError("This field cannot be empty");
                                    et_firstName.requestFocus();
                                    return;
                                }
                                if (lastName.isEmpty()) {
                                    et_lastName.setError("This field cannot be empty");
                                    et_lastName.requestFocus();
                                    return;
                                }
                                if (email.isEmpty()) {
                                    et_email.setError("This field cannot be empty");
                                    et_email.requestFocus();
                                    return;
                                }
                                if (phoneNumber.isEmpty()) {
                                    et_phoneNumber.setError("This field cannot be empty");
                                    et_phoneNumber.requestFocus();
                                    return;
                                }
                                if (Address.isEmpty()) {
                                    et_Address.setError("This field cannot be empty");
                                    et_Address.requestFocus();
                                    return;
                                }


                                // Room
                                contactRoomDb.contactDao().updateContact(contact.getId(),
                                        firstName, lastName, email, phoneNumber, Address);
                                loadContacts();
                                alertDialog.dismiss();
                            }
                        });
                    }
                });

        v.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteContact(contact);
            }

            private void deleteContact(final Contact contact) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //Deletes the Contact
                        contactRoomDb.contactDao().deleteContact(contact.getId());
                        loadContacts();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "The contact (" + contact.getFirst_name() + ") has not been deleted", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        Log.d(TAG, "getView: " + getCount());
        return v;
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    private void loadContacts() {

        contactList = contactRoomDb.contactDao().getAllContacts();
        notifyDataSetChanged();
       // contactCounter.contactListCounter = getCount();


    }

}
