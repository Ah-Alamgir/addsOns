package com.waskuroni.addons;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;

import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class autoLoad {
    public static FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();;
    public static FirebaseFirestore db =FirebaseFirestore.getInstance();;
    public static String userName = "@hanif";
    public static int layoutId = R.layout.webpages;;
    public static int addShowed = 3;
    public static int points = 500;
    static RewardedAd mRewardedAd;
    private static InterstitialAd mInterstitialAd;
    public static boolean connection = false;
    public static FirebaseDatabase database = FirebaseDatabase.getInstance();

    static boolean isLoading, isRewarded = false, RewardShowing = false;


    // splash screen theke purbe kader follow kora hoiche oi id gula "followed" variable a
    //add kora holo.


    //for https
    static RequestQueue queue;

    static String url = "https://www.google.com/";


    public static void alart(Context context, String text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.app_name);
        builder.setMessage(text);
        AlertDialog alert = builder.create();
        alert.show();
    }


    public static void checkNetwork(Context context) {
        queue = Volley.newRequestQueue(context);
        @SuppressLint("SetTextI18n") StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> connection = true,
                error -> {
                    connection = false;
                    alart(context, "Please turn on your data connection");
                });

        queue.add(stringRequest);
    }

    public static boolean isPackageExisted(Context context){
        final PackageManager packageManager = context.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage("com.instagram.android");
        if (intent == null) {
            return false;
        }else{
            return true;
        }
    }

    public static void loadBanner(Context context, String gravity) {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.TOP);
        AdView adView = new AdView(context);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-9422110628550448/8550539984");
        AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
        layout.addView(adView);
        adView.loadAd(adRequestBuilder.build());
    }


    public static void loadAdd(Context context) {
        MobileAds.initialize(context, initializationStatus -> {
        });
    }

    public static void loadInter(Context context, Activity activity) {
        if (mInterstitialAd== null){
            AdRequest loadInter = new AdRequest.Builder().build();

            InterstitialAd.load(context, "ca-app-pub-9422110628550448/7543745921", loadInter,
                    new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                            mInterstitialAd = interstitialAd;
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            // Handle the error
                            mInterstitialAd = null;
                        }
                    });
        }else {
            showInter(activity);
        }


    }



    public static void showInter(Activity activity) {
        if (mInterstitialAd != null) {
            mInterstitialAd.show(activity);
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }
    }




    public static void loadReward(Context context,Activity activity, String id) {
        if (mRewardedAd == null) {
            isLoading = true;
            AdRequest adRequest = new AdRequest.Builder().build();
            RewardedAd.load(context, id, adRequest,
                    new RewardedAdLoadCallback() {
                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {

                            mRewardedAd = null;
                            isLoading = false;
                            Toast.makeText(context, "Ads failed to load try again later", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                            mRewardedAd = rewardedAd;
                            isLoading = false;
                            showReward(context, activity, id);
                        }

                    });
        }else {
            showReward(context, activity, id);
        }

    }




    public static int showReward(Context context, Activity activity, String id) {
        final int[] rewardAmount = {0};
        if (mRewardedAd == null) {
            Log.d("TAG", "The rewarded ad wasn't ready yet.");
            return rewardAmount[0];
        }

        mRewardedAd.setFullScreenContentCallback(
                new FullScreenContentCallback() {
                    @Override
                    public void onAdShowedFullScreenContent() {
                        RewardShowing = true;
                    }


                    @Override
                    public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                        mRewardedAd = null;

                    }

                    @Override
                    public void onAdDismissedFullScreenContent() {
                        mRewardedAd = null;
                        RewardShowing = false;
                    }
                });

        mRewardedAd.show(
                activity,
                rewardItem -> {
                    rewardAmount[0] = rewardItem.getAmount();
                });

        return rewardAmount[0];

    }

































    public static void getPoints(String tags) {

        DatabaseReference myRef = database.getReference("userPoints");

        myRef.child(tags).get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            }
            else {
                points = Integer.valueOf(task.getResult().getValue(Integer.class));
            }
        });
    }


    public static void savePoints(String userNames, Integer pointses) {
        DatabaseReference myRef = database.getReference("userPoints");
        myRef.child(userNames).setValue(Integer.valueOf(pointses));
    }

    public static void withDraw( String pointses) {
        DatabaseReference myRef = database.getReference("withdraw");
        myRef.child(userName).setValue(pointses);
    }

    public static void removedata(String userName) {
        DatabaseReference myRef = database.getReference("tikfan");
        myRef.child(userName).removeValue();

    }


    public static void storePlusMinus(Integer pluspoints, String minusUser, Integer minusPoints) {
        DatabaseReference myRef = database.getReference("tikfan");
        myRef.child(userName).setValue(pluspoints);
        myRef.child(minusUser).setValue(minusPoints);
    }
    
    
    public static String signup(String name,String phone, String email, String password  ){
        Map<String, Object> newInfor = new HashMap<>();
        newInfor.put("name", name);
        newInfor.put("phone", phone);
        newInfor.put("email", email);
        newInfor.put("password", password);
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult ->
                        db.collection("users")
                                .document(authResult.getUser().getUid())
                              .set(newInfor)
                              .addOnSuccessListener(aVoid -> {

                                  userName = firebaseAuth.getCurrentUser().getUid();
                                  points = 500;
                                  savePoints(userName, points);
                                  result = "success";


                              })
                              .addOnFailureListener(e -> result= e.getMessage())
                        )

                .addOnFailureListener(e -> {
                    result= e.getMessage();
                });
        return result;


    }


