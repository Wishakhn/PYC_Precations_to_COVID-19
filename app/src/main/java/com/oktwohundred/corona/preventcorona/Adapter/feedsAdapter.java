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
import com.oktwohundred.corona.preventcorona.Model.feeds;
import com.oktwohundred.corona.preventcorona.R;

import java.util.ArrayList;
import java.util.List;


public class feedsAdapter extends RecyclerView.Adapter<feedsAdapter.feedViewholder> {
    Context context;
    List<feeds> feedsItem;
    List<child> childItem = new ArrayList<>();
    childAdapter cAdapter = new childAdapter(childItem);

    public feedsAdapter(Context context, List<feeds> feedsItem) {
        this.context = context;
        this.feedsItem = feedsItem;
    }

    @NonNull
    @Override
    public feedsAdapter.feedViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      LayoutInflater  layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_feeds, parent, false);
        return new feedViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final feedsAdapter.feedViewholder holder, int position) {
        feeds model = feedsItem.get(position);
        String feedtype = model.getFeedType();
        String title = model.getFeedName();
        String descript = model.getFeedDescrip();
        float rating = model.getFeedRating();
        boolean saved = model.isSaved();
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

    private void getallchilds() {
        childItem.clear();
        childItem.add(new child("Water","4 cups"));
        childItem.add(new child("Ginger","2 tbs"));
        childItem.add(new child("Mint","4 leaves"));
        childItem.add(new child("Honney","4 tbs"));
        cAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return feedsItem.size();
    }

    public class feedViewholder extends RecyclerView.ViewHolder {
        TextView cardtitile;
        TextView cardescrip;
        RelativeLayout containerdetails;
        TextView tips;
        RecyclerView childcycler;
        ImageView display;
        RatingBar reviewrating;
        CardView savebtn;
        TextView savetext;
        public feedViewholder(@NonNull View iv) {
            super(iv);
            cardtitile = iv.findViewById(R.id.cardtitile);
            cardescrip = iv.findViewById(R.id.cardescrip);
            containerdetails = iv.findViewById(R.id.containerdetails);
            tips = iv.findViewById(R.id.tips);
            childcycler = iv.findViewById(R.id.childcycler);
            childcycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
            display = iv.findViewById(R.id.display);
            reviewrating = iv.findViewById(R.id.reviewrating);
            savebtn = iv.findViewById(R.id.savebtn);
            savetext = iv.findViewById(R.id.savetext);


        }
    }
}
