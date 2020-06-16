package com.example.proyectoreal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Buscador extends AppCompatActivity {

    EditText etBuscador;
    RecyclerView rvLista;
    AdaptadorUsuarios adaptador;
    List<Usuario> listaUsuarios;
    Button consulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscador);

        consulta = (Button)findViewById(R.id.consulta);
        etBuscador = findViewById(R.id.etBuscador);

        consulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Buscador.this,Main2Activity.class));
                finish();
            }
        });


        etBuscador.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {

                filtrar(s.toString());
            }
        });

        rvLista = findViewById(R.id.rvLista);
        rvLista.setLayoutManager(new GridLayoutManager(this,1));

        listaUsuarios = new ArrayList<>();

        obtenerUsuarios();
        adaptador = new AdaptadorUsuarios(Buscador.this, listaUsuarios);
        rvLista.setAdapter(adaptador);
    }
    public void obtenerUsuarios(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getResources().getString(R.string.URL_DATOS),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("Usuarios");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                listaUsuarios.add(
                                        new Usuario(
                                                jsonObject1.getString("id_reporte"),
                                                jsonObject1.getString("cliente"),
                                                jsonObject1.getString("reporte"),
                                                jsonObject1.getString("tecnico"),
                                                jsonObject1.getString("coordenadas"),
                                                jsonObject1.getString("estado"),
                                                jsonObject1.getString("comentarios")
                                        )

                                );
                            }

                            adaptador = new AdaptadorUsuarios(Buscador.this, listaUsuarios);
                            rvLista.setAdapter(adaptador);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }
        );

        requestQueue.add(stringRequest);
    }

    public void filtrar(String texto){
        ArrayList<Usuario> filtrarLista = new ArrayList<>();

        for (Usuario usuario : listaUsuarios){
            if (usuario.getTecnico().toLowerCase().contains(texto.toLowerCase())){
                filtrarLista.add(usuario);
            }

        }
        adaptador.filtrar(filtrarLista);
    }


    //Menú
    public boolean onCreateOptionsMenu(Menu menu)  {
        getMenuInflater().inflate(R.menu.overflow, menu);
        return  true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.itcooredenadas){
            startActivity(new Intent(Buscador.this,Coordenadas.class));
            finish();
        //} else  if(id == R.id.itreportes){
            //Toast.makeText(this, "Reportes", Toast.LENGTH_SHORT).show();
            //startActivity(new Intent(Coordenadas.this,Buscador.class));
            //finish();
        //} else if (id == R.id.itclientes){
          //  startActivity(new Intent(Buscador.this,BuscadorClientes.class));
            //finish();
        }else  if(id == R.id.itsistema){
            startActivity(new Intent(Buscador.this,Sistema.class));
            finish();
        }else  if(id == R.id.itayuda){
            //Toast.makeText(this, "Au No esta Listo", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Buscador.this,Ayuda.class));
            finish();
        }else  if(id == R.id.itabout){
            // Toast.makeText(this, "Ain no esta Listo", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Buscador.this,About.class));
            finish();

        }
        return  super.onOptionsItemSelected(item);
    }


}