//    public static void saveSharePref(String name,String phone, String email, String password, Context context){
//
//        SharedPreferences pref = context.getSharedPreferences("MyPref", 0);
//        SharedPreferences.Editor editor = pref.edit();
//        editor.putString("name", name);
//        editor.putString("phone", phone);
//        editor.putString("email", email);
//        editor.putString("password", password);
//        editor.apply();
//        context.startActivity(new Intent(context, homes.class));
//    }



//    public static ArrayList<String> userData = new ArrayList<>();
//
//    public static ArrayList<String> getuserdetailsFromFirebase(Activity activity){
//        DocumentReference docRef = db.collection("users").document(firebaseAuth.getCurrentUser().getUid());
//        docRef.addSnapshotListener(activity, (value, error) -> {
//            try {
//                userData.add(value.getString("name"));
//                userData.add(value.getString("phone"));
//                userData.add(value.getString("email"));
//                userData.add(value.getString("password"));
//                userData.add(value.getString("youtube"));
//                userData.add(value.getString("instagram"));
//            }catch(Exception e) {
//
//            }
//
//        });
//
//        return userData;
//    }
//

















    public static String result;
    
    public static String signin(String email, String password){
        Task<AuthResult> firebaseAuth = FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
              .addOnSuccessListener(authResult -> {
                  userName = FirebaseAuth.getInstance().getCurrentUser().getUid();
                  result= "success";
              })
              .addOnFailureListener(e -> result= e.getMessage());
        return result;
    }
    
    public static void resetPassword(String email, Context context){
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
              .addOnSuccessListener(aVoid -> Toast.makeText(context, "Check email", Toast.LENGTH_LONG).show())
              .addOnFailureListener(e -> Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show());
    }











    //Get Data to show on reclelar view



    public static  ArrayList<String> instGame = new ArrayList<>();
    public static ArrayList<String> webSites= new ArrayList<>();
    public static  ArrayList<String> affiliates = new ArrayList<>();


    public static void getdata(String bucket) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(bucket);
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String s) {
                String link = snapshot.getValue(String.class);
                if(bucket == "web"){
                    webSites.add(link);
                }else if (bucket == "instantGame") {
                    instGame.add(link);
                }else if (bucket=="affliate") {
                    affiliates.add(link);
                }


            }


            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }


        });

        if(bucket == "web"){
            getdata("instantGame");
        } else if (bucket== "instantGame"){
            getdata("affliate");
        }


    }









    public static class sqlDatabase extends SQLiteOpenHelper{
        private static final String dbName = "userDara";
        private static final int dbVersion = 1;
        public sqlDatabase(@Nullable Context context) {
            super(context, dbName, null, dbVersion);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE  PRODUCT (_id INTEGER PRIMARY KEY AUTOINCREMENT , NAME TEXT, EMAIL TEXT, PASSWORD TEXT, PHONE TEXT)";
            db.execSQL(sql);
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    private static void insertData(String name, String email, String password, String phone){
        ContentValues values = new ContentValues();
        values.put("NAME", name);
        values.put("EMAIL", email);
        values.put("PASSWORD", password);

        values.put("PHONE", phone);
//        database.insert("PRODUCT", null, values);

    }

}

