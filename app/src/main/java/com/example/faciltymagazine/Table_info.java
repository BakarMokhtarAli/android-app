package com.example.faciltymagazine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;
import java.util.Map;

public class Table_info extends AppCompatActivity {
    Url url = new Url();
    String ID, fname, email, role ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_info);
        final String qry = getIntent().getStringExtra("qry");;
        //setContentView(R.layout.table_info);
        try {
            //this.setContentView(R.layout.table_info);
            WebView wed;
            wed = findViewById(R.id.wed);

            wed.setWebViewClient(new WebViewClient());
            wed.getSettings().setJavaScriptEnabled(true);
            wed.loadUrl(url.display+"?id="+qry);
        }catch (Exception ex) {
            url.alertMessage (Table_info.this, "Error Message", ex.getMessage());
        }
    }
}
