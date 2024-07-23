package com.example.faciltymagazine;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class MainActivity extends AppCompatActivity {
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    TextView magazineSummery,articleSummery,commentSummery,subscriptionSummery,usersSummery;
    CardView booking_card1, booking_card2, booking_card3, booking_card4, booking_card5;
    Button btn_back;
    Url url = new Url();
    String ID, fname, email, role ;

    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ID = getIntent().getStringExtra("ID");
        fname = getIntent().getStringExtra("fname");
        email = getIntent().getStringExtra("email");
        role = getIntent().getStringExtra("role");

        magazineSummery = findViewById(R.id.magazineSummery);
        articleSummery  = findViewById(R.id.articleSummery);
        commentSummery  = findViewById(R.id.commentSummery);
        subscriptionSummery  = findViewById(R.id.subscriptionSummery);
        usersSummery = findViewById(R.id.usersSummery);
        dashboard ();

        booking_card1 = findViewById(R.id.booking_card1);
        booking_card2  = findViewById(R.id.booking_card2);
        booking_card3  = findViewById(R.id.booking_card3);
        booking_card4  = findViewById(R.id.booking_card4);
        booking_card5 = findViewById(R.id.booking_card5);


        booking_card1.setOnClickListener(v -> {
            //Toast.makeText(MainActivity.this, "Magazine", Toast.LENGTH_LONG).show();
            Intent supp = new Intent(MainActivity.this, Table_info.class);
            supp.putExtra("qry","select m.magazine_id ID, m.title, m.description, m.publication_date 'Publication Date', u.username from magazines m, users u where m.deleted=0 and m.editor_id=u.username;");
            startActivity(supp);
        });
        booking_card2.setOnClickListener(v -> {
            //Toast.makeText(MainActivity.this, "Articles", Toast.LENGTH_LONG).show();
            Intent supp = new Intent(MainActivity.this, Table_info.class);
            supp.putExtra("qry","select a.article_id 'ID',a.title 'Title',a.content 'Content',a.publication_date 'Publication Date', u.username 'Author' from articles a, magazines m, users u where a.deleted=0 and a.author_id=u.username and a.magazine_id=m.title;");
            startActivity(supp);
        });
        booking_card3.setOnClickListener(v -> {
            //Toast.makeText(MainActivity.this, "Comments", Toast.LENGTH_LONG).show();
            Intent supp = new Intent(MainActivity.this, Table_info.class);
            supp.putExtra("qry","select c.comment_id ID, c.content, c.article_id 'Articles', u.username, c.comment_date 'Comments Date' from comments c, users u, articles a where c.deleted=0 and c.article_id=a.title and c.user_id=u.username;");
            startActivity(supp);
        });
        booking_card4.setOnClickListener(v -> {
            //Toast.makeText(MainActivity.this, "Subscription", Toast.LENGTH_LONG).show();
            Intent supp = new Intent(MainActivity.this, Table_info.class);
            supp.putExtra("qry","select s.subscription_id ID, u.username, m.title from subscriptions s, users u, magazines m where s.deleted=0 and s.user_id=u.username and s.magazine_id=m.title;");
            startActivity(supp);
        });
        booking_card5.setOnClickListener(v -> {
            //Toast.makeText(MainActivity.this, "Users", Toast.LENGTH_LONG).show();
            Intent supp = new Intent(MainActivity.this, Table_info.class);
            supp.putExtra("qry","select u.user_id ID,u.username 'Full name',u.email 'Email',u.role 'User Type',u.reg_date 'Created Date' from users u where u.deleted=0");
            startActivity(supp);
        });

        dl = (DrawerLayout)findViewById(R.id.main);
        t = new ActionBarDrawerToggle(this, dl,R.string.nav_open, R.string.nav_close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_articles) {
                    //Toast.makeText(MainActivity.this, "My Account",Toast.LENGTH_SHORT).show();
                    Intent supp = new Intent(MainActivity.this, Articales.class);
                    supp.putExtra("fname",fname);
                    startActivity(supp);
                }else if (id == R.id.nav_comments) {
                    //Toast.makeText(MainActivity.this, "Settings",Toast.LENGTH_SHORT).show();
                    Intent pro = new Intent(MainActivity.this, Comments.class);
                    pro.putExtra("fname",fname);
                    startActivity(pro);
                }else if (id == R.id.nav_magazine) {
                    //Toast.makeText(MainActivity.this, "My Cart",Toast.LENGTH_SHORT).show();
                    Intent order = new Intent(MainActivity.this, Magazine.class);
                    order.putExtra("fname",fname);
                    startActivity(order);
                }else if (id == R.id.nav_subscription) {
                    //Toast.makeText(MainActivity.this, "My Cart",Toast.LENGTH_SHORT).show();
                    Intent delv = new Intent(MainActivity.this, Subscription.class);
                    delv.putExtra("fname",fname);
                    startActivity(delv);
                }else if (id == R.id.nav_usr) {
                    //Toast.makeText(MainActivity.this, "My Cart",Toast.LENGTH_SHORT).show();
                    Intent usr = new Intent(MainActivity.this, UsersManage.class);
                    usr.putExtra("fname",fname);
                    startActivity(usr);
                }else if (id == R.id.nav_logout) {
                    //Toast.makeText(MainActivity.this, "My Cart",Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPreferences = getSharedPreferences("usernameLogin", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("ID", "");
                    editor.putString("fname", "");
                    editor.putString("email", "");
                    editor.putString("role", "");
                    editor.apply();
                    Intent log = new Intent(MainActivity.this, Login.class);
                    startActivity(log);
                    finish();
                }else {
                    return true;
                }
                return true;
            }
        });

    }
    // override the onOptionsItemSelected()
    // function to implement
    // the item click listener callback
    // to open and close the navigation
    // drawer when the icon is clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (t.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void dashboard (){
        final String qry = "call dashboard()";
        try{
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url.find, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    final String[] rst = response.split(",");
                    for (int i = 0; i <rst.length; i++){
                        //Toast.makeText(Invesgater.this, jsonObject.getString("2"), Toast.LENGTH_LONG).show();
                        magazineSummery.setText(rst[0]);
                        articleSummery.setText(rst[1]);
                        commentSummery.setText(rst[2]);
                        subscriptionSummery.setText(rst[3]);
                        usersSummery.setText(rst[4]);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, "Error :> "+error, Toast.LENGTH_LONG).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("qry",qry);

                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
            queue.add(stringRequest);
        }catch (Exception ex){
            Toast.makeText(MainActivity.this, "Eros "+ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}