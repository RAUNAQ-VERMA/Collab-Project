package com.example.sharinghelp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sharinghelp.adapters.Profile_RecyclerViewAdapter;

public class ProfileActivity extends AppCompatActivity {

    ImageView back;
    TextView edit;

    RecyclerView recyclerView;
    Profile_RecyclerViewAdapter adapter;

    public String[] dataSet = {"Description....","Description....","Description....","Description....","Description....","Description....","Description....","Description....","Description....","Description....","Description....","Description....","Description....","Description....","Description....","Description....","Description....","Description....","Description....","Description....","Description....","Description...."};
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        back = findViewById(R.id.back_btn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getParentActivityIntent()));

            }
        });

        recyclerView = findViewById(R.id.profile_recyclerView);
        adapter  = new Profile_RecyclerViewAdapter(dataSet);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);

        edit = findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, ProfileImageActivity.class));
            }
        });

    }
}