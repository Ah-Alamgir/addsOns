package com.waskuroni.addons;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{
    Context context;
    ArrayList<String> imageUrl;

    public MyAdapter(Context context, ArrayList<String> imageUrl) {
        this.context = context;
        this.imageUrl = imageUrl;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.webpages, parent, false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.itemView.setOnClickListener(v -> {
            webview.link = imageUrl.get(position);
            v.getContext().startActivity(new Intent(this.context, webview.class));

        });
    }

    @Override
    public int getItemCount() {
        return imageUrl.size();
    }
}





class MyViewHolder extends RecyclerView.ViewHolder{
    public ImageView imageView;
    CardView cardView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.games);
        cardView = itemView.findViewById(R.id.cardView);
    }





}