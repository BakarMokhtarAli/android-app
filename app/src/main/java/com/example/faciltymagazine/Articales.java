package com.example.faciltymagazine;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Articales extends AppCompatActivity {
    Spinner authorID, magasozeID;
    EditText titles, content, publiccation_date,search;
    TextView articles;
    String getID = null,authorIDs,magasozeIDs;
    Url url = new Url();
    DatePickerDialog picker;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<String> arrayList1 = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articales);

        // ---- inazalization textField and Spiners
        titles = findViewById(R.id.titles);
        content = findViewById(R.id.content);
        publiccation_date = findViewById(R.id.publiccation_date);
        articles = findViewById(R.id.articles);
        //authorID = findViewById(R.id.authorID);
        magasozeID = findViewById(R.id.magasozeID);
        search = findViewById(R.id.search);
        // ---------------------------

        // Getting Session / Cookie
        SharedPreferences mySharedPreferences = getSharedPreferences("usernameLogin", MODE_PRIVATE);
        authorIDs = mySharedPreferences.getString("fname", "");

        // Dropdown list / select box / text box
       // setDataSpinner (authorID, getApplicationContext(), "select u.username from users u where u.deleted=0");
        setDataSpinner2 (magasozeID,getApplicationContext(), "select m.title from magazines m where m.deleted=0");

        // Spiner event / Dropdown list
        magasozeID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //
                magasozeIDs = parent.getItemAtPosition(position).toString();
                //Toast.makeText(Articales.this, parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });

        // Getting Date
        publiccation_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDate(publiccation_date);
            }
        });
    }
    // this method can allow to make inserting data
    public void save(View view){
        final String qry = "call articles_sp('"+getID+"','"+titles.getText().toString()+"','"+content.getText().toString()+"','"+
                publiccation_date.getText().toString()+"','"+authorIDs+"','"+magasozeIDs+"','insert')";
        try{
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url.setPost, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Toast.makeText(UsersManage.this, response, Toast.LENGTH_LONG).show();
                    url.alertMessage (Articales.this, "Response Message", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Articales.this, "Error :> "+error, Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("qry",qry);

                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(Articales.this);
            queue.add(stringRequest);
        }catch (Exception ex){
            Toast.makeText(Articales.this, "Eros "+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // this method can allow to make Updating / modify data
    public void update(View view){
        final String qry = "call articles_sp('"+getID+"','"+titles.getText().toString()+"','"+content.getText().toString()+"','"+
                publiccation_date.getText().toString()+"','"+authorIDs+"','"+magasozeIDs+"','update')";
        try{
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url.setPost, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Toast.makeText(UsersManage.this, response, Toast.LENGTH_LONG).show();
                    url.alertMessage (Articales.this, "Response Message", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Articales.this, "Error :> "+error, Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("qry",qry);

                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(Articales.this);
            queue.add(stringRequest);
        }catch (Exception ex){
            Toast.makeText(Articales.this, "Eros "+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    // this method can allow to make Deleting / Drop data
    public void delete(View view){
        final String qry = "call articles_sp('"+getID+"','"+titles.getText().toString()+"','"+content.getText().toString()+"','"+
                publiccation_date.getText().toString()+"','"+authorIDs+"','"+magasozeIDs+"','delete')";
        try{
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url.setPost, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Toast.makeText(UsersManage.this, response, Toast.LENGTH_LONG).show();
                    url.alertMessage (Articales.this, "Response Message", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Articales.this, "Error :> "+error, Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("qry",qry);

                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(Articales.this);
            queue.add(stringRequest);
        }catch (Exception ex){
            Toast.makeText(Articales.this, "Eros "+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    // this method can allow to make Searching data
    public void show(View view){
        final String qrys = "select a.article_id,a.title,a.content,a.publication_date from articles a where a.deleted=0 and a.article_id="+search.getText().toString();
        try{
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url.find, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Toast.makeText(Invesgater.this, response, Toast.LENGTH_LONG).show();
                    final String[] rst = response.split(",");
                    for (int i = 0; i <rst.length; i++){
                        //Toast.makeText(Invesgater.this, jsonObject.getString("2"), Toast.LENGTH_LONG).show();
                        getID = rst[0];
                        titles.setText(rst[1]);
                        content.setText(rst[2]);
                        publiccation_date.setText(rst[3]);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Articales.this, "Error :> "+error, Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("qry",qrys);

                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(Articales.this);
            queue.add(stringRequest);
        }catch (Exception ex){
            Toast.makeText(Articales.this, "Eros "+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    // this method can allow to make Showing table data
    public void info(View view){
        Intent supp = new Intent(Articales.this, Table_info.class);
        supp.putExtra("qry","select a.article_id 'ID',a.title 'Title',a.content 'Content',a.publication_date 'Publication Date', u.username 'Author' from articles a, magazines m, users u where a.deleted=0 and a.author_id=u.username and a.magazine_id=m.title;");
        startActivity(supp);
    }
    //Set data in Spinner 1 and 2
    public void setDataSpinner(Spinner spner, Context cxt, String sql){
        final String qry = sql;
        try{
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url.find, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Toast.makeText(Invetigations.this, response, Toast.LENGTH_LONG).show();
                    final String[] rst = response.split(",");
                    //final String[] spinerData;
                    try {
                        for (int i = 0; i <rst.length; i++){
                            //Toast.makeText(Invesgater.this, jsonObject.getString("2"), Toast.LENGTH_LONG).show();
                            arrayList.add(rst[i]);
                            adapter = new ArrayAdapter<String>(cxt, android.R.layout.simple_spinner_item, arrayList);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spner.setAdapter(adapter);
                        }
                    }catch (Exception ex){
                        Toast.makeText(Articales.this, ex.getMessage(), Toast.LENGTH_LONG).show();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(cxt, "Error :> "+error, Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("qry",qry);

                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(Articales.this);
            queue.add(stringRequest);
        }catch (Exception ex){
            Toast.makeText(cxt, "Eros "+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void setDataSpinner2(Spinner spner, Context cxt, String sql){
        final String qry = sql;
        try{
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url.find, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Toast.makeText(Invetigations.this, response, Toast.LENGTH_LONG).show();
                    final String[] rst = response.split(",");
                    //final String[] spinerData;
                    try {
                        for (int i = 0; i <rst.length; i++){
                            //Toast.makeText(Invesgater.this, jsonObject.getString("2"), Toast.LENGTH_LONG).show();
                            arrayList1.add(rst[i]);
                            adapter1 = new ArrayAdapter<String>(cxt, android.R.layout.simple_spinner_item, arrayList1);
                            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spner.setAdapter(adapter1);
                        }
                    }catch (Exception ex){
                        Toast.makeText(Articales.this, ex.getMessage(), Toast.LENGTH_LONG).show();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(cxt, "Error :> "+error, Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("qry",qry);

                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(Articales.this);
            queue.add(stringRequest);
        }catch (Exception ex){
            Toast.makeText(cxt, "Eros "+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // Getting Date
    public void getDate(EditText editText){
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        picker = new DatePickerDialog(Articales.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        editText.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, year, month, day);
        picker.show();
    }
}