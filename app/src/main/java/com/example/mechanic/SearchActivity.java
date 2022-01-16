package com.example.mechanic;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mechanic.Anime;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import java.util.List;


public class SearchActivity extends AppCompatActivity {
    private List<Anime> lstAnime;
    private RecyclerView recyclerView;
    Button search;
    public String searchv;
    public String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //get spinner from the xml
        Spinner dropdown = findViewById(R.id.spinner1);
        //create  list of items for the spinner
        String[] items = new String[]{"Nakuru","Nairobi","Mombasa","Naivasha","Kericho"};
        //create an sdapter to describe how the items are displayed
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,items);
        //Set the spinner adapter to the previously created
         //adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        dropdown.setAdapter(adapter);



        url = "https://mybusses.herokuapp.com/api/fetch_mechanic_api.php";

       search = findViewById(R.id.search);

       search.setOnClickListener(v -> {
           searchv = dropdown.getSelectedItem().toString();

           lstAnime = new ArrayList<>();
           recyclerView = findViewById(R.id.recyclerviewid);
           jsonrequest();
       });


    }

    private void jsonrequest() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching mechanics...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        String JSON_URL = url;
        JSONObject jsonObject = new JSONObject();

        try {


            jsonObject.put("about", searchv);

            Response.Listener<JSONArray> successListener = response -> {


                for (int i = 0; i < response.length(); i++) {

                    try {
                        Anime anime = Anime.gethostel(response.getJSONObject(i));
                        anime.setHostelname(anime.getHostelname());
                        anime.setPrice(anime.getPrice());
                        anime.setAvalrooms(anime.getAvalrooms());
                        anime.setLocation(anime.getLocation());
                        anime.setUrl(anime.getUrl());
                        progressDialog.dismiss();
                        lstAnime.add(anime);

                    } catch (JSONException e) {
                        Toast.makeText(SearchActivity.this,"No match found for the current location",Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();


                    }

                }
                setuprecycleview(lstAnime);

            };
            Response.ErrorListener errorListener = error -> {
                if (error instanceof NoConnectionError) {
                    setContentView(R.layout.error);
                    Toast.makeText(SearchActivity.this, "No internet access", Toast.LENGTH_LONG).show();
                } else {
                    setContentView(R.layout.error);
                    Toast.makeText(SearchActivity.this, "No response,Make sure you have a strong internet connection", Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            };
            JsonArrayRequest request = new JsonArrayRequest(JSON_URL, successListener, errorListener);
            RequestQueue requestQueue = Volley.newRequestQueue(SearchActivity.this);
            requestQueue.add(request);


        } catch (Exception e) {
            Toast.makeText(SearchActivity.this, "json exception", Toast.LENGTH_LONG).show();
        }

    }

    private void setuprecycleview(List<Anime> lstAnime) {
        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this, lstAnime);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myadapter);
    }
}
