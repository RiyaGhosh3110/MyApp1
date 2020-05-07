package com.example.myapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class updateVegVendor extends AppCompatActivity {

    TextInputLayout upVeg,upWeight,upPrice;
    Button submit,back;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_veg_vendor);

        upVeg=findViewById(R.id.veg_name);
        upWeight=findViewById(R.id.veg_weight);
        upPrice=findViewById(R.id.veg_price);
        submit=findViewById(R.id.submit);
        back=findViewById(R.id.back);


        Intent intent=getIntent();
        String username=intent.getStringExtra("username");
        String veg = intent.getStringExtra("vegetable");

        upVeg.getEditText().setText(veg);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!validateVegetableWeight() || !validateVegetablePrice())
                    return;

                reference= FirebaseDatabase.getInstance().getReference("users").child(username).child("Vendor");
                reference.child(veg).child("weight").setValue(upWeight.getEditText().getText().toString().trim());
                reference.child(veg).child("price").setValue(upPrice.getEditText().getText().toString().trim());
                Toast.makeText(updateVegVendor.this,"Vegetable Successfully Updated",Toast.LENGTH_LONG).show();
                Intent in=new Intent(updateVegVendor.this,viewInfoVendor.class);
                in.putExtra("username",username);
                startActivity(in);
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(updateVegVendor.this,viewInfoVendor.class);
                in.putExtra("username",username);
                startActivity(in);
                finish();
            }
        });
    }

    private Boolean validateVegetableWeight() {
        String val = upVeg.getEditText().getText().toString();
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
            return true;
        }
    }

    private Boolean validateVegetablePrice() {
        String val = upPrice.getEditText().getText().toString();
        if (val.isEmpty()) {
            upPrice.setError("Field cannot be empty");
            return false;
        }
        else if(val.equals("0")){
            upPrice.setError("Invalid Input");
            return false;
        }
        else {
            upPrice.setError(null);
            return true;
        }
    }
}
