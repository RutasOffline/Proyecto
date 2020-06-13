package com.example.proyectoreal;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {

    EditText etidr, etx_cliente, etx_reporte, etx_tecnico, etx_estado, etx_coordenadas, etx_comentarios;
    RequestQueue requestQueue;
    Button bt_buscar, Guardar, Ruta;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        etidr = (EditText)findViewById(R.id.etidr);
        etx_cliente=(EditText)findViewById(R.id.etx_cliente);
        etx_reporte=(EditText)findViewById(R.id.etx_reporte);
        etx_tecnico=(EditText)findViewById(R.id.etx_tecnico);
        etx_estado=(EditText)findViewById(R.id.etx_estado);
        etx_coordenadas=(EditText)findViewById(R.id.etx_coordenadas);
        etx_comentarios=(EditText)findViewById(R.id.etx_comentarios);

        bt_buscar=(Button)findViewById(R.id.bt_buscar);
        Guardar=(Button)findViewById(R.id.Guardar);
        Ruta=(Button)findViewById(R.id.Ruta);


        Ruta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Main2Activity.this, TrazarRuta.class);
                intent.putExtra("info",etx_coordenadas.getText().toString());
                startActivity(intent);
              //  startActivity(new Intent(Main2Activity.this,TrazarRuta.class));
                //finish();
            }
        });


        bt_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarProducto("http://192.168.1.69/CDSI/Buscar.php?codigo="+etidr.getText()+"");
            }
        });


        Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ejecutarServicio("http://192.168.1.69/CDSI/Actualizar.php");
            }
        });

    }

    private void buscarProducto(String URL){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        etx_cliente.setText(jsonObject.getString("cliente"));
                        etx_tecnico.setText(jsonObject.getString("tecnico"));
                        etx_reporte.setText(jsonObject.getString("reporte"));
                        etx_estado.setText(jsonObject.getString("estado"));
                        etx_coordenadas.setText(jsonObject.getString("coordenadas"));
                        etx_comentarios.setText(jsonObject.getString("comentarios"));
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Este reporte no existe. Verifica el ID",Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    private  void  ejecutarServicio(String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "OPERACION EXITOSA", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("id_reporte", etidr.getText().toString());
                parametros.put("cliente", etx_cliente.getText().toString());
                parametros.put("reporte", etx_reporte.getText().toString());
                parametros.put("tecnico", etx_tecnico.getText().toString());
                parametros.put("estado", etx_estado.getText().toString());
                parametros.put("coordenadas", etx_coordenadas.getText().toString());
                parametros.put("comentarios", etx_comentarios.getText().toString());
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    //Menú
    public boolean onCreateOptionsMenu(Menu menu)  {
        getMenuInflater().inflate(R.menu.overflow, menu);
        return  true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.itcooredenadas){
            startActivity(new Intent(Main2Activity.this,Coordenadas.class));
            finish();
        } else  if(id == R.id.itreportes){
            //Toast.makeText(this, "Reportes", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Main2Activity.this,Buscador.class));
            finish();
        } else if (id == R.id.itclientes){

        }else  if(id == R.id.itsistema){
            startActivity(new Intent(Main2Activity.this,Sistema.class));
            finish();
        }else  if(id == R.id.itayuda){
            //Toast.makeText(this, "Au No esta Listo", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Main2Activity.this,Ayuda.class));
            finish();
        }else  if(id == R.id.itabout){
            startActivity(new Intent(Main2Activity.this,About.class));
            finish();

        }
        return  super.onOptionsItemSelected(item);
    }
}
