package com.example.mye_commerceapplication;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
<<<<<<< HEAD
import android.view.View;
import android.widget.Button;
=======
>>>>>>> 4883ac17b2f394c2da82b7e20173e3cde737b681

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
<<<<<<< HEAD
import com.google.android.gms.maps.model.Marker;
=======
>>>>>>> 4883ac17b2f394c2da82b7e20173e3cde737b681
import com.google.android.gms.maps.model.MarkerOptions;

public class UserMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    static String londitude_user;
    static String latitude_user;
<<<<<<< HEAD
    Marker marker;
    Button confirmerCoordUser;
=======
>>>>>>> 4883ac17b2f394c2da82b7e20173e3cde737b681

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
<<<<<<< HEAD
        confirmerCoordUser=findViewById(R.id.setCoordUserButtonUse);
        mapFragment.getMapAsync(this);

=======
        mapFragment.getMapAsync(this);
>>>>>>> 4883ac17b2f394c2da82b7e20173e3cde737b681
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

        // Add a marker in Sydney and move the camera
<<<<<<< HEAD
        final LatLng ensias = new LatLng(33.98431180000001, -6.867601900000004);
        marker= mMap.addMarker(new MarkerOptions().position(ensias).title("your Position").draggable(true));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ensias));
=======
        LatLng sydney = new LatLng(-38, 15);
        mMap.addMarker(new MarkerOptions().position(sydney).title("your Position"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
>>>>>>> 4883ac17b2f394c2da82b7e20173e3cde737b681
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                latitude_user=String.valueOf(latLng.latitude);
                londitude_user=String.valueOf(latLng.longitude);
<<<<<<< HEAD
                marker.setPosition(latLng);

            }
        });

        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                latitude_user=String.valueOf(marker.getPosition().latitude);
                londitude_user=String.valueOf(marker.getPosition().longitude);
                LatLng latLngFinal=new LatLng(marker.getPosition().latitude,marker.getPosition().longitude);
                marker.setPosition(latLngFinal);
            }
        });

        confirmerCoordUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                latitude_user=String.valueOf(marker.getPosition().latitude);
                londitude_user=String.valueOf(marker.getPosition().longitude);
                finish();
=======
                Intent intent=new Intent(UserMapsActivity.this,RegisterActivity.class);
                startActivity(intent);
>>>>>>> 4883ac17b2f394c2da82b7e20173e3cde737b681
            }
        });
    }
}
