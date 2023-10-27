package com.example.sharinghelp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharinghelp.adapters.Home_RecyclerViewAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    NavigationView navigationView;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigation;

    FirebaseAuth mAuth;

    RecyclerView recyclerView;
    CircleImageView profile;

    String []  helpRequired = {"Help Required","Help Required","Help Required","Help Required","Help Required","Help Required","Help Required","Help Required","Help Required","Help Required","Help Required","Help Required","Help Required"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        profile = findViewById(R.id.profile_main);

        //Recycler View Adapter-------------------------------------------------------
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager  manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        Home_RecyclerViewAdapter adapter = new Home_RecyclerViewAdapter(helpRequired);
        recyclerView.setAdapter(adapter);
        //-------------------------------------------------------

        // For bottom navigation-----------------------------------------
        bottomNavigation = findViewById(R.id.bottomNavigationView);
        bottomNavigation.setSelectedItemId(R.id.home);


        //For tool bar----------------------------------------------------
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ProfileActivity.class));
            }
        });

        // Navigation drawer--------------------------------------------------------
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_open,R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Bottom navigation Listener------------------------
        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home:
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
                        startActivity(new Intent(getApplicationContext(),SocialsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
        //---------------------------------------------------

        navigationView.setNavigationItemSelectedListener(this);
        mAuth = FirebaseAuth.getInstance();

    }
    //Login/Signup Check--------------------------------------------

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            startActivity(new Intent(MainActivity.this,SignUpActivity.class));
        }
    }
    //--------------------------------------------------------------
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(R.id.logout == item.getItemId()){
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this,SignUpActivity.class));
            return true;
        }
        return false;
    }
}