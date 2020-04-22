package com.example.tms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AnnouncementActivity extends AppCompatActivity {
BottomNavigationView bottomNavigationView;
    DatabaseReference mref;
//View view;
    TextView data_of_announcement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);
       // view=(View)findViewById(R.id.announcement_view);
        data_of_announcement=(TextView)findViewById(R.id.dataOfannouncement);
        mref= FirebaseDatabase.getInstance().getReference("Announcement");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("back");
//read data from firebase
        bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_navg_view);
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               data_of_announcement.setText(dataSnapshot.getValue(String.class)) ;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.home){
                    startActivity(new Intent(AnnouncementActivity.this,MainActivity.class));

                }if(menuItem.getItemId()==R.id.announcement){
                   startActivity(new Intent(AnnouncementActivity.this,AnnouncementActivity.class));


                }/*if(menuItem.getItemId()==R.id.gallery){
                    startActivity(new Intent(AnnouncementActivity.this,GalleryActivity.class));
                }
*/
                return true;
            }
       });

//        ActionBar actionBar=getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
//    }
//    public boolean onOptionsItemSelected(MenuItem item){
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                finish();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    public boolean onCreateOptionsMenu(Menu menu) {
//        return true;
//    }
    }}

