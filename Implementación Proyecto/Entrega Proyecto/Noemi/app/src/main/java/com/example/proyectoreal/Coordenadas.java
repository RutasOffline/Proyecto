package com.example.proyectoreal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Coordenadas extends AppCompatActivity {

    Button Calcular, Mapa, Agregar;
    EditText Latitud, Longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordenadas);
        Agregar = (Button)findViewById(R.id.Agregar);
        Calcular = (Button)findViewById(R.id.Calcular);
        Latitud = (EditText)findViewById(R.id.latitud);
        Longitud = (EditText)findViewById(R.id.longitud);
        Mapa = (Button)findViewById(R.id.Mapa);









        Agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Coordenadas.this, BuscadorClientes.class);
                intent.putExtra("info1",Latitud.getText().toString());
                intent.putExtra("info2",Longitud.getText().toString());
                startActivity(intent);
                //  startActivity(new Intent(Main2Activity.this,TrazarRuta.class));
                //finish();
            }
        });


        Mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Coordenadas.this,Noe.class));
                finish();
            }
        });


        Calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationManager locationManager = (LocationManager) Coordenadas.this.getSystemService(Context.LOCATION_SERVICE);

                LocationListener locationListener = new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {

                        //Intent i = new Intent(Coordenadas.this, Noe.class);
                        Latitud.setText(""+location.getLatitude());
                        Longitud.setText(""+location.getLongitude());
                        //i.putExtra("Latitud",location.getLatitude());
                        //i.putExtra("Longitud",location.getLongitude());
                        //startActivity(i);

                    }

                    public void onStatusChanged(String provaider, int status, Bundle extras) {
                    }

                    public void onProviderEnabled(String provider) {
                    }

                    public void onProviderDisabled(String provider) {
                    }

                };
                int permissionCheck = ContextCompat.checkSelfPermission(Coordenadas.this,
                        Manifest.permission.ACCESS_FINE_LOCATION);

                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
            }
        });

        //Solicita Permisos De Ubicación
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);

        //Valida Los Obtencion de Permisos
        if (permissionCheck == PackageManager.PERMISSION_DENIED){

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }

        }

    }
    //Menú
    public boolean onCreateOptionsMenu(Menu menu)  {
        getMenuInflater().inflate(R.menu.overflow, menu);
        return  true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.itcooredenadas){
            //Toast.makeText(this, "Coordenadas", Toast.LENGTH_SHORT).show();
        } else  if(id == R.id.itreportes){
            //Toast.makeText(this, "Reportes", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Coordenadas.this,Buscador.class));
            finish();
        } else if (id == R.id.itclientes){
            Intent intent = new Intent(Coordenadas.this, BuscadorClientes.class);
            intent.putExtra("info1",Latitud.getText().toString());
            intent.putExtra("info2",Longitud.getText().toString());
            startActivity(intent);
        }else  if(id == R.id.itsistema){
            startActivity(new Intent(Coordenadas.this,Sistema.class));
            finish();
        }else  if(id == R.id.itayuda){
            //Toast.makeText(this, "Au No esta Listo", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Coordenadas.this,Ayuda.class));
            finish();
        }else  if(id == R.id.itabout){
           // Toast.makeText(this, "Ain no esta Listo", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Coordenadas.this,About.class));
            finish();

        }
        return  super.onOptionsItemSelected(item);
    }

}

