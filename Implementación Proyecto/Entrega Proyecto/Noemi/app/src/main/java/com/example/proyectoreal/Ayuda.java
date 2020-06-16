package com.example.proyectoreal;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Ayuda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);
    }


    //Menú
    public boolean onCreateOptionsMenu(Menu menu)  {
        getMenuInflater().inflate(R.menu.overflow, menu);
        return  true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.itcooredenadas){
            startActivity(new Intent(Ayuda.this,Coordenadas.class));
            finish();
        } else  if(id == R.id.itreportes){
            //Toast.makeText(this, "Reportes", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Ayuda.this,Buscador.class));
            finish();
        } else if (id == R.id.itclientes){

        }else  if(id == R.id.itsistema){
            startActivity(new Intent(Ayuda.this,Sistema.class));
            finish();
        }else  if(id == R.id.itayuda){

        }else  if(id == R.id.itabout){
            startActivity(new Intent(Ayuda.this,About.class));
            finish();

        }
        return  super.onOptionsItemSelected(item);
    }
}
