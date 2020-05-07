package com.example.myapp1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class viewInfo extends AppCompatActivity {

    DatabaseReference reference;
    ListView list;
    FloatingActionButton add;
    Button logout;
    TextView welcome;

    Dialog myDialog;
    Button delete,update;
    FloatingActionButton close;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);  //removes status bar

        setContentView(R.layout.activity_view_info);

        list = (ListView) findViewById(R.id.listView);
        add=findViewById(R.id.floatingActionButton);
        logout=findViewById(R.id.logout);
        welcome=findViewById(R.id.welcome);

        Intent intent = getIntent();
        final String username = intent.getStringExtra("username");
        welcome.setText("Hello "+ username+"\nWelcome back (customer)!");
        reference = FirebaseDatabase.getInstance().getReference("users").child(username).child("Master");
        reference.addValueEventListener(new ValueEventListener() {
            ArrayList<String> keys = new ArrayList<String>();
            ArrayList<String> values = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot subSnap : dataSnapshot.getChildren())
//                    keys.add(subSnap.getKey());
//                for (String key : keys) {
//                    String value = dataSnapshot.child(key).getValue().toString();
//                    values.add(value);
//                }

                for(DataSnapshot subSnap: dataSnapshot.getChildren()) {
                       String value=dataSnapshot.child(subSnap.getKey()).getValue().toString();
                       if(!value.equals("0"))
                        {
                            keys.add(subSnap.getKey());
                            values.add((String) subSnap.getValue());
                        }
                }
                MyAdapter adapter = new MyAdapter(viewInfo.this, keys, values);
                list.setAdapter(adapter);

                list.setOnItemClickListener((parent, view, position, id) -> {

                    myDialog =new Dialog(viewInfo.this);
                    myDialog.setContentView(R.layout.activity_popup_view_info);
                    myDialog.setTitle("My Custom Dialog");

                    delete=(Button)myDialog.findViewById(R.id.delete);
                    update=(Button)myDialog.findViewById(R.id.update);
                    close=(FloatingActionButton)myDialog.findViewById(R.id.close);

                    delete.setEnabled(true);
                    update.setEnabled(true);
                    close.setEnabled(true);

                    delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String vegName = (String) parent.getItemAtPosition(position);
                            reference.child(vegName).setValue("0");
                            finish();
                            startActivity(getIntent());
                            //Toast.makeText(viewInfo.this,"Delete",Toast.LENGTH_LONG).show();
                        }
                    });

                    update.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String selectedItem = (String) parent.getItemAtPosition(position);
                            Intent in = new Intent(viewInfo.this, updateVeg.class);
                            in.putExtra("vegetable", selectedItem);
                            in.putExtra("username",username);
                            startActivity(in);
                            finish();
                        }
                    });

                    close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myDialog.cancel();
                        }
                    });


                    myDialog.show();

                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        add.setOnClickListener((View V)->{

                Intent intent1 = new Intent(viewInfo.this, updateInfo.class);
                intent1.putExtra("username", username);
                startActivity(intent1);
                finish();
        });

        logout.setOnClickListener((View V)->{

            Intent intent1 = new Intent(viewInfo.this, login.class);
            startActivity(intent1);
            finish();
        });
    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        ArrayList<String> rTitle;
        ArrayList<String> rDescription;

        MyAdapter (Context c, ArrayList<String> title, ArrayList<String> description) {
            super(c, R.layout.row, R.id.textView1, title);
            this.context = c;
            this.rTitle = title;
            this.rDescription = description;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            TextView myTitle = row.findViewById(R.id.textView1);
            TextView myDescription = row.findViewById(R.id.textView2);

            // now set our resources on views
            myTitle.setText(rTitle.get(position));
            myDescription.setText(rDescription.get(position));


            return row;
        }
    }

    public void vegetableInformationPopup() {


    }
}


