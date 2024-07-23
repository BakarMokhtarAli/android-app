package com.example.faciltymagazine;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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

public class SignUp extends AppCompatActivity {
    Spinner u_types;
    EditText u_fnames,u_emails,u_passs,u_dates,search;
    String getID = null;
    Url url = new Url();
    DatePickerDialog picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        u_fnames = findViewById(R.id.u_fnames);
        u_emails = findViewById(R.id.u_emails);
        u_passs = findViewById(R.id.u_passs);
        u_types = findViewById(R.id.u_types);
        u_dates = findViewById(R.id.u_dates);




        u_dates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDate(u_dates);
            }
        });
    }
    // this method can allow to make inserting data
    public void save(View view){
        final String qry = "call users_sp('null','"+u_fnames.getText().toString()+"','"+u_emails.getText().toString()+
                "','"+u_passs.getText().toString()+"','"+u_types.getSelectedItem().toString()+"','"+u_dates.getText().toString()+"','insert')";
        try{
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url.setPost, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(SignUp.this, response, Toast.LENGTH_LONG).show();
                    Intent login = new Intent(SignUp.this,Login.class);
                    startActivity(login);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(SignUp.this, "Error :> "+error, Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("qry",qry);

                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(SignUp.this);
            queue.add(stringRequest);
        }catch (Exception ex){
            Toast.makeText(SignUp.this, "Eros "+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    // Getting Date
    public void getDate(EditText editText){
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        picker = new DatePickerDialog(SignUp.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        editText.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, year, month, day);
        picker.show();
    }
}