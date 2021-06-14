package com.pedroapp.labassingment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.pedroapp.labassingment.util.ContactLongClickHelper;

public class ContactInteraction extends AppCompatActivity {

    TextView name,email,phone,address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_interaction);

        name = findViewById(R.id.firstAndLastName);
        String firstAndLastName = ContactLongClickHelper.firstNameClicked +" "+ContactLongClickHelper.lastNameClicked;
        name.setText(firstAndLastName);

        email = findViewById(R.id.emailtext);
        email.setText(ContactLongClickHelper.emailClicked);

        phone = findViewById(R.id.phoneNumberText);
        phone.setText(ContactLongClickHelper.phoneNumberClicked);

        address = findViewById(R.id.addressText);
        address.setText(ContactLongClickHelper.addressClicked);

    }

    public void makeCall(View view) {
        String phone = ContactLongClickHelper.phoneNumberClicked;
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
        startActivity(intent);
    }

    public void sendMessage(View view) {
        String phone = ContactLongClickHelper.phoneNumberClicked;
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
        startActivity(intent);
    }

    public void sendEmail(View view) {

        String sendTo = ContactLongClickHelper.emailClicked;

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","abc@gmail.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));

    }
}