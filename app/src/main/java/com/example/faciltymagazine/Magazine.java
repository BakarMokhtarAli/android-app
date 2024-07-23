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
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

public class Magazine extends AppCompatActivity {
    Spinner authorrsID;
    EditText magazine_title, magazine_description, magazine_publicationDate, search;
    String getID = null, authorrsIDs;
    Url url = new Url();
    DatePickerDialog picker;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magazine);

        try {
            magazine_title = findViewById(R.id.magazine_title);
            magazine_description = findViewById(R.id.magazine_description);
            magazine_publicationDate = findViewById(R.id.magazine_publicationDate);
            //authorrsID = findViewById(R.id.authorrsID);

            //setDataSpinner(authorrsID, getApplicationContext(), "select u.username from users u where u.deleted=0");
            SharedPreferences mySharedPreferences = getSharedPreferences("usernameLogin", MODE_PRIVATE);
            authorrsIDs = mySharedPreferences.getString("fname", "");

            search = findViewById(R.id.search);

            magazine_publicationDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getDate(magazine_publicationDate);
                }
            });
        }catch (Exception ex){
            Toast.makeText(Magazine.this, ex.getMessage().toString(), Toast.LENGTH_LONG).show();
        }
    }

    // this method can allow to make inserting data
    public void save(View view) {
        final String qry = "call magazines_sp('" + getID + "','" + magazine_title.getText().toString() + "','"  + magazine_description.getText().toString() + "','"  +
                magazine_publicationDate.getText().toString() + "','" + authorrsIDs + "','insert')";
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url.setPost, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Toast.makeText(UsersManage.this, response, Toast.LENGTH_LONG).show();
                    url.alertMessage (Magazine.this, "Response Message", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Magazine.this, "Error :> " + error, Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("qry", qry);

                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(Magazine.this);
            queue.add(stringRequest);
        } catch (Exception ex) {
            Toast.makeText(Magazine.this, "Eros " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // this method can allow to make inserting data
    public void update(View view) {
        final String qry = "call magazines_sp('" + getID + "','" + magazine_title.getText().toString() + "','"  + magazine_description.getText().toString() + "','"  +
                magazine_publicationDate.getText().toString() + "','" + authorrsIDs + "','update')";
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url.setPost, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Toast.makeText(UsersManage.this, response, Toast.LENGTH_LONG).show();
                    url.alertMessage (Magazine.this, "Response Message", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Magazine.this, "Error :> " + error, Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("qry", qry);

                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(Magazine.this);
            queue.add(stringRequest);
        } catch (Exception ex) {
            Toast.makeText(Magazine.this, "Eros " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // this method can allow to make inserting data
    public void delete(View view) {
        final String qry = "call magazines_sp('" + getID + "','" + magazine_title.getText().toString() + "','"  + magazine_description.getText().toString() + "','"  +
                magazine_publicationDate.getText().toString() + "','" + authorrsIDs + "','delete')";
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url.setPost, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Toast.makeText(UsersManage.this, response, Toast.LENGTH_LONG).show();
                    url.alertMessage (Magazine.this, "Response Message", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Magazine.this, "Error :> " + error, Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("qry", qry);

                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(Magazine.this);
            queue.add(stringRequest);
        } catch (Exception ex) {
            Toast.makeText(Magazine.this, "Eros " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // this method can allow to make Searching data
    public void show(View view) {
        final String qrys = "select * from magazines m where m.deleted=0 and m.magazine_id=" + search.getText().toString();
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url.find, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Toast.makeText(Invesgater.this, response, Toast.LENGTH_LONG).show();
                    final String[] rst = response.split(",");
                    for (int i = 0; i < rst.length; i++) {
                        //Toast.makeText(Invesgater.this, jsonObject.getString("2"), Toast.LENGTH_LONG).show();
                        getID = rst[0];
                        magazine_title.setText(rst[1]);
                        magazine_description.setText(rst[2]);
                        magazine_publicationDate.setText(rst[3]);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Magazine.this, "Error :> " + error, Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("qry", qrys);

                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(Magazine.this);
            queue.add(stringRequest);
        } catch (Exception ex) {
            Toast.makeText(Magazine.this, "Eros " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // this method can allow to make Showing table data
    public void info(View view) {
        final String qry = "select m.magazine_id ID, m.title, m.description, m.publication_date 'Publication Date', u.username from magazines m, users u where m.deleted=0 and m.editor_id=u.username;";
        Intent supp = new Intent(Magazine.this, Table_info.class);
        supp.putExtra("qry",qry);
        startActivity(supp);
    }
    //Set data in Spinner
    public void setDataSpinner(Spinner spner, Context cxt, String sql) {
        final String qry = sql;
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url.find, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Toast.makeText(Invetigations.this, response, Toast.LENGTH_LONG).show();
                    final String[] rst = response.split(",");
                    //final String[] spinerData;
                    try {
                        for (int i = 0; i < rst.length; i++) {
                            //Toast.makeText(Invesgater.this, jsonObject.getString("2"), Toast.LENGTH_LONG).show();
                            arrayList.add(rst[i]);
                            adapter = new ArrayAdapter<String>(cxt, android.R.layout.simple_spinner_item, arrayList);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spner.setAdapter(adapter);
                        }
                    } catch (Exception ex) {
                        Toast.makeText(Magazine.this, ex.getMessage(), Toast.LENGTH_LONG).show();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(cxt, "Error :> " + error, Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("qry", qry);

                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(Magazine.this);
            queue.add(stringRequest);
        } catch (Exception ex) {
            Toast.makeText(cxt, "Eros " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // Getting Date
    public void getDate(EditText editText) {
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        picker = new DatePickerDialog(Magazine.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        editText.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, year, month, day);
        picker.show();
    }
}