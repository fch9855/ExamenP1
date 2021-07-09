package com.example.heroesapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
//hola Claudia el token del API es: 4337527472970581
public class MainActivity extends AppCompatActivity {
    Button button;
    EditText editTextPersonName;
    ArrayList<SuperHeroe> superHeroes = new ArrayList<SuperHeroe>();
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        editTextPersonName = (EditText) findViewById(R.id.editTextPersonName);
    }
    public void buscarHeroe(View view) {
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
            String url="https://www.superheroapi.com/api.php/4402502963107815/search/"+editTextPersonName.getText().toString();
            JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray myJsonArray = response.getJSONArray("results");
                        for (int i=0;i<myJsonArray.length();i++){
                            JSONObject myObject=myJsonArray.getJSONObject(i);
                            String id=myObject.getString("id");
                            String alterego=myObject.getJSONObject("biography").getString("full-name");
                            String nombre=myObject.getString("name");
                            HashMap habilidades=new HashMap();
                            habilidades.put("Inteligencia",myObject.getJSONObject("powerstats").getString("intelligence"));
                            habilidades.put("Fuerza",myObject.getJSONObject("powerstats").getString("strength"));
                            habilidades.put("Velocidad",myObject.getJSONObject("powerstats").getString("speed"));
                            habilidades.put("Durabilidad",myObject.getJSONObject("powerstats").getString("durability"));
                            habilidades.put("Poder",myObject.getJSONObject("powerstats").getString("power"));
                            habilidades.put("Combate",myObject.getJSONObject("powerstats").getString("combat"));
                            SuperHeroe superHeroe=new SuperHeroe(id,alterego,nombre,habilidades);
                            superHeroes.add(superHeroe);
                            System.out.println(superHeroe);
                        }
                        Intent intent=new Intent(MainActivity.this,Respuesta.class);
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("Superheroes",superHeroes);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        finish();
                    } catch (JSONException e) {
                        System.out.println("Error "+e);
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("Error"+error);
                }
            });
            queue.add(request);
    }
}