package com.waskuroni.addons;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class instGameRecyclelar extends RecyclerView.Adapter<instGameRecyclelar.ViewHolder> {

    private List<String> mData;
    String[] collectText;
    private ArrayList<String> webLink= new ArrayList<>();
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    instGameRecyclelar(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.webpages, parent, false);
        return new ViewHolder(view);
    }













    // view setup
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        collectText = mData.get(position).split(",");
        holder.collects.setText(collectText[1]);
        holder.gameName.setText(collectText[2]);
        webLink.add(collectText[1]);
        Picasso.get().load(collectText[3]).into(holder.imageView);
        holder.cardView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.anim1));
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView gameName;
        TextView collects;
        ImageView  imageView;
        CardView cardView;

        ViewHolder(View itemView) {
            super(itemView);
            gameName = itemView.findViewById(R.id.gameNAme);
            collects = itemView.findViewById(R.id.collect);
            imageView  = itemView.findViewById(R.id.gameImage);
            cardView = itemView.findViewById(R.id.cardView3);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }













    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);



    }
}