package com.example.myapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class updateInfo extends AppCompatActivity {

    DatabaseReference reference,ref;
    TextInputLayout vegetableWeight;
    TextInputLayout vegetableName;
    Button submit,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);  //removes status bar
        setContentView(R.layout.activity_update_info);
        vegetableWeight = findViewById(R.id.veg_weight);
        vegetableName=findViewById(R.id.veg_name);
        submit=findViewById(R.id.submit);
        back=findViewById(R.id.back);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        reference = FirebaseDatabase.getInstance().getReference("users").child(username).child("Master");

        submit.setOnClickListener((View V)->{
            if(!validateVegetableName() || !validateVegetableWeight() )
                return;

            String vegetable_name = vegetableName.getEditText().getText().toString().trim();
            reference.child(vegetable_name).setValue(vegetableWeight.getEditText().getText().toString().trim());

            Intent intent1 = new Intent( updateInfo.this,viewInfo.class);
            intent1.putExtra("username", username);
            startActivity(intent1);
            finish();

        });

        back.setOnClickListener((View v)->{
            Intent intent1 = new Intent( updateInfo.this,viewInfo.class);
            startActivity(intent1);
            finish();
        });

    }


    private Boolean validateVegetableName() {
        String val = vegetableName.getEditText().getText().toString();
        if (val.isEmpty()) {
            vegetableName.setError("Field cannot be empty");
            return false;
        } else {
            vegetableName.setError(null);
            vegetableName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateVegetableWeight() {
        String val = vegetableWeight.getEditText().getText().toString();
        if (val.isEmpty()) {
            vegetableWeight.setError("Field cannot be empty");
            return false;
        } else {
            vegetableWeight.setError(null);
            vegetableWeight.setErrorEnabled(false);
            return true;
        }
    }
}
