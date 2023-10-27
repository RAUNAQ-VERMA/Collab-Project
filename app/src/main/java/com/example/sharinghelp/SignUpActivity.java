package com.example.sharinghelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sharinghelp.users.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpActivity extends AppCompatActivity {

    //todo: Add google and facebook login

    private Button submit_btn;
    private EditText username,email,phoneNumber,password,confirmPassword;
    private TextView error, login;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        login = findViewById(R.id.textViewLogin);
        submit_btn = findViewById(R.id.submit);
        username = findViewById(R.id.editTextUsername);
        email = findViewById(R.id.editTextEmail);
        phoneNumber = findViewById(R.id.editTextPhoneNumber);
        password = findViewById(R.id.editTextTextPassword);
        confirmPassword = findViewById(R.id.editTextConfirmPassword);
        error = findViewById(R.id.textViewError);
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        //SignUp----------------------------------------------------------------------------
        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                error.setText("");
                if(!TextUtils.isEmpty(username.getText()) && !TextUtils.isEmpty(email.getText()) && !TextUtils.isEmpty(phoneNumber.getText()) && !TextUtils.isEmpty(password.getText()) && !TextUtils.isEmpty(confirmPassword.getText())){
                    if(password.getText().toString().equals(confirmPassword.getText().toString())){
                        if(password.length()>=6){
                            mAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if(task.isSuccessful()){
                                        User user = new User(username.getText().toString(),email.getText().toString(),phoneNumber.getText().toString(),password.getText().toString());
                                        String id = task.getResult().getUser().getUid();
                                        database.getReference().child("User").child(id).setValue(user);
                                        Log.d("mylog","User Created");
                                        startActivity(new Intent(SignUpActivity.this,MainActivity.class));
                                    }
                                    else{
                                        Log.d("mylog",task.getException().getMessage());
                                    }
                                }
                            });
                        }
                        else{
                            error.setText("!!Password must be atleast 6 characters long!!");
                        }
                    }
                    else{
                        error.setText("!!Passwords do not match!!");
                    }
                }
                else{
                    error.setText("!!Fill all fields!!");
                }
            }
        });
        //----------------------------------------------------------------------------------
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
            }
        });
    }
}