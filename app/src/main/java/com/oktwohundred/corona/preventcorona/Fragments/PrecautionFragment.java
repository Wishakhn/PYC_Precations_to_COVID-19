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
import com.oktwohundred.corona.preventcorona.Model.child;
import com.oktwohundred.corona.preventcorona.Model.feeds;
import com.oktwohundred.corona.preventcorona.R;
import com.oktwohundred.corona.preventcorona.Adapter.feedsAdapter;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrecautionFragment extends Fragment {

    RecyclerView precCycler;
    feedsAdapter fadapter;
    List<feeds> feedItems;
    ProgressBar progressloader;
    TextView errormessage;
    public PrecautionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View pv = inflater.inflate(R.layout.fragment_precaution, container, false);
        precCycler = pv.findViewById(R.id.precCycler);
        errormessage = pv.findViewById(R.id.errormessage);
        progressloader = pv.findViewById(R.id.progressloader);
        precCycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        feedItems = new ArrayList<>();
        fadapter = new feedsAdapter(getContext(), feedItems);
        loadAllPrecautions();

        return pv;
    }

    private void loadAllPrecautions() {
        feedItems.clear();
        DatabaseReference firebaseRef = FirebaseDatabase.getInstance().getReference("NewsFeeds");
        firebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressloader.setVisibility(View.GONE);
                if (dataSnapshot.getValue() != null){
                    for (DataSnapshot shots : dataSnapshot.getChildren()){
                        feeds precautions = shots.getValue(feeds.class);

                        String feedtype = precautions.getFeedType();
                        if (feedtype.equalsIgnoreCase("precaution")){
                            String feedid = precautions.getFeedId();
                            String feedname = precautions.getFeedName();
                            String feedintro = precautions.getFeedIntro();
                            String feeddescrip = precautions.getFeedDescrip() ;
                            String feedrating = precautions.getFeedRating();
                            String isSaved = precautions.getIsSaved();
                            List<child> IsChild = precautions.getIngs();
                            feeds model = new feeds(feedid,feedtype,feedname,feedintro,feeddescrip,feedrating,isSaved, IsChild);
                            feedItems.add(model);
                        }
                    }
                    precCycler.setAdapter(fadapter);
                    fadapter.notifyDataSetChanged();
                }
                else {
                    errormessage.setText("No Precaution Found");
                    errormessage.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
