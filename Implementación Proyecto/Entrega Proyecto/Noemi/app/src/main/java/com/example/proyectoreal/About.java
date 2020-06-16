package com.example.proyectoreal;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }

    //Menú
    public boolean onCreateOptionsMenu(Menu menu)  {
        getMenuInflater().inflate(R.menu.overflow, menu);
        return  true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.itcooredenadas){
            startActivity(new Intent(About.this,Coordenadas.class));
            finish();
        } else  if(id == R.id.itreportes){
            //Toast.makeText(this, "Reportes", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(About.this,Buscador.class));
            finish();
        } else if (id == R.id.itclientes){
           // startActivity(new Intent(About.this,BuscadorClientes.class));
           // finish();
        }else  if(id == R.id.itsistema){
            startActivity(new Intent(About.this,Sistema.class));
            finish();
        }else  if(id == R.id.itayuda){
            //Toast.makeText(this, "Au No esta Listo", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(About.this,Ayuda.class));
            finish();
        }else  if(id == R.id.itabout){
            // Toast.makeText(this, "Ain no esta Listo", Toast.LENGTH_SHORT).show();
           // startActivity(new Intent(About.this,About.class));
            //finish();

        }
        return  super.onOptionsItemSelected(item);
    }

}
