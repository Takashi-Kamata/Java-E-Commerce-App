package com.example.videogameapp;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private ArrayList<Videogame> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    ListAdapter(Context context, ArrayList<Videogame> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String gameName = mData.get(position).getName();
        holder.mNameView.setText(gameName);

        String gamePrice = "$" + String.format("%.2f", mData.get(position).getPrice());
        holder.mPriceView.setText(gamePrice);

        String ratingBar = String.valueOf(mData.get(position).getRating());
        holder.mRatingBar.setRating(Float.parseFloat(ratingBar));

        Resources res = holder.itemView.getContext().getResources();
        String mDrawable = mData.get(position).getCoverAddress();
        int resID = res.getIdentifier(mDrawable , "drawable", holder.itemView.getContext().getPackageName());
        holder.mGameIcon.setImageResource(resID);

        if (mData.get(position).getPreOwned() == false) {
            holder.mBanner.setVisibility(View.VISIBLE);
        }

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView mNameView;
        TextView mPriceView;
        RatingBar mRatingBar;
        ImageView mGameIcon;
        ImageView mBanner;

        ViewHolder(View itemView) {
            super(itemView);
            mNameView = itemView.findViewById(R.id.gameName_row);
            mPriceView = itemView.findViewById(R.id.gamePrice_row);
            mRatingBar = itemView.findViewById(R.id.ratingBar_row);
            mGameIcon = itemView.findViewById(R.id.gameIcon_row);
            mBanner = itemView.findViewById(R.id.banner_row);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }


    // convenience method for getting data at click position
    String getItem(int id) {
        return mData.get(id).getName();
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