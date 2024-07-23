package com.example.faciltymagazine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    TextView lbVwTxtLogUp, u_signUp;
    EditText txtUsername,txtUpass;
    Button btn_SignIn;
    Url url = new Url();
    SharedPreferences  sharedPreferences ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername = findViewById(R.id.log_username);
        txtUpass = findViewById(R.id.log_pass);
        btn_SignIn = findViewById(R.id.btn_SignIn);
        u_signUp = findViewById(R.id.u_signUp);
        // this condition is checkers those feilds
        if(TextUtils.isEmpty(txtUsername.getText())){
            txtUsername.setError("username is required");
        }else if(TextUtils.isEmpty(txtUpass.getText())){
            txtUpass.setError("password is required");
        }

        u_signUp.setOnClickListener(v -> {
            Intent login = new Intent(Login.this,SignUp.class);
            startActivity(login);
        });

        btn_SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    final String usname = txtUsername.getText().toString();
                    final String uspass = txtUpass.getText().toString();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url.url_lo,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    //Toast.makeText(Login.this, "Successfull :> "+response, Toast.LENGTH_LONG).show();
                                    try {
                                        // initialize response JSON Array
                                        JSONArray jsonArray = new JSONArray(response);
                                        // parse array
                                        //parseArray(jsonArray);
                                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                                        String rest = jsonObject.getString("state");
                                        String ID = jsonObject.getString("ID");
                                        String fname = jsonObject.getString("fname");
                                        String email = jsonObject.getString("email");
                                        String role = jsonObject.getString("role");
                                        if (rest.equals("success") && role.equals("admin")) {
                                            sharedPreferences = getSharedPreferences("usernameLogin", MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString("ID", ID);
                                            editor.putString("fname", fname);
                                            editor.putString("email", email);
                                            editor.putString("role", role);
                                            editor.apply();

                                            Intent home = new Intent(Login.this,MainActivity.class);
                                            home.putExtra("ID",ID);
                                            home.putExtra("fname",fname);
                                            home.putExtra("email",email);
                                            home.putExtra("role",role);
                                            startActivity(home);
                                            //Toast.makeText(Login.this, role, Toast.LENGTH_LONG).show();
                                        } else if (rest.equals("success") && role.equals("editor")){
                                            sharedPreferences = getSharedPreferences("usernameLogin", MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString("ID", ID);
                                            editor.putString("fname", fname);
                                            editor.putString("email", email);
                                            editor.putString("role", role);
                                            editor.apply();

                                            Intent home = new Intent(Login.this,MainActivity.class);
                                            home.putExtra("ID",ID);
                                            home.putExtra("fname",fname);
                                            home.putExtra("email",email);
                                            home.putExtra("role",role);
                                            startActivity(home);
                                            //Toast.makeText(Login.this, role, Toast.LENGTH_LONG).show();
                                        } else if (rest.equals("success") && role.equals("faculty")){
                                            sharedPreferences = getSharedPreferences("usernameLogin", MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString("ID", ID);
                                            editor.putString("fname", fname);
                                            editor.putString("email", email);
                                            editor.putString("role", role);
                                            editor.apply();

                                            Intent home = new Intent(Login.this,MainActivity.class);
                                            home.putExtra("ID",ID);
                                            home.putExtra("fname",fname);
                                            home.putExtra("email",email);
                                            home.putExtra("role",role);
                                            startActivity(home);
                                            //Toast.makeText(Login.this, role, Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(Login.this, "Something wrong please try again", Toast.LENGTH_LONG).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Login.this, "Error :> "+error, Toast.LENGTH_LONG).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<String, String>();
                            params.put("username",usname);
                            params.put("pass",uspass);
                            return params;
                        }
                    };
                    RequestQueue queue = Volley.newRequestQueue(Login.this);
                    queue.add(stringRequest);
                }catch (Exception ex){
                    Toast.makeText(Login.this, "Eros "+ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}