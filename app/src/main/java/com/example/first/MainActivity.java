package com.example.first;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class MainActivity extends AppCompatActivity {

    EditText name,phone,age;
    Button upload;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ref = FirebaseDatabase.getInstance().getReference("Database").child("Users");
        name=(EditText)findViewById(R.id.Name);
        phone=(EditText)findViewById(R.id.Phone);
        age=(EditText)findViewById(R.id.Age);
        upload=(Button)findViewById(R.id.upload_btn);

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(name.getText().toString())==false&&TextUtils.isEmpty(phone.getText().toString())==false&&TextUtils.isEmpty(age.getText().toString())==false)

                {
                    Module obj1 = new Module(name.getText().toString(),phone.getText().toString(),age.getText().toString());
                    ref.push().setValue(obj1);
                }

                else
                {
                    Toast.makeText(MainActivity.this,"Please enter your data",Toast.LENGTH_SHORT).show();
                }


            }

        });

        getSingleData();
        getMultiData();
    }
    private void getSingleData()
    {
        FirebaseDatabase.getInstance().getReference().child("Database").child("Text").child("names").child("numbers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.v("single",dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void getMultiData()
    {
        FirebaseDatabase.getInstance().getReference().child("Database").child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    Module module = snapshot.getValue(Module.class);
                    Log.v("OBJ",module.getName());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
