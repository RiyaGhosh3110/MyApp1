package com.example.myapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
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
import java.util.List;
import java.util.Objects;

public class updateInfo extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    DatabaseReference reference;
    TextInputLayout vegetableWeight;
    Button submit,back;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);  //removes status bar
        setContentView(R.layout.activity_update_info);
        vegetableWeight = findViewById(R.id.veg_weight);
        submit=findViewById(R.id.submit);
        back=findViewById(R.id.back);
        spinner=findViewById(R.id.sel_veg);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");

        List<String> veg_list=new ArrayList<>();
        veg_list.add("abc");
        veg_list.add("pqr");
        veg_list.add("xyz");

//        reference = FirebaseDatabase.getInstance().getReference("users").child(username).child("Master");
//        reference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot s:dataSnapshot.getChildren()) {
//                    String key=s.getKey();
//                    String value= Objects.requireNonNull(dataSnapshot.child(Objects.requireNonNull(key)).getValue()).toString().trim();
//                    if(value.equals("0"))
//                        veg_list.add(key);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        String str="";
        for(String k:veg_list)
            str=str+" "+k;

        Toast.makeText(updateInfo.this,str,Toast.LENGTH_LONG).show();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, veg_list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //spinner.setPrompt("Select Vegetable!");
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        submit.setOnClickListener((View V)->{
            if(!validateVegetableWeight() ) //!validateSpinner() ||
                return;

            //String vegetable_name = spinner.getSelectedItem().toString().trim();
            Toast.makeText(updateInfo.this,"vegetable_name",Toast.LENGTH_LONG).show();
//            String vegetable_name = vegetableName.getEditText().getText().toString().trim();
           // reference.child(vegetable_name).setValue(vegetableWeight.getEditText().getText().toString().trim());

            Intent intent1 = new Intent( updateInfo.this,viewInfo.class);
            //intent1.putExtra("username", username);
            startActivity(intent1);
            finish();

        });

        back.setOnClickListener((View v)->{
            Intent intent1 = new Intent( updateInfo.this,viewInfo.class);
            startActivity(intent1);
            finish();
        });

    }


//    private Boolean validateVegetableName() {
//        String val = vegetableName.getEditText().getText().toString();
//        if (val.isEmpty()) {
//            vegetableName.setError("Field cannot be empty");
//            return false;
//        } else {
//            vegetableName.setError(null);
//            vegetableName.setErrorEnabled(false);
//            return true;
//        }
//    }

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

    boolean validateSpinner(){
        if(spinner.getSelectedItem().toString().trim().equals("Select Vegetable!"))
        {
            Toast.makeText(updateInfo.this,"Select a Vegetable",Toast.LENGTH_LONG).show();
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text=parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
