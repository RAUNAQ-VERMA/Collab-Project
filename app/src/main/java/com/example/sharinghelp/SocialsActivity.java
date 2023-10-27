package com.example.sharinghelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class SocialsActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socials);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.socials);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.posts:
                        startActivity(new Intent(getApplicationContext(),PostsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.request:
                        startActivity(new Intent(getApplicationContext(),RequestActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.socials:
                        return true;
                }
                return false;
            }
        });

        // Navigation drawer--------------------------------------------------------
        drawerLayout = findViewById(R.id.drawerLayout3);
        navigationView = findViewById(R.id.navigationView3);
        toolbar = findViewById(R.id.toolbar3);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_open,R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //toolbar----------------------
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                startActivity(new Intent(SocialsActivity.this,ProfileActivity.class));
                return true;
            }
        });
    }
}