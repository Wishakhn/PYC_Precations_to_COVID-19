package com.oktwohundred.corona.preventcorona.Fragments;

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
import com.oktwohundred.corona.preventcorona.Model.feeds;
import com.oktwohundred.corona.preventcorona.R;
import com.oktwohundred.corona.preventcorona.Adapter.feedsAdapter;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RemedyFragment extends Fragment {

    List<feeds> feedItems;
    feedsAdapter fadapter;
    RecyclerView remCycler;
    ProgressBar progressloader;
    TextView errormessage;
    public RemedyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rv = inflater.inflate(R.layout.fragment_remedy, container, false);
        remCycler = rv.findViewById(R.id.remCycler);
        errormessage = rv.findViewById(R.id.errormessage);
        progressloader = rv.findViewById(R.id.progressloader);
        remCycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        feedItems = new ArrayList<>();
        fadapter = new feedsAdapter(getContext(), feedItems);
        loadAllRemedies();
        return rv;
    }
    private void loadAllRemedies() {
        feedItems.clear();
        DatabaseReference firebaseRef = FirebaseDatabase.getInstance().getReference("NewsFeeds");
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
                            feedItems.add(model);
                        }
                    }
                    remCycler.setAdapter(fadapter);
                    fadapter.notifyDataSetChanged();
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
