package com.example.myapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    TextInputEditText vegetablename;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);
        vegetableWeight = findViewById(R.id.veg_weight);
        vegetablename=findViewById(R.id.veg_name);
        submit=findViewById(R.id.submit);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        reference = FirebaseDatabase.getInstance().getReference("users").child(username).child("Master");

        submit.setOnClickListener((View V)->{
            String vegetable_name = vegetablename.getText().toString().trim();
//            Toast.makeText(getApplicationContext(),vegetable_name,Toast.LENGTH_LONG).show();
            reference.child(vegetable_name).setValue(vegetableWeight.getEditText().getText().toString().trim());
//            Log.i("myMessage", reference.child(vegetable_name).toString());
            finish();

        });

    }
}
