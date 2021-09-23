package com.example.videogameapp;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import android.content.res.Resources;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TopPicksAdapter extends RecyclerView.Adapter<TopPicksAdapter.TopPicksViewHolder> {
    private ArrayList<Videogame> mTopPickList;
    private ItemClickListener mClickListener;

    public class TopPicksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView TPImageView;
        public TextView TPTextView;

        public TopPicksViewHolder(@NonNull View itemView) {
            super(itemView);
            TPImageView = itemView.findViewById(R.id.top_pick_image);
            TPTextView = itemView.findViewById(R.id.top_picks_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    public TopPicksAdapter(ArrayList<Videogame> topPickList){
        mTopPickList = topPickList;
    }

    @NonNull
    @Override
    public TopPicksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_picks_layout, parent, false);
        TopPicksViewHolder tpvh = new TopPicksViewHolder(v);
        return tpvh;
    }

    @Override
    public void onBindViewHolder(@NonNull TopPicksViewHolder holder, int position) {
        Videogame currentItem = mTopPickList.get(position);
        Resources res = holder.itemView.getContext().getResources();
        String tpCoverAddress = currentItem.getCoverAddress();
        int TPCoverId = res.getIdentifier(tpCoverAddress, "drawable", holder.itemView.getContext().getPackageName());
        holder.TPImageView.setImageResource(TPCoverId);

        holder.TPTextView.setText(currentItem.getName());
    }

    @Override
    public int getItemCount() {
        return mTopPickList.size();
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return mTopPickList.get(id).getName();
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
