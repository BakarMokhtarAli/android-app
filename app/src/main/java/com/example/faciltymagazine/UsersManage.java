package com.example.faciltymagazine;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class UsersManage extends AppCompatActivity {
    EditText u_fname,u_email,u_pass,u_date,search;
    Spinner u_type;
    String getID = null;
    Url url = new Url();
    DatePickerDialog picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_manage);

        u_fname = findViewById(R.id.u_fname);
        u_email = findViewById(R.id.u_email);
        u_pass = findViewById(R.id.u_pass);
        u_type = findViewById(R.id.u_type);
        u_date = findViewById(R.id.u_date);

        search = findViewById(R.id.search);

        u_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDate(u_date);
            }
        });
    }
    // this method can allow to make inserting data
    public void save(View view){
        final String qry = "call users_sp('null','"+u_fname.getText().toString()+"','"+u_email.getText().toString()+"','"+
                u_pass.getText().toString()+"','"+u_type.getSelectedItem().toString()+"','"+u_date.getText().toString()+"','insert')";
        try{
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url.setPost, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Toast.makeText(UsersManage.this, response, Toast.LENGTH_LONG).show();
                    url.alertMessage (UsersManage.this, "Response Message", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(UsersManage.this, "Error :> "+error, Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("qry",qry);

                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(UsersManage.this);
            queue.add(stringRequest);
        }catch (Exception ex){
            Toast.makeText(UsersManage.this, "Eros "+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    // this method can allow to make inserting data
    public void update(View view){
        final String qry = "call users_sp('"+getID+"','"+u_fname.getText().toString()+"','"+u_email.getText().toString()+"','"+
                u_pass.getText().toString()+"','"+u_type.getSelectedItem().toString()+"','"+u_date.getText().toString()+"','update')";
        try{
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url.setPost, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Toast.makeText(UsersManage.this, response, Toast.LENGTH_LONG).show();
                    url.alertMessage (UsersManage.this, "Response Message", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(UsersManage.this, "Error :> "+error, Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("qry",qry);

                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(UsersManage.this);
            queue.add(stringRequest);
        }catch (Exception ex){
            Toast.makeText(UsersManage.this, "Eros "+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    // this method can allow to make inserting data
    public void delete(View view){
        final String qry = "call users_sp('"+getID+"','"+u_fname.getText().toString()+"','"+u_email.getText().toString()+"','"+
                u_pass.getText().toString()+"','"+u_type.getSelectedItem().toString()+"','"+u_date.getText().toString()+"','delete')";
        try{
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url.setPost, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Toast.makeText(UsersManage.this, response, Toast.LENGTH_LONG).show();
                    url.alertMessage (UsersManage.this, "Response Message", response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(UsersManage.this, "Error :> "+error, Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("qry",qry);

                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(UsersManage.this);
            queue.add(stringRequest);
        }catch (Exception ex){
            Toast.makeText(UsersManage.this, "Eros "+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    // this method can allow to make Searching data
    public void show(View view){
        final String qrys = "select u.user_id,u.username,u.email,u.password,u.role,u.reg_date from users u where u.deleted=0 and u.user_id="+search.getText().toString();
        try{
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url.find, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //Toast.makeText(Invesgater.this, response, Toast.LENGTH_LONG).show();
                    final String[] rst = response.split(",");
                    for (int i = 0; i <rst.length; i++){
                        //Toast.makeText(Invesgater.this, jsonObject.getString("2"), Toast.LENGTH_LONG).show();
                        getID = rst[0];
                        u_fname.setText(rst[1]);
                        u_email.setText(rst[2]);
                        u_pass.setText(rst[3]);
                        u_date.setText(rst[4]);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(UsersManage.this, "Error :> "+error, Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("qry",qrys);

                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(UsersManage.this);
            queue.add(stringRequest);
        }catch (Exception ex){
            Toast.makeText(UsersManage.this, "Eros "+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    // this method can allow to make Showing table data
    public void info(View view){
        Intent supp = new Intent(UsersManage.this, Table_info.class);
        supp.putExtra("qry","select u.user_id ID,u.username 'Full name',u.email 'Email',u.role 'User Type',u.reg_date 'Created Date' from users u where u.deleted=0");
        startActivity(supp);
    }
    // Getting Date
    public void getDate(EditText editText){
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        picker = new DatePickerDialog(UsersManage.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        editText.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, year, month, day);
        picker.show();
    }
}