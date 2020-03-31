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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oktwohundred.corona.preventcorona.LocalDatabase.DbManager;
import com.oktwohundred.corona.preventcorona.Model.child;
import com.oktwohundred.corona.preventcorona.Model.feeds;
import com.oktwohundred.corona.preventcorona.Model.savedfeeds;
import com.oktwohundred.corona.preventcorona.R;

import java.util.ArrayList;
import java.util.List;

public class saveditemAdapter extends RecyclerView.Adapter<saveditemAdapter.savedViewholder> {

    Context context;
    List<feeds> savedItems;


    public saveditemAdapter(Context context, List<feeds> savedItems) {
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
        feeds model = savedItems.get(position);
        String feedtype = model.getFeedType();
        String title = model.getFeedName();
        String descript = model.getFeedDescrip();
        float rating = Float.parseFloat(model.getFeedRating());
        final String id = model.getFeedId();
        List<child> isChild = model.getIngs();



        if (feedtype.equalsIgnoreCase("remedy")){
            holder.display.setVisibility(View.VISIBLE);
            childAdapter cAdapter = new childAdapter(isChild);
            holder.childcycler.setAdapter(cAdapter);
            cAdapter.notifyDataSetChanged();
        }
        else {
            holder.display.setVisibility(View.GONE);
        }
        holder.savebtn.setVisibility(View.INVISIBLE);
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
