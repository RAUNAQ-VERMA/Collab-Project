package com.example.sharinghelp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private Button submit_btn;
    private EditText email ,password;
    private TextView error , signup;
    private FirebaseAuth mAuth;
// ...
// Initialize Firebase Auth

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.editTextEmail2);
        password = findViewById(R.id.editTextTextPassword2);
        error = findViewById(R.id.textViewError2);
        signup = findViewById(R.id.textViewSignup);
        submit_btn = findViewById(R.id.submit2);

        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()){
                    mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        error.setText("!!Login Failed, Try Again!!");
                                        Log.d("mylog",task.getException().getMessage());
                                    }
                                }
                            });
                }
                else{
                    error.setText("!!Fill all fields!!");
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
            }
        });
    }
}