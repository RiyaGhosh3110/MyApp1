package com.example.myapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class updateVeg extends AppCompatActivity {

    TextInputLayout upVeg,upWeight;
    Button submit,back;
    DatabaseReference reference;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);  //removes status bar
        setContentView(R.layout.activity_update_veg);

        upVeg=findViewById(R.id.veg_name);
        upWeight=findViewById(R.id.veg_weight);
        submit=findViewById(R.id.submit);
        back=findViewById(R.id.back);


        Intent intent=getIntent();
        String username=intent.getStringExtra("username");
        String veg = intent.getStringExtra("vegetable");

        upVeg.getEditText().setText(veg);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!validateVegetableWeight()) {
                    return;
                }

                reference= FirebaseDatabase.getInstance().getReference("users").child(username).child("Master");
                reference.child(veg).setValue(upWeight.getEditText().getText().toString().trim());
                Toast.makeText(updateVeg.this,"Vegetable Weight Successfully Updated",Toast.LENGTH_LONG).show();
                Intent in=new Intent(updateVeg.this,viewInfo.class);
                in.putExtra("username",username);
                startActivity(in);
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(updateVeg.this,viewInfo.class);
                in.putExtra("username",username);
                startActivity(in);
                finish();
            }
        });

    }

    private Boolean validateVegetableWeight() {
        String val = upVeg.getEditText().getText().toString();
        Toast.makeText(updateVeg.this,"VALUE: "+val,Toast.LENGTH_LONG).show();
        if (val.isEmpty()) {
            upVeg.setError("Field cannot be empty");
            return false;
        }
        else if(val.equals("0") ) {
            upVeg.setError("Invalid Input");
            return false;
        }
        else {
            upVeg.setError(null);
            upVeg.setErrorEnabled(false);
            return true;
        }
    }
}
