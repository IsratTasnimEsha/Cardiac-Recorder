package com.example.gitproject;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    String st_phone;
    RecyclerView recyclerView;
    ArrayList<UserClass> arrayList;
    AdapterClass adapterClass;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent1 = getIntent();
        st_phone = intent1.getStringExtra("Phone");

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrayList= new ArrayList<>();
        adapterClass= new AdapterClass(this, arrayList);
        recyclerView.setAdapter(adapterClass);

        databaseReference.child("users").child(st_phone)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        arrayList.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            UserClass userClass = dataSnapshot.getValue(UserClass.class);
                            arrayList.add(userClass);
                        }
                        adapterClass.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}