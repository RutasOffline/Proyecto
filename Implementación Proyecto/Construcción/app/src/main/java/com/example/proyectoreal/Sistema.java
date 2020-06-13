package com.example.proyectoreal;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class Sistema extends AppCompatActivity {

    WebView Sistemita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sistema);

        Sistemita = (WebView)findViewById(R.id.Sistemita);
        Sistemita.getSettings().setJavaScriptEnabled(true);
        Sistemita.setWebViewClient(new WebViewClient());
        Sistemita.loadUrl("https://www.gamestorrents.nu/juegos-pc/");

    }

    //Menú
    public boolean onCreateOptionsMenu(Menu menu)  {
        getMenuInflater().inflate(R.menu.overflow, menu);
        return  true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.itcooredenadas){
            startActivity(new Intent(Sistema.this,Coordenadas.class));
            finish();
        } else  if(id == R.id.itreportes){
            //Toast.makeText(this, "Reportes", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Sistema.this,Buscador.class));
            finish();
        } else if (id == R.id.itclientes){
            // startActivity(new Intent(About.this,BuscadorClientes.class));
            // finish();
        }else  if(id == R.id.itsistema){

        }else  if(id == R.id.itayuda){
            //Toast.makeText(this, "Au No esta Listo", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Sistema.this,Ayuda.class));
            finish();
        }else  if(id == R.id.itabout){
            startActivity(new Intent(Sistema.this,About.class));
            finish();

        }
        return  super.onOptionsItemSelected(item);
    }
}
