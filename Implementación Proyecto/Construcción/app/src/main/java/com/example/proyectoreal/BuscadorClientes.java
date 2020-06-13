package com.example.proyectoreal;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class BuscadorClientes extends AppCompatActivity {

    EditText etBuscador;
    RecyclerView rvLista;
    AdaptadorClientes adaptador2;
    List<Cliente> listaClientes;
    Button consulta;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscador_clientes);

        Bundle bundle = getIntent().getExtras();
        final String dato = bundle.getString("info1").toString();
        final String dato2 = bundle.getString("info2").toString();


        consulta = (Button)findViewById(R.id.consulta);
        etBuscador = findViewById(R.id.etBuscador);


        consulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(BuscadorClientes.this,AgregarCoo.class));
                //finish();

                Intent intent = new Intent(BuscadorClientes.this, AgregarCoo.class);
                intent.putExtra("info1",dato);
                intent.putExtra("info2",dato2);
                startActivity(intent);
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

        listaClientes = new ArrayList<>();

        obtenerCliente();
        adaptador2 = new AdaptadorClientes(BuscadorClientes.this, listaClientes);
        rvLista.setAdapter(adaptador2);
    }
    public void obtenerCliente(){
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, getResources().getString(R.string.URL_CLIENTES),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("Usuarios");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                listaClientes.add(
                                        new Cliente(
                                                jsonObject1.getString("id_cliente"),
                                                jsonObject1.getString("nombre"),
                                                jsonObject1.getString("telefono"),
                                                jsonObject1.getString("coordenadas")
                                        )

                                );
                            }

                            adaptador2 = new AdaptadorClientes(BuscadorClientes.this, listaClientes);
                            rvLista.setAdapter(adaptador2);

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
        ArrayList<Cliente> filtrarLista = new ArrayList<>();

        for (Cliente cliente : listaClientes){
            if (cliente.getNombre().toLowerCase().contains(texto.toLowerCase())){
                filtrarLista.add(cliente);
            }

        }
        adaptador2.filtrar(filtrarLista);
    }

    //Menú
    public boolean onCreateOptionsMenu(Menu menu)  {
        getMenuInflater().inflate(R.menu.overflow, menu);
        return  true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.itcooredenadas){
            startActivity(new Intent(BuscadorClientes.this,Coordenadas.class));
            finish();
        } else  if(id == R.id.itreportes){
            //Toast.makeText(this, "Reportes", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(BuscadorClientes.this,Buscador.class));
            finish();
        } else if (id == R.id.itclientes){
            //
        }else  if(id == R.id.itsistema){
            startActivity(new Intent(BuscadorClientes.this,Sistema.class));
            finish();
        }else  if(id == R.id.itayuda){
            //Toast.makeText(this, "Au No esta Listo", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(BuscadorClientes.this,Ayuda.class));
            finish();
        }else  if(id == R.id.itabout){
            // Toast.makeText(this, "Ain no esta Listo", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(BuscadorClientes.this,About.class));
            finish();

        }
        return  super.onOptionsItemSelected(item);
    }
    }

