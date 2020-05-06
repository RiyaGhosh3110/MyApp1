package com.example.myapp1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class viewInfoVendor extends AppCompatActivity {

    DatabaseReference reference;
    ListView list;
    FloatingActionButton add;
    Button logout;
    TextView welcome;
    Dialog myDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);  //removes status bar
        setContentView(R.layout.activity_view_info_vendor);

        list = findViewById(R.id.listView);
        add=findViewById(R.id.floatingActionButton);
        logout=findViewById(R.id.logout);
        welcome=findViewById(R.id.welcome);

        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");
        welcome.setText("Hello "+ username+"\nWelcome back (vendor)!");
        reference = FirebaseDatabase.getInstance().getReference("users").child(username).child("Vendor");

        reference.addValueEventListener(new ValueEventListener() {
            ArrayList<String> vegename=new ArrayList<>();
            ArrayList<String> weight=new ArrayList<>();
            ArrayList<String> price=new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot subSnap: dataSnapshot.getChildren()){
                    String v = subSnap.getKey();
                    String w = subSnap.child("weight").getValue(String.class);
                    String p = subSnap.child("price").getValue(String.class);
                    if(!w.equals("0"))
                    {
                        vegename.add(v);
                        weight.add(w);
                        price.add(p);
                    }

                }

                MyAdapter adapter = new MyAdapter(viewInfoVendor.this, vegename, weight,price);
                list.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        add.setOnClickListener((View V)->{

            Intent intent1 = new Intent(viewInfoVendor.this, updateInfoVendor.class);
            intent1.putExtra("username", username);
            startActivity(intent1);
            finish();
        });

        logout.setOnClickListener((View V)->{

            Intent intent1 = new Intent(viewInfoVendor.this, login.class);
            startActivity(intent1);
            finish();
        });
    }
}

class MyAdapter extends ArrayAdapter<String> {

    Context context;
    ArrayList<String> rVegetableName;
    ArrayList<String> rWeight;
    ArrayList<String> rPrice;

    MyAdapter (Context c, ArrayList<String> vegetableName, ArrayList<String> weight, ArrayList<String> price) {
        super(c, R.layout.row_vendor, R.id.textView1, vegetableName);
        this.context = c;
        this.rVegetableName = vegetableName;
        this.rWeight = weight;
        this.rPrice = price;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row_vendor = layoutInflater.inflate(R.layout.row_vendor, parent, false);
        TextView myVegetableName = row_vendor.findViewById(R.id.textView1);
        TextView myWeight = row_vendor.findViewById(R.id.textView2);
        TextView myPrice = row_vendor.findViewById(R.id.textView3);
        // now set our resources on views
        myVegetableName.setText(rVegetableName.get(position));
        myWeight.setText("Weight: "+rWeight.get(position));
        myPrice.setText("Price: "+rPrice.get(position));


        return row_vendor;
    }
}

class Values{
    String vegename;
    String weight;
    String price;
    public Values(String vegename, String weight, String price){
        this.vegename = vegename;
        this.weight = weight;
        this.price = price;
    }

    public String getVegename() {
        return vegename;
    }

    public void setVegename(String vegename) {
        this.vegename = vegename;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
