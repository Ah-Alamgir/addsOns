package com.waskuroni.addons;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class homes extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    ImageView imageMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homes);




        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_View);
        imageMenu = findViewById(R.id.imageMenu);

        toggle = new ActionBarDrawerToggle(homes.this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        Button addclicked = findViewById(R.id.addclick);
        Button profile = findViewById(R.id.profile);
        Button ref = findViewById(R.id.refer);



        addclicked.setOnClickListener(v -> startActivity(new Intent(this, playAds.class)));
        ref.setOnClickListener(v -> startActivity(new Intent(this, refetence.class)));


















































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
            } else if (id == R.id.web) {
                startActivity(new Intent(homes.this, visitWebsite.class));
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
}