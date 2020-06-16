package com.example.proyectoreal;


import android.os.Bundle;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class TrazarRuta extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    TextView prueba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trazar_ruta);

        Bundle bundle = getIntent().getExtras();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        String dato = bundle.getString("info").toString();


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
        Bundle bundle = getIntent().getExtras();
        String dato = bundle.getString("info").toString();

        String[] parts = dato.split(" ");
        String part1 = parts[0]; //obtiene: 19
        String part2 = parts[1]; //obtiene: 19-A

        double doble1 = Double.parseDouble(part1);
        double doble2 = Double.parseDouble(part2);

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(doble1, doble2);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Reporte"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney,18),5000,null);
    }
}
