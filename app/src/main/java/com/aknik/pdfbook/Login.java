package com.aknik.pdfbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText mPassword , mEmail;
    Button mlogin;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPassword=findViewById(R.id.passwordE);
        mEmail=findViewById(R.id.Email);
        fAuth= FirebaseAuth.getInstance();
        mlogin=findViewById(R.id.LoginBtn);
        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=mEmail.getText().toString().trim();
                String password=mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("email is required");
                    return;
                }

                if(password.length()<8){
                    mPassword.setError("password Must be >=8 characters");
                    return;
                }


//  entre to my conte
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this , "logen in succsfully",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }else {
                            Toast.makeText(Login.this , "Error !"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }


}

    /*
    EditText mPassword , mUser;
    Button mlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mPassword=findViewById(R.id.passwordE);
        mUser=findViewById(R.id.User);
        mlogin=findViewById(R.id.LoginBtn);
        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mUser.getText().toString();
                String password = mPassword.getText().toString();

                String USERNAME="admin";
                String PASSWORD = "1234567890";
                if (user.equals(USERNAME)==false) {
                    mUser.setError("Username is required");
                    return;
                }

                if ((password.equals(PASSWORD))==false) {
                    mPassword.setError("password is required");
                    return;
                }

                if(user.equals(USERNAME)==true && password.equals(PASSWORD)==true){
                    Intent intent = new Intent(Login.this,MainActivity.class);
                    startActivity(intent);
                }
            }


        });
    }



}*/