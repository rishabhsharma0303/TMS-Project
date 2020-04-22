package com.example.tms;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_navg_view);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(MapsActivity.this,MainActivity.class));
                        finish();
                        //   Toast.makeText(MainActivity.this, "Recents", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.location:
                        //  Toast.makeText(MainActivity.this, "Favorites", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MapsActivity.this,MapsActivity.class));
                        finish();
                        break;
                   /*case R.id.profile:
                       // Toast.makeText(MainActivity.this, "Nearby", Toast.LENGTH_SHORT).show();
                       break;*/
                    case R.id.announcement:
                        startActivity(new Intent(MapsActivity.this,AnnouncementActivity.class));
                         finish();
                        break;
                   /* case R.id.gallery:
                        startActivity(new Intent(MapsActivity.this,GalleryActivity.class));
                        break;*/
                    case R.id.profile:
                        startActivity(new Intent(MapsActivity.this,ProfileActivity.class));
                     //   finish();

                }
                return true;
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        uDesign();

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(28.684744, 77.298079);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Vimal education"));
        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,20));

///mMap.setMyLocationEnabled(true);

    }

    private void uDesign() {
        mMap.getUiSettings().setZoomControlsEnabled(true);
       // mMap.getUiSettings().setMyLocationButtonEnabled(true);
    }
}
