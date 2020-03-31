package com.oktwohundred.corona.preventcorona.Activities;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oktwohundred.corona.preventcorona.Adapter.feedsAdapter;
import com.oktwohundred.corona.preventcorona.Adapter.searchfeedsAdapter;
import com.oktwohundred.corona.preventcorona.BaseAtivity;
import com.oktwohundred.corona.preventcorona.Model.feeds;
import com.oktwohundred.corona.preventcorona.R;

import java.util.ArrayList;
import java.util.List;

import static com.oktwohundred.corona.preventcorona.Helpers.LocaleManager.getLocale;

public class SearchActivity extends BaseAtivity {

    ImageView backgo;
    TextView backtext;
    ImageView searchgo;
    SearchView searchView;
    LinearLayout scontainer;
    RecyclerView searchCycler;
    searchfeedsAdapter fAdapter;
    List<feeds> feedItems;
    ProgressBar progressloader;
    TextView errormessage;


    @Override
    public void initViews(Bundle savedInstanceState) {
        getLocale(getResources());

        scontainer = findViewById(R.id.scontainer);
        backtext = findViewById(R.id.backtext);
        backgo = findViewById(R.id.backgo);
        searchgo = findViewById(R.id.searchgo);
        searchView = findViewById(R.id.searchView);
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        errormessage = findViewById(R.id.errormessage);
        progressloader = findViewById(R.id.progressloader);
        searchCycler = findViewById(R.id.searchCycler);
        searchCycler.setLayoutManager(new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false));
        feedItems = new ArrayList<>();
        fAdapter = new searchfeedsAdapter(SearchActivity.this, feedItems);

    }

    @Override
    public int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void setListener() {
        searchgo.setVisibility(View.VISIBLE);
        backgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeScreen(SearchActivity.this,MainActivity.class);
            }
        });
        loadAllFeeds();
        searchgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scontainer.getVisibility()==View.VISIBLE){
                    scontainer.setVisibility(View.GONE);
                    searchgo.setImageResource(R.drawable.search_white);
                }
                else {
                    scontainer.setVisibility(View.VISIBLE);
                    searchgo.setImageResource(R.drawable.no_white);
                }
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                fAdapter.getFilter().filter(s);
                return false;
            }
        });
        int searchCloseButtonId = searchView.getContext().getResources()
                .getIdentifier("android:id/search_close_btn", null, null);
        int searchEditorId = searchView.getContext()
                .getResources()
                .getIdentifier("android:id/search_src_text", null, null);
        final EditText searchEditor = searchView.findViewById(searchEditorId);
        View closeSearch = searchView.findViewById(searchCloseButtonId);
        closeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchEditor.setText("");
                fAdapter = new searchfeedsAdapter(SearchActivity.this,feedItems);
                fAdapter.notifyDataSetChanged();
                searchCycler.setAdapter(fAdapter);
            }
        });
    }

    @Override
    public void setToolbar() {
        backtext.setText("SEARCH");

    }
    private void loadAllFeeds() {
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
                        String feedid = remedies.getFeedId();
                        String feedname = remedies.getFeedName();
                        String feedintro = remedies.getFeedIntro();
                        String feeddescrip = remedies.getFeedDescrip() ;
                        String feedrating = remedies.getFeedRating();
                        String isSaved = remedies.getIsSaved();
                        feeds model = new feeds(feedid,feedtype,feedname,feedintro,feeddescrip,feedrating,isSaved);
                        feedItems.add(model);
                    }
                    searchCycler.setAdapter(fAdapter);
                    fAdapter.notifyDataSetChanged();
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
    @Override
    public void hideStatusbar() {
//Required
    }
}
