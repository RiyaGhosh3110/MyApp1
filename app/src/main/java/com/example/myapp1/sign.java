package com.example.myapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class sign extends AppCompatActivity {

    TextInputLayout regName,regUsername, regEmail, regPhoneNo, regPassword;
    Button regBtn,regToLoginBtn;
    RadioGroup radioGroup;
    RadioButton radioButton;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);  //removes status bar
        setContentView(R.layout.activity_sign);


        //Hooks
        regName=findViewById(R.id.reg_name);
        regUsername=findViewById(R.id.reg_username);
        regEmail=findViewById(R.id.reg_email);
        regPhoneNo=findViewById(R.id.reg_phoneNo);
        regPassword=findViewById(R.id.reg_password);
        regBtn=findViewById(R.id.reg_btn);
        regToLoginBtn=findViewById(R.id.reg_login_btn);
        radioGroup=findViewById(R.id.radioGroup);





        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode=FirebaseDatabase.getInstance();
                reference=rootNode.getReference("users");

                registerUser(v);
            }
        });

        regToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(sign.this,login.class);
                startActivity(intent);
            }
        });
    }

    private Boolean userExists(String val){
        final boolean[] check = new boolean[1];
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = ref.orderByChild("username").equalTo(val);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    Toast.makeText(sign.this,"USER EXISTS",Toast.LENGTH_LONG).show();
                    check[0] =false;
                }
                else
                {
                    Toast.makeText(sign.this,"USER NOT EXISTS",Toast.LENGTH_LONG).show();
                    check[0]=true;
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Toast.makeText(sign.this,""+check[0],Toast.LENGTH_LONG).show();
        return check[0];
    }

    private Boolean validateName(){
        String value=regName.getEditText().getText().toString();

        if(value.isEmpty())
        {
            regName.setError("Field cannot be empty");
            return false;
        }
        else
        {
            regName.setError(null); //to empty the error field
            regName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUserName(){
        String value=regUsername.getEditText().getText().toString();

        String user="[A-Za-z0-9]+";
        //String noWhiteSpace = "\\A\\w{4,20}\\z";

        if(value.isEmpty())
        {
            regUsername.setError("Field cannot be empty");
            return false;
        }
        else if(value.length()>=15)
        {
            regUsername.setError("Username too Long");
            return false;
        }
        else
            //            if (!user.matcher(value).find())
        if(!value.matches(user))
        {
            regUsername.setError("Invalid Username, use only letters and digits");
            return false;
        }
//        else if(userExists(value)) {
//            Toast.makeText(sign.this,"USER EXISTS",Toast.LENGTH_LONG).show();
//            regUsername.setError("User exists!");
//            return false;
//        }
        else
        {
            regUsername.setError(null); //to empty the error field
            regUsername.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = regEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            regEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            regEmail.setError("Invalid email address");
            return false;
        } else {
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePhoneNo() {
        String val = regPhoneNo.getEditText().getText().toString();

        if (val.isEmpty()) {
            regPhoneNo.setError("Field cannot be empty");
            return false;
        } else {
            regPhoneNo.setError(null);
            regPhoneNo.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = regPassword.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            regPassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            regPassword.setError("Password is too weak");
            return false;
        } else {
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }
    }

    public void registerUser(View view)
    {

        //Get all the values

        String username=regUsername.getEditText().getText().toString();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");

        Query checkUser = ref.orderByChild("username").equalTo(username);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
                    regUsername.setError("User exists!");
                    regUsername.requestFocus();
                }
                else
                {
                    if(!validateName() |!validatePassword() | !validatePhoneNo() | !validateEmail() | !validateUserName())
                    {
                        return;
                    }

                    //radiobutton
                    int radioId = radioGroup.getCheckedRadioButtonId();
                    radioButton = findViewById(radioId);
                    Toast.makeText(sign.this, "Selected User as: " + radioButton.getText(),Toast.LENGTH_SHORT).show();

                    String name=regName.getEditText().getText().toString();
                    String email=regEmail.getEditText().getText().toString();
                    String phone=regPhoneNo.getEditText().getText().toString();
                    String password=regPassword.getEditText().getText().toString();
                    String type=radioButton.getText().toString().equals("Customer")?"Customer":"Vendor";

                    UserHelperClass helperClass=new UserHelperClass(name,username,email,phone,password,type);
                    reference.child(username).setValue(helperClass);

                    if(type.equals("Customer")) {
                        Master m = new Master();
                        reference.child(username).child("Master").setValue(m);
                    }
                    else
                    {
                        Vendor v = new Vendor();
                        reference.child(username).child("Vendor").setValue(v);
                    }
                    Toast.makeText(getApplicationContext(),"User Successfully Registered", Toast.LENGTH_LONG).show();

                    Intent intent=new Intent(sign.this,login.class);
                    startActivity(intent);
                    finish();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(radioId);

        Toast.makeText(this, "Selected User as: " + radioButton.getText(),
                Toast.LENGTH_SHORT).show();
    }
}
