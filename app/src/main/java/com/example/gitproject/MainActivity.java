package com.example.gitproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView menu;
    HomeFragment homeFragment=new HomeFragment();
    AddFragment addFragment = new AddFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    }
}