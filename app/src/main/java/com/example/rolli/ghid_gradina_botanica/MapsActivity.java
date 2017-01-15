package com.example.rolli.ghid_gradina_botanica;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.location.Address;
import android.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harta);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        LatLng gradina = new LatLng(46.76242999999999, 23.589140000000043);
        LatLng sere_intrare = new LatLng(46.759577, 23.588271);
        LatLng Iris = new LatLng(46.760491, 23.586837);
        LatLng Nuferi = new LatLng(46.761558, 23.587227);

        mMap.addMarker(new MarkerOptions().position(gradina).title("Gradina Botanica"));
        mMap.addMarker(new MarkerOptions().position(sere_intrare).title("Intrare Sere"));
        mMap.addMarker(new MarkerOptions().position(Iris).title("Iris"));
        mMap.addMarker(new MarkerOptions().position(Nuferi).title("Nuferi"));



        mMap.moveCamera(CameraUpdateFactory.newLatLng(gradina));

        // Move the camera instantly to location with a zoom of 15.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gradina, 15));
        // Zoom in, animating the camera.
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17), 2000, null);

    }


    //Metoda pentru a cauta locatii

    public void onSearch(View view){
        EditText location_tf = (EditText)findViewById(R.id.TFAdress);
        String location = location_tf.getText().toString();
        List<Address> addressList = null;

        if(location != null || location.equals("")){
            Geocoder geocoder = new Geocoder(this);
            try {

                addressList = geocoder.getFromLocationName(location, 1);
            } catch (IOException e){
                e.printStackTrace();

            }
            Address address = addressList.get(0);

            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title("Nuferi"));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

        }


    }


}
