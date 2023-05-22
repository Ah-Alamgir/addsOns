package com.waskuroni.addons;


import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Objects;


public class autoLoad {
    public static String userName = "@hanif", points = "500", followed = "@hanif, @tikfollow" ;
    static RewardedAd mRewardedAd;
    private static InterstitialAd mInterstitialAd;
    public static boolean connection = false;
    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static ArrayList<String> nameList = new ArrayList<>();
    public static ArrayList<String> follow = new ArrayList<>();
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




    public static void loadReward(Context context,Activity activity, String id, String screen) {
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
                            showReward(context, activity, id, screen);
                        }

                    });
        }else {
            showReward(context, activity, id, screen);
        }

    }




    public static void showReward(Context context,Activity activity, String id, String screen) {

        if (mRewardedAd == null) {
            Log.d("TAG", "The rewarded ad wasn't ready yet.");
            return;
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
                        if(screen == "bonus"){
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setTitle(R.string.app_name);
                            builder.setMessage("You will get your offer within a day. Please keep patience");
                            AlertDialog alert = builder.create();
                            alert.show();
                        }
                        RewardShowing = false;
                    }
                });

        mRewardedAd.show(
                activity,
                rewardItem -> {
                    int rewardAmount = rewardItem.getAmount();
                    if (Objects.equals(screen, "doTask")){
                        points = String.valueOf(Integer.parseInt(points)+rewardAmount);
                        savedata(userName);

                    } else if (Objects.equals(screen, "profile")) {
                        points = String.valueOf(Integer.parseInt(points)+rewardAmount);
                        savedata(userName);
                    }else {
                        isRewarded = true;
                    }
                });

    }

































    public static void getDatas() {
        DatabaseReference myRef = database.getReference("tikfan");


        myRef.get().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.e("firebase", "Error getting data", task.getException());
            } else {
                String dict = String.valueOf(task.getResult().getValue());

            }
        });
    }


    public static void savedata(String userName) {
        DatabaseReference myRef = database.getReference("tikfan");
        myRef.child(userName).setValue(Integer.valueOf(points));
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
    
    
    public static void signup(String name,String phone, String email, String password, Context context  ){
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult ->
                        firebaseFirestore.collection("users")
                                .document(authResult.getUser().getUid())
                              .set(new signDetail(name, email, password, phone))
                              .addOnSuccessListener(aVoid -> {
                                  Toast.makeText(context, "Signup Successful", Toast.LENGTH_SHORT).show();


                              })
                              .addOnFailureListener(e -> Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show())
                        )

                .addOnFailureListener(e -> Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show());
    }
    
    
    public static void signin(String email, String password, Context context){
        Task<AuthResult> firebaseAuth = FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
              .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show();
                    }
                })
              .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                       
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    
    public static void resetPassword(String email, Context context){
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
              .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, "Check email", Toast.LENGTH_LONG).show();
                    }
                })
              .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }






}

