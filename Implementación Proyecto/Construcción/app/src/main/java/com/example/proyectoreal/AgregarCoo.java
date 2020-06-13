package com.example.proyectoreal;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class AgregarCoo extends AppCompatActivity {

    TextView txNombre;
    EditText etidr, etCoordenadas;
    Button Guardar, bt_buscar;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_coo);

        etidr = (EditText) findViewById(R.id.etidr);
        etCoordenadas = (EditText) findViewById(R.id.etCoordenadas);
        txNombre = (TextView) findViewById(R.id.txNombre);
        Guardar = (Button) findViewById(R.id.Guardar);
        bt_buscar = (Button) findViewById(R.id.bt_buscar);

        Bundle bundle = getIntent().getExtras();
        final String dato = bundle.getString("info1").toString();
        final String dato2 = bundle.getString("info2").toString();
        etCoordenadas.setText(dato+" "+dato2);


        bt_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarProducto("http://192.168.1.69/CDSI/BuscarIDCliente.php?codigo="+etidr.getText()+"");
            }
        });

        Guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ejecutarServicio("http://192.168.1.69/CDSI/AgregarCoo.php");
            }
        });

    }

    private void buscarProducto(String URL) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        txNombre.setText(jsonObject.getString("nombre"));
                       // etCoordenadas.setText(jsonObject.getString("coordenadas"));
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Este reporte no existe. Verifica el ID", Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue = Volley.newRequestQueue(this);
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
                parametros.put("id_cliente", etidr.getText().toString());
                parametros.put("coordenadas", etCoordenadas.getText().toString());

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
            startActivity(new Intent(AgregarCoo.this,Coordenadas.class));
            finish();
        } else  if(id == R.id.itreportes){
            //Toast.makeText(this, "Reportes", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AgregarCoo.this,Buscador.class));
            finish();
        } else if (id == R.id.itclientes){
           // startActivity(new Intent(AgregarCoo.this,BuscadorClientes.class));
            //finish();
        }else  if(id == R.id.itsistema){
            startActivity(new Intent(AgregarCoo.this,Sistema.class));
            finish();
        }else  if(id == R.id.itayuda){
            //Toast.makeText(this, "Au No esta Listo", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AgregarCoo.this,Ayuda.class));
            finish();
        }else  if(id == R.id.itabout){
            startActivity(new Intent(AgregarCoo.this,About.class));
            finish();

        }
        return  super.onOptionsItemSelected(item);
    }


}
