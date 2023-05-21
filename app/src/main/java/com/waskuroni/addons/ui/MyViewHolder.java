package com.waskuroni.addons.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.waskuroni.addons.R;

public class MyViewHolder extends RecyclerView.ViewHolder{
    public ImageView imageView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.games);
    }
}
