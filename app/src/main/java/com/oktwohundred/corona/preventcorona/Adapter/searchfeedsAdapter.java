package com.oktwohundred.corona.preventcorona.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
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
import com.oktwohundred.corona.preventcorona.Model.child;
import com.oktwohundred.corona.preventcorona.Model.feeds;
import com.oktwohundred.corona.preventcorona.R;

import java.util.ArrayList;
import java.util.List;


public class searchfeedsAdapter extends RecyclerView.Adapter<searchfeedsAdapter.feedViewholder> implements Filterable {
    Context context;
    List<feeds> feedsItem;
    List<feeds> scrootedItems;
    List<child> childItem = new ArrayList<>();
    childAdapter cAdapter = new childAdapter(childItem);

    public searchfeedsAdapter(Context context, List<feeds> feedsItem) {
        this.context = context;
        this.feedsItem = feedsItem;
        scrootedItems = feedsItem;

    }

    @NonNull
    @Override
    public searchfeedsAdapter.feedViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      LayoutInflater  layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_feeds, parent, false);
        return new feedViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final searchfeedsAdapter.feedViewholder holder, int position) {
        feeds model = feedsItem.get(position);
        String feedtype = model.getFeedType();
        String title = model.getFeedName();
        String descript = model.getFeedDescrip();
        float rating = Float.parseFloat(model.getFeedRating());
        boolean saved = Boolean.parseBoolean(model.getIsSaved());
        String id = model.getFeedId();
        if (feedtype.equalsIgnoreCase("remedy")){
            holder.display.setVisibility(View.VISIBLE);
            getallchilds(id);
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

    private void getallchilds(final String id) {
        childItem.clear();
        DatabaseReference  firebaseRef = FirebaseDatabase.getInstance().getReference("NewsFeeds").child(id).child("Ings");
        firebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
             if (dataSnapshot.exists()){
                 for (DataSnapshot shots : dataSnapshot.getChildren()){
                     child cmodel = shots.getValue(child.class);
                     String name = cmodel.getChildName();
                     String measure = cmodel.getChildDescrip();
                     child model = new child(name,measure);
                     childItem.add(model);
                 }
                 cAdapter.notifyDataSetChanged();
             }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return feedsItem.size();
    }

    @Override
    public Filter getFilter() {
        return searchFilter;
    }

    private Filter searchFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String charString = constraint.toString();

            if (charString.isEmpty()) {
                scrootedItems = feedsItem;
            } else {

                List<feeds> filteredList = new ArrayList<>();

                for (feeds item : scrootedItems) {

                    if (item.getFeedName().toLowerCase().contains(charString) || item.getFeedType().toLowerCase().contains(charString)) {

                        filteredList.add(item);
                    }
                }

                feedsItem = filteredList;
            }

            FilterResults results = new FilterResults();
            results.values = feedsItem;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults filterResults) {
            feedsItem = (ArrayList<feeds>) filterResults.values;
            notifyDataSetChanged();
        }
    };

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
