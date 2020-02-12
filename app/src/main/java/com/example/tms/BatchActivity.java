package com.example.tms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BatchActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
  // DatabaseReference myRef = database.getReference("message");
DatabaseReference mref;
TextView tv;
TextView tv_physics,tv_chemistry,tv_maths;
Spinner spinner;
String physics,chemistry,maths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch);
        mref=FirebaseDatabase.getInstance().getReference("batches");
        spinner=(Spinner)findViewById(R.id.spinner);
        tv=(TextView)findViewById(R.id.tv);
        ArrayList<String> subjects=new ArrayList<>();
        subjects.add("Choose subjects");
        subjects.add("Physics by Rishabh sharma");
        subjects.add("chemistry by sanjay sharma");
        subjects.add("Maths by Vimal sharma");
        ArrayAdapter<String> dataAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,subjects);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    getData();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("Choose subjects")){
                    // do nothing
                }
                else if(parent.getItemAtPosition(position).equals("Physics by Rishabh sharma")){
                    String item=parent.getItemAtPosition(position).toString();
                    tv.setText(item+" "+"timing"+" "+physics);
                }
                else if(parent.getItemAtPosition(position).equals("chemistry by sanjay sharma")){
                    String item=parent.getItemAtPosition(position).toString();
                    tv.setText(item+" "+"timing"+" "+chemistry);
                }
                else if(parent.getItemAtPosition(position).equals("Maths by Vimal sharma")){
                    String item=parent.getItemAtPosition(position).toString();
                    tv.setText(item+" "+"timing"+" "+maths);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        tv_physics=(TextView)findViewById(R.id.show_phy);
//        tv_chemistry=(TextView)findViewById(R.id.show_chem);
//       tv_maths= (TextView)findViewById(R.id.show_maths);
//        mref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//              //tv.setText(dataSnapshot.child("physics").getValue(String.class));
//              //  int a=2;
//                String chemistry= dataSnapshot.child("chem_timing").getValue(String.class);
//              //  tv.setText(chemistry);
//                String physics=dataSnapshot.child("phy_timing").getValue(String.class);
//                String maths=dataSnapshot.child("math_timing").getValue(String.class);
//                tv_physics.setText(physics);
//                tv_chemistry.setText(chemistry);
//                tv_maths.setText(maths);
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

    }

    private void getData() {
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                physics=dataSnapshot.child("phy_timing").getValue(String.class);
           maths=dataSnapshot.child("math_timing").getValue(String.class);
               chemistry= dataSnapshot.child("chem_timing").getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
