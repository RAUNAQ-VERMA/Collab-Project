package com.example.sharinghelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.sharinghelp.adapters.Home_RecyclerViewAdapter;
import com.example.sharinghelp.adapters.Posts_RecyclerViewAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class PostsActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Posts_RecyclerViewAdapter adapter;
    RecyclerView recyclerView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    String []  helpRequired = {"Help Require","Help Require","Help Require","Help Require","Help Require","Help Require","Help Require","Help Require","Help Require","Help Require","Help Require","Help Require","Help Require","Help Require","Help Require","Help Require","Help Require","Help Require","Help Require","Help Require","Help Require","Help Require","Help Require"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);

        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Posts_RecyclerViewAdapter(helpRequired);
        recyclerView.setAdapter(adapter);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.posts);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.posts:
                        return true;
                    case R.id.request:
                        startActivity(new Intent(getApplicationContext(),RequestActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.socials:
                        startActivity(new Intent(getApplicationContext(),SocialsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        // Navigation drawer--------------------------------------------------------
        drawerLayout = findViewById(R.id.drawerLayout2);
        navigationView = findViewById(R.id.navigationView2);
        toolbar = findViewById(R.id.toolbar2);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_open,R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //toolbar---------
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                startActivity(new Intent(PostsActivity.this,ProfileActivity.class));
                return true;
            }
        });
    }
}