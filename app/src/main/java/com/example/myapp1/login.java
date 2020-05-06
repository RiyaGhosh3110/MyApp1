package com.example.myapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class login extends AppCompatActivity {

    Button callSignUp,callLoginUser,callForgotPass;
    ImageView logo;
    TextView title;
    TextInputLayout username,password;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);  //removes status bar
        setContentView(R.layout.activity_login);

        //Hooks
        callSignUp=findViewById(R.id.signUpScreen);
        callLoginUser=findViewById(R.id.login_btn);
        callForgotPass=findViewById(R.id.forgotPassword);
        logo=findViewById(R.id.logo_image);
        title=findViewById(R.id.logo_name);
        username = findViewById(R.id.username);
        password=findViewById(R.id.password);

        callForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(login.this,userProfile.class);
                startActivity(intent);
            }
        });

        callSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(login.this,sign.class);
//                Pair[] pairs=new Pair[2];
//                pairs[0]=new Pair<View,String>(logo,"logo_image");
//                pairs[1]=new Pair<View,String>(title,"logo_text");
//                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(login.this,pairs);
                startActivity(intent);
                finish();
            }
        });

        callLoginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginUser(v);

            }
        });

    }


    private Boolean validateUsername() {
        String val = username.getEditText().getText().toString();
        if (val.isEmpty()) {
            username.setError("Field cannot be empty");
            return false;
        } else {
            username.setError(null);
            username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = password.getEditText().getText().toString();
        if (val.isEmpty()) {
            password.setError("Field cannot be empty");
            return false;
        } else {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }


    public void loginUser(View view) {
        //Validate Login Info
        if (!validateUsername() | !validatePassword())
            return;
        else
            isUser();
    }


    public void isUser(){

        final String userEnteredUsername = username.getEditText().getText().toString().trim();
        final String userEnteredPassword = password.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = reference.orderByChild("username").equalTo(userEnteredUsername);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    username.setError(null);
                    username.setErrorEnabled(false);

                    String passwordFromDB = dataSnapshot.child(userEnteredUsername).child("password").getValue(String.class);

                    if (passwordFromDB.equals(userEnteredPassword)) {
                        username.setError(null);
                        username.setErrorEnabled(false);

                        String usernameFromDB = dataSnapshot.child(userEnteredUsername).child("username").getValue(String.class);

                        String user_type=dataSnapshot.child(userEnteredUsername).child("type").getValue(String.class);
                        Toast.makeText(login.this,"User Type: "+user_type,Toast.LENGTH_LONG).show();
                        if(user_type.equals("Customer"))
                        {
                            Intent intent = new Intent(getApplicationContext(), viewInfo.class);
                            intent.putExtra("username", usernameFromDB);
                            startActivity(intent);
                            finish();
                        }
                        else
                        {
                            Intent intent = new Intent(getApplicationContext(), viewInfoVendor.class);
                            intent.putExtra("username", usernameFromDB);
                            startActivity(intent);
                            finish();
                        }
                    }
                    else {
                        //progressBar.setVisibility(View.GONE);
                        password.setError("Wrong Password");
                        password.requestFocus();
                    }
                }
                else {
                    //progressBar.setVisibility(View.GONE);
                    username.setError("No such User exist");
                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
