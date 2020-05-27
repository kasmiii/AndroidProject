package com.example.mye_commerceapplication;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.firestore.GeoPoint;

public class VenteMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

        private Double longitude;
        private Double latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vente_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

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

        longitude=Double.valueOf(getIntent().getStringExtra("longitude"));
        latitude=Double.valueOf(getIntent().getStringExtra("latitude"));
        // Add a marker in Sydney and move the camera
        LatLng order = new LatLng(latitude, longitude);
        LatLng yourposition=new LatLng(33.1015904,-6.8498129);
        mMap.addMarker(new MarkerOptions().position(order).title("your Order"));
        mMap.addMarker(new MarkerOptions().position(yourposition).title("your Position"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(order));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(yourposition));

        //detection de la longitude et latitude a l'aide de setonMapClickListener() !!!!

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng clickCoords) {
                //Log.e("TAG", "Found @ " + clickCoords.latitude + " " + clickCoords.longitude);
                System.out.println("clicked point:\n"+"latitude:\t"+clickCoords.latitude+"\nlongitude:\t"+clickCoords.longitude);
                Intent intent =new Intent(VenteMapsActivity.this,SalesActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
        /*int X = (int)event.getX();
        int Y = (int)event.getY();
*/
        //GeoPoint geoPoint = mapView.getProjection().fromPixels(X, Y);
    }
}
