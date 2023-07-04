package com.example.gitproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {

    EditText username, email, phone, password;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username=findViewById(R.id.username);
        email=findViewById(R.id.email);
        phone=findViewById(R.id.phone);
        password=findViewById(R.id.password);
    }

    public void registerPage(View view) {
        String st_username=username.getText().toString();
        String st_email=email.getText().toString();
        String st_password=password.getText().toString();
        String st_phone=phone.getText().toString();

        if(st_email.isEmpty()) {
            email.setError("Email Address Is Required.");
            Toast.makeText(this, "Email Address Is Required.", Toast.LENGTH_SHORT).show();
        }
        else if(st_phone.isEmpty()) {
            phone.setError("Name Is Required.");
            Toast.makeText(this, "Name Is Required.", Toast.LENGTH_SHORT).show();
        }
        else if(st_username.isEmpty()) {
            username.setError("Username Is Required.");
            Toast.makeText(this, "Username Is Required.", Toast.LENGTH_SHORT).show();
        }
        else if(st_password.length()<6) {
            password.setError("Password Must Be At Least 6 Characters.");
            Toast.makeText(this, "Password Must Be At Least 6 Characters.", Toast.LENGTH_SHORT).show();
        }
        else {
            databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.hasChild(st_phone)) {
                        Toast.makeText(RegisterActivity.this,
                                "Phone Number Is Already Registered.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        databaseReference.child("users").child(st_phone).child("User_Name").setValue(st_username);
                        databaseReference.child("users").child(st_phone).child("Phone").setValue(st_phone);
                        databaseReference.child("users").child(st_phone).child("EMail").setValue(st_email);
                        databaseReference.child("users").child(st_phone).child("Password").setValue(st_password);

                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    public void loginPage(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }
}