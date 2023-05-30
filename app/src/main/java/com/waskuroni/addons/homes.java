package com.waskuroni.addons;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

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

import java.util.ArrayList;

public class homes extends AppCompatActivity {
    DrawerLayout drawerLayout;

    NavigationView navigationView;
    ActionBarDrawerToggle toggle;

    TextView textView;
    ImageView imageMenu;


     homeRecycler adapter;
    ArrayList<String> homeArrayList= new ArrayList<>();

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homes);






        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_View);
        navigationView.setItemIconTintList(null);
        imageMenu = findViewById(R.id.profileback);

        toggle = new ActionBarDrawerToggle(homes.this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        Button addclicked = findViewById(R.id.addclick);
        Button profile = findViewById(R.id.profile);
        Button ref = findViewById(R.id.refer);

        textView = findViewById(R.id.textView3);
        textView.setText(String.valueOf(autoLoad.points));
        getPoints(autoLoad.userName);







        addclicked.setOnClickListener(v -> startActivity(new Intent(this, playAds.class)));
        ref.setOnClickListener(v -> startActivity(new Intent(this, refetence.class)));
        profile.setOnClickListener(v -> startActivity(new Intent(this,profile.class)));







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



        imageMenu = findViewById(R.id.profileback);

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
            creathomeRecycle();
        });
    }



int j=0;
    int k=0;
    public void creathomeRecycle(){
        try {
            for (int i = 0; i <18;i++){
                if(i<6){
                    homeArrayList.add(autoLoad.instGame.get(i));
                } else if(i>5 &&i<12){
                    homeArrayList.add(autoLoad.webSites.get(j));
                    j++;
                } else if (i>11&&i<18) {
                    homeArrayList.add(autoLoad.affiliates.get(k));
                    k++;
                }

            }

            recyclerView = findViewById(R.id.homeRecycler);
            recyclerView.setHasFixedSize(true);
            adapter = new homeRecycler(this, homeArrayList);
            StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(staggeredGridLayoutManager);
            recyclerView.setAdapter(adapter);

        }catch (Exception e){}

    }
}