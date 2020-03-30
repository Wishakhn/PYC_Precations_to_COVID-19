package com.oktwohundred.corona.preventcorona.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oktwohundred.corona.preventcorona.Adapter.saveditemAdapter;
import com.oktwohundred.corona.preventcorona.LocalDatabase.DbManager;
import com.oktwohundred.corona.preventcorona.Model.feeds;
import com.oktwohundred.corona.preventcorona.Model.savedfeeds;
import com.oktwohundred.corona.preventcorona.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SaveFeedFragment extends Fragment {

    RecyclerView SavedCycler;
    saveditemAdapter sAdapter;
    List<feeds> saveItems;
    Context context;
    ProgressBar progressloader;
    TextView errormessage;
    public SaveFeedFragment() {
        // Required empty public constructor
    }

    public SaveFeedFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View sv = inflater.inflate(R.layout.fragment_save_feed, container, false);
        errormessage = sv.findViewById(R.id.errormessage);
        progressloader = sv.findViewById(R.id.progressloader);
        SavedCycler = sv.findViewById(R.id.SavedCycler);
        SavedCycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        saveItems = new ArrayList<>();
        sAdapter = new saveditemAdapter(context,saveItems);
        loadAllRemedies();
        return sv ;
    }

    private void loadAllRemedies() {
        saveItems.clear();
        DbManager db = new DbManager(context);
        String id = ""+db.getUserData().getFirebaseId();
        DatabaseReference firebaseRef = FirebaseDatabase.getInstance().getReference("SavedFeeds").child(id);
        firebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressloader.setVisibility(View.GONE);
                if (dataSnapshot.getValue() != null){
                    for (DataSnapshot shots : dataSnapshot.getChildren()){
                        feeds remedies = shots.getValue(feeds.class);

                        String feedtype = remedies.getFeedType();
                        if (feedtype.equalsIgnoreCase("remedy")){
                            String feedid = remedies.getFeedId();
                            String feedname = remedies.getFeedName();
                            String feedintro = remedies.getFeedIntro();
                            String feeddescrip = remedies.getFeedDescrip() ;
                            float feedrating = remedies.getFeedRating();
                            boolean isSaved = remedies.isSaved();
                            feeds model = new feeds(feedid,feedtype,feedname,feedintro,feeddescrip,feedrating,isSaved);
                            saveItems.add(model);
                        }
                    }
                    SavedCycler.setAdapter(sAdapter);
                    sAdapter.notifyDataSetChanged();
                }
                else {
                    errormessage.setText("No Remedies here");
                    errormessage.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }


}
