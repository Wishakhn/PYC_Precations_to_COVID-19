package com.oktwohundred.corona.preventcorona.Adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.util.Log;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oktwohundred.corona.preventcorona.Activities.MainActivity;
import com.oktwohundred.corona.preventcorona.Helpers.CommonMethods;
import com.oktwohundred.corona.preventcorona.LocalDatabase.DbManager;
import com.oktwohundred.corona.preventcorona.Model.child;
import com.oktwohundred.corona.preventcorona.Model.feeds;
import com.oktwohundred.corona.preventcorona.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.oktwohundred.corona.preventcorona.Helpers.Constants.KEY_USER_IS_ACTIVE;
import static com.oktwohundred.corona.preventcorona.Helpers.Constants.PYC_LOG;
import static com.oktwohundred.corona.preventcorona.Helpers.Constants.SORRY;


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
        final feeds model = feedsItem.get(position);
        final String feedtype = model.getFeedType();
        final String title = model.getFeedName();
        final String descript = model.getFeedDescrip();
        final float rating = model.getFeedRating();
        boolean saved = model.isSaved();
        final String id = model.getFeedId();
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
        holder.savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFeedstoUser(id,feedtype,title,descript,rating,true, holder.savetext, holder.savebtn);
                saveFeedChild(id);
            }
        });

    }

    private void saveFeedChild(final String id) {
        DatabaseReference  childRef = FirebaseDatabase.getInstance().getReference("NewsFeeds").child(id).child("Ings");
        childRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot shots : dataSnapshot.getChildren()){
                        child cmodel = shots.getValue(child.class);
                        String name = cmodel.getChildName();
                        String measure = cmodel.getChildDescrip();
                        child model = new child(name,measure);
                        populateData(id,model);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }

    private void populateData(String id, child model) {
        DbManager db = new DbManager(context);
        String uid = ""+db.getUserData().getFirebaseId();
        DatabaseReference firebaseRef = FirebaseDatabase.getInstance().getReference("SavedFeeds").child(uid).child(id).child("Ings");
        HashMap<String, String> hashmap = new HashMap<>();
        hashmap.put("childName", model.getChildName());
        hashmap.put("childDescrip", model.getChildDescrip());
        firebaseRef.setValue(hashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                //do nothing

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

    void  saveFeedstoUser(String feedIds, final String type, String name, String descrip, float rating, boolean saved,
                          final TextView savetext, final CardView savebtn){
        DbManager db = new DbManager(context);
        String id = ""+db.getUserData().getFirebaseId();
        DatabaseReference firebaseRef = FirebaseDatabase.getInstance().getReference("SavedFeeds").child(id).child(feedIds);
        HashMap<String, String> hashmap = new HashMap<>();
        hashmap.put("feedId", feedIds);
        hashmap.put("feedType", type);
        hashmap.put("feedName", name);
        hashmap.put("feedIntro", descrip);
        hashmap.put("feedDescrip", descrip);
        hashmap.put("feedRating", ""+rating);
        hashmap.put("isSaved", ""+saved);
        Log.i(PYC_LOG,"Sending Params "+hashmap);
        firebaseRef.setValue(hashmap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    savetext.setText("SAVED");
                    savebtn.setEnabled(false);
                    savebtn.setCardBackgroundColor(context.getResources().getColor(R.color.grey));
                }
                else {
                    CommonMethods.makeAlert(context,SORRY,"Unable to save this "+type);
                }
            }
        });    }

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
