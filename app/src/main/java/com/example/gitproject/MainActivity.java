package com.example.gitproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
<<<<<<< HEAD
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String st_phone;
    RecyclerView recyclerView;
    ArrayList<UserClass> arrayList;
    AdapterClass adapterClass;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
=======

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView menu;
    HomeFragment homeFragment=new HomeFragment();
    AddFragment addFragment = new AddFragment();
>>>>>>> esha

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        arrayList= new ArrayList<>();
        adapterClass= new AdapterClass(this, arrayList);
        recyclerView.setAdapter(adapterClass);

        Button buttonAdd = findViewById(R.id.ADD_ADDButton);

        buttonAdd.setOnClickListener(view-> startActivity(new Intent(this,AddActivity.class)));

        FirebaseAuth auth = FirebaseAuth.getInstance();
        databaseReference.child("users").child(auth.getCurrentUser().getUid())
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

=======
        menu=findViewById(R.id.h_menu);

        getSupportFragmentManager().beginTransaction().replace(R.id.h_frame, homeFragment).commit();

        menu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.h_home) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.h_frame, homeFragment).commit();
                }
                if(item.getItemId()==R.id.h_add) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.h_frame, addFragment).commit();
                }
                return true;
            }
        });
>>>>>>> esha
    }
}