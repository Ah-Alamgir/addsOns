package com.waskuroni.addons;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class homes extends AppCompatActivity {
    DrawerLayout drawerLayout;
    Button v1,v2,v3,v4,v5,v6,v7,v8,v9,v10,v11,v12,v13,v14,v15,v16,v17,v18;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    TextView textView;
    ImageView imageMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homes);






        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_View);
        navigationView.setItemIconTintList(null);
        imageMenu = findViewById(R.id.imageMenu);

        toggle = new ActionBarDrawerToggle(homes.this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        Button addclicked = findViewById(R.id.addclick);
        Button profile = findViewById(R.id.profile);
        Button ref = findViewById(R.id.refer);

        textView = findViewById(R.id.textView3);
        getPoints(autoLoad.userName);



        addclicked.setOnClickListener(v -> startActivity(new Intent(this, playAds.class)));
        ref.setOnClickListener(v -> startActivity(new Intent(this, refetence.class)));
        profile.setOnClickListener(v -> startActivity(new Intent(this,profile.class)));

        v1 = findViewById(R.id.v1);
        v2 = findViewById(R.id.v2);
        v3 = findViewById(R.id.v3);
        v4 = findViewById(R.id.v4);
        v5 = findViewById(R.id.v5);
        v6 = findViewById(R.id.v6);
        v7 = findViewById(R.id.v7);
        v8 = findViewById(R.id.v8);
        v9 = findViewById(R.id.v9);
        v10 = findViewById(R.id.v10);
        v11 = findViewById(R.id.v11);
        v12 = findViewById(R.id.v12);

//        v13 = findViewById(R.id.v13);
//        v14 = findViewById(R.id.v14);
//        v15 = findViewById(R.id.v15);
//        v16 = findViewById(R.id.v16);
//        v17 = findViewById(R.id.v17);
//        v18 = findViewById(R.id.v18);




        v1.setOnClickListener(v -> {
            webview.link = autoLoad.home.get(0);
            startActivity(new Intent(this, webview.class));
        });
        v2.setOnClickListener(v -> {
            webview.link = autoLoad.home.get(1);
            startActivity(new Intent(this, webview.class));
        });
        v3.setOnClickListener(v -> {
            webview.link = autoLoad.home.get(2);
            startActivity(new Intent(this, webview.class));
        });
        v4.setOnClickListener(v -> {
            webview.link = autoLoad.home.get(3);
            startActivity(new Intent(this, webview.class));
        });
        v5.setOnClickListener(v -> {
            webview.link = autoLoad.home.get(4);
            startActivity(new Intent(this, webview.class));
        });
        v6.setOnClickListener(v -> {
            webview.link = autoLoad.home.get(5);
            startActivity(new Intent(this, webview.class));
        });
        v7.setOnClickListener(v -> {
            webview.link = autoLoad.home.get(6);
            startActivity(new Intent(this, webview.class));
        });
        v8.setOnClickListener(v -> {
            webview.link = autoLoad.home.get(7);
            startActivity(new Intent(this, webview.class));
        });
        v9.setOnClickListener(v -> {
            webview.link = autoLoad.home.get(8);
            startActivity(new Intent(this, webview.class));
        });
        v10.setOnClickListener(v -> {
            webview.link = autoLoad.home.get(9);
            startActivity(new Intent(this, webview.class));
        });
        v11.setOnClickListener(v -> {
            webview.link = autoLoad.home.get(10);
            startActivity(new Intent(this, webview.class));
        });
        v12.setOnClickListener(v -> {
            webview.link = autoLoad.home.get(11);
            startActivity(new Intent(this, webview.class));
        });
//        v13.setOnClickListener(v -> {
//            webview.link = autoLoad.home.get(12);
//            startActivity(new Intent(this, webview.class));
//        });
//        v14.setOnClickListener(v -> {
//            webview.link = autoLoad.home.get(13);
//            startActivity(new Intent(this, webview.class));
//        });
//        v15.setOnClickListener(v -> {
//            webview.link = autoLoad.home.get(14);
//            startActivity(new Intent(this, webview.class));
//        });
//        v16.setOnClickListener(v -> {
//            webview.link = autoLoad.home.get(15);
//            startActivity(new Intent(this, webview.class));
//        });
//        v17.setOnClickListener(v -> {
//            webview.link = autoLoad.home.get(16);
//            startActivity(new Intent(this, webview.class));
//        });
//        v18.setOnClickListener(v -> {
//            webview.link = autoLoad.home.get(17);
//            startActivity(new Intent(this, webview.class));
//        });













































        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if(id == R.id.games){
                startActivity(new Intent(homes.this, instantGame.class));
            } else if (id == R.id.rate) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+ getPackageName())));
            } else if (id == R.id.nav_home) {
                drawerLayout.closeDrawers();
            } else if (id == R.id.web) {
                startActivity(new Intent(homes.this, visitWebsite.class));
            } else if (id == R.id.refer) {
                startActivity(new Intent(homes.this, ReferCOde.class));
            } else if (id == R.id.Withdraw) {
                startActivity(new Intent(homes.this, withdraw.class));
            } else if (id == R.id.spinner) {
                startActivity(new Intent(homes.this, Spinner.class));
            } else if (id == R.id.share) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey i am using this app and utilizing my free time. Give it a try to earn like me "+ "https://play.google.com/store/apps/details?id="+ getPackageName());
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            } else if (id == R.id.About) {
                startActivity(new Intent(homes.this, aboutUs.class));
            } else if (id == R.id.affilia) {
                startActivity(new Intent(homes.this, Affiliate.class));
            }

            return false;
        });



        ImageButton infos = findViewById(R.id.info);
        infos.setOnClickListener(view -> {
            startActivity(new Intent(homes.this, aboutUs.class));
        });



        imageMenu = findViewById(R.id.imageMenu);

        imageMenu.setOnClickListener(view -> {
            // Code Here
            drawerLayout.openDrawer(GravityCompat.START);
        });

    }



    public void getPoints(String tags) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("userPoints");

        myRef.child(tags).get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            }
            else {
                autoLoad.points = Integer.valueOf(task.getResult().getValue().toString());
                textView.setText(task.getResult().getValue().toString());
            }
        });
    }
}