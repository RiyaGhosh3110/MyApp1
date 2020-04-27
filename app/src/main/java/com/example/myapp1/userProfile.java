package com.example.myapp1;
//FORGOT PASSWORD

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class userProfile extends AppCompatActivity {

    //TextInputLayout email,phone,password;
    //TextView fullNameLabel,usernameLabel;
    Button updateBtn;
    TextInputLayout username, password;
    //String user_username, user_name, user_email, user_phoneNo, user_password;

    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);  //removes status bar
        setContentView(R.layout.activity_user_profile);


        reference= FirebaseDatabase.getInstance().getReference("users");

//        //Hooks
//        email = findViewById(R.id.email_profile);
//        phone = findViewById(R.id.phone_no_profile);
//        password = findViewById(R.id.password_profile);
//        fullNameLabel = findViewById(R.id.fullname_field);
//        usernameLabel = findViewById(R.id.username_field);

        username=findViewById(R.id.username_profile);
        password=findViewById(R.id.password_profile);
        updateBtn=findViewById(R.id.update_btn);
        //showAllUserData();


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //updateProfile(v);
                isUser();
            }
        });

    }

    public void isUser(){

        final String userEnteredUsername = username.getEditText().getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Toast.makeText(this, userEnteredUsername, Toast.LENGTH_LONG).show();
        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    username.setError(null);
                    username.setErrorEnabled(false);
                    //password.requestFocus();
                    DatabaseReference ref=FirebaseDatabase.getInstance().getReference("users").child(userEnteredUsername).child("password");
                    ref.setValue(password.getEditText().getText().toString());
                    finish();

                }
                else {
                    username.setError("No such User exist");
                    username.requestFocus();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

/*
    private void showAllUserData(){

    Intent intent=getIntent();

        user_username = intent.getStringExtra("username");
        user_name = intent.getStringExtra("name");
        user_email = intent.getStringExtra("email");
        user_phoneNo = intent.getStringExtra("phone");
        user_password = intent.getStringExtra("password");

        fullNameLabel.setText(user_name);
        usernameLabel.setText(user_username);
        email.getEditText().setText(user_email);
        phone.getEditText().setText(user_phoneNo);
        password.getEditText().setText(user_password);
    }

    public void updateProfile(View view)
    {
        boolean check1 = isEmailChanged();
        boolean check2 = isPasswordChanged();
        boolean check3 = isPhoneChanged();
        if(check1 || check2 || check3)
        {
            Toast.makeText(this,"Data has been updated",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this,"Data is same and cannot be updated",Toast.LENGTH_LONG).show();
        }
    }

    private boolean isPhoneChanged() {
        if(!user_phoneNo.equals(phone.getEditText().getText().toString())) //update
        {
            reference.child(user_username).child("phone").setValue(phone.getEditText().getText().toString());
            return true;
        }
        else
            return false;
    }

    private boolean isPasswordChanged() {
        if(!user_password.equals(password.getEditText().getText().toString())) //update
        {
            reference.child(user_username).child("password").setValue(password.getEditText().getText().toString());
            return true;
        }
        else
            return false;
    }

    private boolean isEmailChanged() {
        if(!user_email.equals(email.getEditText().getText().toString())) //update
        {
            reference.child(user_username).child("email").setValue(email.getEditText().getText().toString());
            return true;
        }
        else
            return false;
    }

    */

}
