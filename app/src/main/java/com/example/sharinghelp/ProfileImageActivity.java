package com.example.sharinghelp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.drjacky.imagepicker.ImagePicker;
import com.github.drjacky.imagepicker.constant.ImageProvider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.annotations.NotNull;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public class ProfileImageActivity extends AppCompatActivity {

    ImageView profileImage,coverImage;
    FloatingActionButton editProfile, editCover;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_image);

        save = findViewById(R.id.save);
        profileImage = findViewById(R.id.profileImage);
        coverImage = findViewById(R.id.coverImage);
        editProfile = findViewById(R.id.edit_profileImage);
        editCover = findViewById(R.id.edit_coverImage);

        editProfile.setOnClickListener(new View.OnClickListener() {
            ActivityResultLauncher<Intent> launcher=
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),(ActivityResult result)->{
                    if(result.getResultCode()==RESULT_OK){
                        Uri uri=result.getData().getData();
                        // Use the uri to load the image
                        profileImage.setImageURI(uri);

                    }else if(result.getResultCode()==ImagePicker.RESULT_ERROR){
                        // Use  to show an error
                        Toast.makeText(ProfileImageActivity.this, ImagePicker.Companion.getError(result.getData())+"", Toast.LENGTH_SHORT).show();
                    }
                });
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(ProfileImageActivity.this)
                        .crop(1f,1f)
                        .cropOval()
                        .maxResultSize(512,512,true)
                        .provider(ImageProvider.BOTH) //Or bothCameraGallery()
                        .createIntentFromDialog(new Function1(){
                            public Object invoke(Object var1){
                                this.invoke((Intent)var1);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(@NotNull Intent it){
                                Intrinsics.checkNotNullParameter(it,"it");
                                launcher.launch(it);
                            }
                        });
            }
        });
        editCover.setOnClickListener(new View.OnClickListener() {
            ActivityResultLauncher<Intent> launcher=
                    registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),(ActivityResult result)->{
                        if(result.getResultCode()==RESULT_OK){
                            Uri uri=result.getData().getData();
                            // Use the uri to load the image
                            coverImage.setImageURI(uri);

                        }else if(result.getResultCode()==ImagePicker.RESULT_ERROR){
                            // Use  to show an error
                            Toast.makeText(ProfileImageActivity.this, ImagePicker.Companion.getError(result.getData())+"", Toast.LENGTH_SHORT).show();
                        }
                    });
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(ProfileImageActivity.this)
                        .crop(4f,1f)
                        .cropOval()
                        .maxResultSize(512,512,true)
                        .provider(ImageProvider.BOTH) //Or bothCameraGallery()
                        .createIntentFromDialog(new Function1(){
                            public Object invoke(Object var1){
                                this.invoke((Intent)var1);
                                return Unit.INSTANCE;
                            }
                            public final void invoke(@NotNull Intent it){
                                Intrinsics.checkNotNullParameter(it,"it");
                                launcher.launch(it);
                            }
                        });
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo: Add to database and set image in ProfileActivity

                Intent intent  = new Intent(ProfileImageActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });

    }
}