package com.example.faciltymagazine;

import android.app.DatePickerDialog;
import android.content.Context;
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

public class Subscription extends AppCompatActivity {
    Spinner userID, magazineID;
    EditText subscriptionDate, search;
    String getID = null, userIDs,magazineIDs;
    Url url = new Url();
    DatePickerDialog picker;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayList<String> arrayList1 = new ArrayList<>();
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapter1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);

        try {
            //userID = findViewById(R.id.userID);
            magazineID = findViewById(R.id.magazineID);
            subscriptionDate = findViewById(R.id.subscriptionDate);
            SharedPreferences mySharedPreferences = getSharedPreferences("usernameLogin", MODE_PRIVATE);
            userIDs = mySharedPreferences.getString("fname", "");
            //setDataSpinner(userID, getApplicationContext(), "select u.username from users u where u.deleted=0");
            setDataSpinner2(magazineID, getApplicationContext(), "select m.title from magazines m where m.deleted=0");

            magazineID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    //
                    magazineIDs = parent.getItemAtPosition(position).toString();
                    //Toast.makeText(Articales.this, parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    //
                }
            });

            search = findViewById(R.id.search);

            subscriptionDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getDate(subscriptionDate);
                }
            });
        }catch (Exception ex){
            Toast.makeText(Subscription.this, ex.getMessage().toString(), Toast.LENGTH_LONG).show();
        }
    }

    // this method can allow to make inserting data
    public void save(View view) {
        final String qry = "call subscriptions_sp('" + getID + "','" + userIDs + "','"  + magazineIDs +
                "','" + subscriptionDate.getText().toString() + "','insert')";
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url.setPost, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Toast.makeText(UsersManage.this, response, Toast.LENGTH_LONG).show();
                    url.alertMessage (Subscription.this, "Response Message", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Subscription.this, "Error :> " + error, Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("qry", qry);

                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(Subscription.this);
            queue.add(stringRequest);
        } catch (Exception ex) {
            Toast.makeText(Subscription.this, "Eros " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // this method can allow to make inserting data
    public void update(View view) {
        final String qry = "call subscriptions_sp('" + getID + "','" + userIDs + "','"  + magazineIDs +
                "','" + subscriptionDate.getText().toString() + "','update')";
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url.setPost, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Toast.makeText(UsersManage.this, response, Toast.LENGTH_LONG).show();
                    url.alertMessage (Subscription.this, "Response Message", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Subscription.this, "Error :> " + error, Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("qry", qry);

                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(Subscription.this);
            queue.add(stringRequest);
        } catch (Exception ex) {
            Toast.makeText(Subscription.this, "Eros " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // this method can allow to make inserting data
    public void delete(View view) {
        final String qry = "call subscriptions_sp('" + getID + "','" + userIDs + "','"  + magazineIDs +
                "','" + subscriptionDate.getText().toString() + "','delete')";
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url.setPost, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Toast.makeText(UsersManage.this, response, Toast.LENGTH_LONG).show();
                    url.alertMessage (Subscription.this, "Response Message", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Subscription.this, "Error :> " + error, Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("qry", qry);

                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(Subscription.this);
            queue.add(stringRequest);
        } catch (Exception ex) {
            Toast.makeText(Subscription.this, "Eros " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // this method can allow to make Searching data
    public void show(View view) {
        final String qrys = "select * from subscriptions s where s.deleted=0 and s.subscription_id=" + search.getText().toString();
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url.find, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Toast.makeText(Invesgater.this, response, Toast.LENGTH_LONG).show();
                    final String[] rst = response.split(",");
                    for (int i = 0; i < rst.length; i++) {
                        //Toast.makeText(Invesgater.this, jsonObject.getString("2"), Toast.LENGTH_LONG).show();
                        getID = rst[0];
                        subscriptionDate.setText(rst[3]);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Subscription.this, "Error :> " + error, Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("qry", qrys);

                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(Subscription.this);
            queue.add(stringRequest);
        } catch (Exception ex) {
            Toast.makeText(Subscription.this, "Eros " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    // this method can allow to make Showing table data
    public void info(View view) {
        final String qry = "select s.subscription_id ID, u.username, m.title from subscriptions s, users u, magazines m where s.deleted=0 and s.user_id=u.username and s.magazine_id=m.title;";
        setContentView(R.layout.table_info);
        WebView wed;
        wed = findViewById(R.id.wed);

        wed.setWebViewClient(new WebViewClient());
        wed.getSettings().setJavaScriptEnabled(true);
        wed.loadUrl(url.display + "?id=" + qry);
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
                        Toast.makeText(Subscription.this, ex.getMessage(), Toast.LENGTH_LONG).show();
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
            RequestQueue queue = Volley.newRequestQueue(Subscription.this);
            queue.add(stringRequest);
        } catch (Exception ex) {
            Toast.makeText(cxt, "Eros " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void setDataSpinner2(Spinner spner, Context cxt, String sql) {
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
                            arrayList1.add(rst[i]);
                            adapter1 = new ArrayAdapter<String>(cxt, android.R.layout.simple_spinner_item, arrayList1);
                            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spner.setAdapter(adapter1);
                        }
                    } catch (Exception ex) {
                        Toast.makeText(Subscription.this, ex.getMessage(), Toast.LENGTH_LONG).show();
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
            RequestQueue queue = Volley.newRequestQueue(Subscription.this);
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
        picker = new DatePickerDialog(Subscription.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        editText.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, year, month, day);
        picker.show();
    }
}