package com.example.tms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
View classroom;
View about_us;
View query;
View payment;
AnimationDrawable animationDrawable;
ImageView anim_img;
BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        classroom=(View)findViewById(R.id.view1);
        about_us=(View)findViewById(R.id.about_us);
        query=(View)findViewById(R.id.query);
        payment=(View)findViewById(R.id.rupee);
        anim_img=(ImageView)findViewById(R.id.imgview_animation);
        bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_navg_view);
        animationDrawable=new AnimationDrawable();
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.anim1),4000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.anim2),3000);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.anim3),3000);
        anim_img.setBackground(animationDrawable);
        animationDrawable.start();


        classroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,BatchActivity.class));

            }
        });
        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AboutActivity.class));
            }
        });
        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,QueryActivity.class));
            }
        });
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,DeliveryActivity.class));
            }
        });
       bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
               switch (menuItem.getItemId()) {
                   case R.id.home:
                       startActivity(new Intent(MainActivity.this,MainActivity.class));
                       finish();
                       //   Toast.makeText(MainActivity.this, "Recents", Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.location:
                       //  Toast.makeText(MainActivity.this, "Favorites", Toast.LENGTH_SHORT).show();
                       break;
                   /*case R.id.profile:
                       // Toast.makeText(MainActivity.this, "Nearby", Toast.LENGTH_SHORT).show();
                       break;*/
                   case R.id.announcement:
                       startActivity(new Intent(MainActivity.this,AnnouncementActivity.class));
                      // finish();
                       break;
                   case R.id.gallery:
                       startActivity(new Intent(MainActivity.this,GalleryActivity.class));
               }
               return true;
           }
       });
    }
}
