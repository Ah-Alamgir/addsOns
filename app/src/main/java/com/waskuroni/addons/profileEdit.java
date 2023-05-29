package com.waskuroni.addons;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class profileEdit extends AppCompatActivity {

    EditText first_name;
    EditText youtubee;
    EditText instagram;
    EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileedit_details);

        first_name = findViewById(R.id.editText_name_editPro);
        youtubee = findViewById(R.id.youtube);
        instagram = findViewById(R.id.instagram);
        phone = findViewById(R.id.phone);
        Button update= findViewById(R.id.button_editPro);

        update.setOnClickListener(v -> updateFirestore());

    }



    public void updateFirestore(){
        String nas= first_name.getText().toString();
        Map<String, Object> newInfor = new HashMap<>();
        newInfor.put("name", nas);
        newInfor.put("youtube", youtubee.getText().toString());
        newInfor.put("instagram", instagram.getText().toString());
        newInfor.put("phone", phone.getText().toString());
        FirebaseFirestore.getInstance()
                .collection("users")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .update(newInfor)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        autoLoad.alart(profileEdit.this, "Saved successfully");
                    }
                });
    }
}