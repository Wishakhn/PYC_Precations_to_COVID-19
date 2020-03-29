package com.oktwohundred.corona.preventcorona.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.oktwohundred.corona.preventcorona.Model.child;
import com.oktwohundred.corona.preventcorona.Model.savedfeeds;
import com.oktwohundred.corona.preventcorona.R;

import java.util.ArrayList;
import java.util.List;

public class saveditemAdapter extends RecyclerView.Adapter<saveditemAdapter.savedViewholder> {

    Context context;
    List<savedfeeds> savedItems;
    List<child> childItem = new ArrayList<>();
    childAdapter cAdapter = new childAdapter(childItem);


    public saveditemAdapter(Context context, List<savedfeeds> savedItems) {
        this.context = context;
        this.savedItems = savedItems;
    }

    @NonNull
    @Override
    public saveditemAdapter.savedViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_feeds, parent, false);
        return new savedViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final saveditemAdapter.savedViewholder holder, int position) {
        savedfeeds model = savedItems.get(position);
        String feedtype = model.getFeedType();
        String title = model.getFeedName();
        String descript = model.getFeedDescrip();
        float rating = model.getFeedRating();
        if (feedtype.equalsIgnoreCase("remedy")){
            holder.display.setVisibility(View.VISIBLE);
            getallchilds();
            holder.childcycler.setAdapter(cAdapter);
            cAdapter.notifyDataSetChanged();
        }
        else {
            holder.display.setVisibility(View.GONE);
        }
        holder.cardtitile.setText(title);
        holder.cardescrip.setText(descript);
        holder.reviewrating.setRating(rating);
        holder.display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.containerdetails.getVisibility() == View.GONE){
                    holder.containerdetails.setVisibility(View.VISIBLE);
                    holder.display.setImageResource(R.drawable.uparrow);
                }
                else {
                    holder.containerdetails.setVisibility(View.GONE);
                    holder.display.setImageResource(R.drawable.downarrow_unselected);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return savedItems.size();
    }
    private void getallchilds() {
        childItem.clear();
        childItem.add(new child("Water","4 cups"));
        childItem.add(new child("Ginger","2 tbs"));
        childItem.add(new child("Mint","4 leaves"));
        childItem.add(new child("Honney","4 tbs"));
        cAdapter.notifyDataSetChanged();
    }

    public class savedViewholder extends RecyclerView.ViewHolder {
        TextView cardtitile;
        TextView cardescrip;
        RelativeLayout containerdetails;
        TextView tips;
        RecyclerView childcycler;
        ImageView display;
        RatingBar reviewrating;
        CardView savebtn;
        TextView savetext;
        public savedViewholder(@NonNull View sv) {
            super(sv);
            cardtitile = sv.findViewById(R.id.cardtitile);
            cardescrip = sv.findViewById(R.id.cardescrip);
            containerdetails = sv.findViewById(R.id.containerdetails);
            tips = sv.findViewById(R.id.tips);
            childcycler = sv.findViewById(R.id.childcycler);
            childcycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            display = sv.findViewById(R.id.display);
            reviewrating = sv.findViewById(R.id.reviewrating);
            savebtn = sv.findViewById(R.id.savebtn);
            savetext = sv.findViewById(R.id.savetext);
        }
    }
}
