package com.example.gitproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddActivity extends AppCompatActivity {
    EditText sys, dias, rate, comment;
    String str_phone;
    Button add;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        sys=findViewById(R.id.sys);
        dias=findViewById(R.id.dias);
        rate=findViewById(R.id.rate);
        comment=findViewById(R.id.comment);
        add=findViewById(R.id.add);

        Intent intent=getIntent();
        str_phone=intent.getStringExtra("Phone");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String st_sys=sys.getText().toString();
                String st_dias=dias.getText().toString();
                String st_rate=rate.getText().toString();
                String st_time=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
                String st_comment=comment.getText().toString();

                databaseReference.child("users").child(str_phone).child(st_time).child("Systolic").setValue(st_sys);
                databaseReference.child("users").child(str_phone).child(st_time).child("Diastolic").setValue(st_dias);
                databaseReference.child("users").child(str_phone).child(st_time).child("Heart_Rate").setValue(st_rate);
                databaseReference.child("users").child(str_phone).child(st_time).child("Comment").setValue(st_comment);
                databaseReference.child("users").child(str_phone).child(st_time).child("Time").setValue(st_time);
            }
        });
    }
}