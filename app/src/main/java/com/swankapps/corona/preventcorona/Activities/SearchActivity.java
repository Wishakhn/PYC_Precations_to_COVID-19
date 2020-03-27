package com.swankapps.corona.preventcorona.Activities;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.swankapps.corona.preventcorona.Adapter.feedsAdapter;
import com.swankapps.corona.preventcorona.BaseAtivity;
import com.swankapps.corona.preventcorona.Model.feeds;
import com.swankapps.corona.preventcorona.R;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseAtivity {

    ImageView backgo;
    TextView backtext;
    ImageView searchgo;
    SearchView searchView;
    LinearLayout scontainer;
    RecyclerView searchCycler;
    feedsAdapter fAdapter;
    List<feeds> feedItems;

    @Override
    public void initViews(Bundle savedInstanceState) {
        scontainer = findViewById(R.id.scontainer);
        backtext = findViewById(R.id.backtext);
        backgo = findViewById(R.id.backgo);
        searchgo = findViewById(R.id.searchgo);
        searchView = findViewById(R.id.searchView);
        searchCycler = findViewById(R.id.searchCycler);
        searchCycler.setLayoutManager(new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false));
        feedItems = new ArrayList<>();
        fAdapter = new feedsAdapter(SearchActivity.this, feedItems);

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
        loadAllRemedies();
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
    }

    @Override
    public void setToolbar() {
        backtext.setText("SEARCH");

    }
    private void loadAllRemedies() {
        feedItems.clear();
        feedItems.add(new feeds("remedy", "Ginger Tea", "Ever had real, fresh ginger tea? It’s soothing and invigorating at the same time. Ginger tea has been consumed for centuries, and yet it has only recently crossed my radar. I love it!", "Ever had real, fresh ginger tea? It’s soothing and invigorating at the same time. Ginger tea has been consumed for centuries, and yet it has only recently crossed my radar. I love it!", 3.5f, false));
        feedItems.add(new feeds("remedy", "Cure is  Tea", "Ever had real, fresh ginger tea? It’s soothing and invigorating at the same time. Ginger tea has been consumed for centuries, and yet it has only recently crossed my radar. I love it!", "Ever had real, fresh ginger tea? It’s soothing and invigorating at the same time. Ginger tea has been consumed for centuries, and yet it has only recently crossed my radar. I love it!", 4f, false));
        feedItems.add(new feeds("precaution", "Stay home if you can.", "", "You can do your part to help your community and the world. Do not get close to other people.\n" +
                "\n" + "This is called “social distancing” or “physical distancing,” and is basically a call to stand far away from other people. Experts believe the coronavirus travels through droplets, so limiting your exposure to other people is a good way to protect yourself.", 3.5f, false));
        feedItems.add(new feeds("remedy", "Hot Tea", "Ever had real, fresh ginger tea? It’s soothing and invigorating at the same time. Ginger tea has been consumed for centuries, and yet it has only recently crossed my radar. I love it!", "Ever had real, fresh ginger tea? It’s soothing and invigorating at the same time. Ginger tea has been consumed for centuries, and yet it has only recently crossed my radar. I love it!", 5.0f, false));

        feedItems.add(new feeds("precaution","Wash your hands. With soap. Then wash them again.","","A refresher: Wet your hands and scrub them with soap, taking care to get between your fingers and under your nails. Wash for at least 20 seconds (or about the time it takes to sing “Happy Birthday” twice), and dry. Make sure you get your thumbs, too. The C.D.C. also recommends you avoid touching your eyes, nose and mouth with unwashed hands (tough one, we know).", 4.4f, false));
        feedItems.add(new feeds("remedy", "Herbal Tea", "Ever had real, fresh ginger tea? It’s soothing and invigorating at the same time. Ginger tea has been consumed for centuries, and yet it has only recently crossed my radar. I love it!", "Ever had real, fresh ginger tea? It’s soothing and invigorating at the same time. Ginger tea has been consumed for centuries, and yet it has only recently crossed my radar. I love it!", 2.5f, false));

        feedItems.add(new feeds("precaution","Don’t stockpile masks.","","Face masks have become a symbol of coronavirus, but stockpiling them might do more harm than good.\n" +
                "\n" +
                "First, they don’t do much to protect you. Most surgical masks are too loose to prevent inhalation of the virus.", 5.0f, false));
        feedItems.add(new feeds("precaution","If surfaces are dirty, clean them","","use detergent or soap and water prior to disinfection. Full information on how to disinfect found here.", 3.5f, false));
        feedItems.add(new feeds("remedy", "Good Tea", "Ever had real, fresh ginger tea? It’s soothing and invigorating at the same time. Ginger tea has been consumed for centuries, and yet it has only recently crossed my radar. I love it!", "Ever had real, fresh ginger tea? It’s soothing and invigorating at the same time. Ginger tea has been consumed for centuries, and yet it has only recently crossed my radar. I love it!", 2f, false));

        searchCycler.setAdapter(fAdapter);
        fAdapter.notifyDataSetChanged();
    }
    @Override
    public void hideStatusbar() {
//Required
    }
}
