package com.oktwohundred.corona.preventcorona.Activities;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oktwohundred.corona.preventcorona.Adapter.insightAdapter;
import com.oktwohundred.corona.preventcorona.BaseAtivity;
import com.oktwohundred.corona.preventcorona.Helpers.CommonMethods;
import com.oktwohundred.corona.preventcorona.LocalDatabase.DbManager;
import com.oktwohundred.corona.preventcorona.Model.insights;
import com.oktwohundred.corona.preventcorona.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.oktwohundred.corona.preventcorona.Helpers.Constants.PYC_LOG;
import static com.oktwohundred.corona.preventcorona.Helpers.LocaleManager.getLocale;

public class MainActivity extends BaseAtivity {

    NavigationView navView;
    DrawerLayout dLayout;
    ImageView closedrawer_btn;
    LinearLayout openProfile;
    LinearLayout openSearch;
    LinearLayout openIntro;
    LinearLayout openSymptoms;
    LinearLayout openRemedies;
    LinearLayout openprecaution;
    LinearLayout openmr;
    LinearLayout opensettings;
    ImageView shownavigation;
    RecyclerView insCycler;
    insightAdapter iAdapter;
    List<insights> insItems;
    DbManager database;
    TextView usernametitle;
    CircleImageView user_image;
    ProgressBar progressloader;
    TextView errormessage;
    FirebaseAuth auth;
    DatabaseReference firebaseRef;



    @Override
    public void initViews(Bundle savedInstanceState) {
        getLocale(getResources());
        database = new DbManager(MainActivity.this);
        auth = FirebaseAuth.getInstance();
        errormessage = findViewById(R.id.errormessage);
        progressloader = findViewById(R.id.progressloader);
        user_image = findViewById(R.id.user_image);
        insCycler = findViewById(R.id.insCycler);
        insCycler.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
        insItems = new ArrayList<>();
        iAdapter = new insightAdapter(insItems, MainActivity.this);
        navView = findViewById(R.id.navView);
        dLayout = findViewById(R.id.dLayout);
        usernametitle = findViewById(R.id.usernametitle);
        String uname = ""+database.getUserData().getUserName();
        Log.i(PYC_LOG,"User name is "+uname);
        usernametitle.setText(uname);
        String imgBase64 = ""+database.getUserData().getUserImage();
        if (imgBase64.equalsIgnoreCase("default") || imgBase64.isEmpty()){
            Glide.with(MainActivity.this).load(R.drawable.profile_image2).into(user_image);
        }
        else {
            Glide.with(MainActivity.this).load(imgBase64).into(user_image);
        }

        closedrawer_btn = findViewById(R.id.closedrawer_btn);
        openProfile = findViewById(R.id.openProfile);
        openSearch = findViewById(R.id.openSearch);
        openIntro = findViewById(R.id.openIntro);
        openSymptoms = findViewById(R.id.openSymptoms);
        openRemedies = findViewById(R.id.openRemedies);
        openprecaution = findViewById(R.id.openprecaution);
        openmr = findViewById(R.id.openmr);
        opensettings = findViewById(R.id.opensettings);
        shownavigation = findViewById(R.id.shownavigation);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void setListener() {
        loadInsights();
        shownavigation.setOnClickListener(openDrawerListenr);
        closedrawer_btn.setOnClickListener(closeDrawerListenr);
        openProfile.setOnClickListener(openProfileListner);
        openProfile.setOnClickListener(openProfileListner);
        openSearch.setOnClickListener(openSearchListner);
        openIntro.setOnClickListener(openIntroListner);
        openSymptoms.setOnClickListener(openSymptomsListner);
        openRemedies.setOnClickListener(openRemediesListner);
        openprecaution.setOnClickListener(openPrecautionListner);
        opensettings.setOnClickListener(openSettingsListner);
        openmr.setOnClickListener(openOpenmrListner);

    }

    private void loadInsights() {
        insItems.clear();
        firebaseRef = FirebaseDatabase.getInstance().getReference("Insights");
        firebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                progressloader.setVisibility(View.GONE);
               if (dataSnapshot.exists()){
                   for (DataSnapshot shots : dataSnapshot.getChildren()){
                       insights getAllInsights = shots.getValue(insights.class);

                       String type = getAllInsights.getType();
                       String title = getAllInsights.getTitle();
                       String descrip = getAllInsights.getDescrip();
                       String url = getAllInsights.getUrl();
                       insights model = new insights(type,title,descrip,url);
                       insItems.add(model);

                   }
                   insCycler.setAdapter(iAdapter);
                   iAdapter.notifyDataSetChanged();
               }
               else {
                   errormessage.setText("No Insights available");
                   errormessage.setVisibility(View.VISIBLE);
               }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void setToolbar() {
//Required

    }

    @Override
    public void hideStatusbar() {
//Required

    }

    private View.OnClickListener openDrawerListenr = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openDrawer();
        }
    };

    private void openDrawer() {
        if (!dLayout.isDrawerOpen(navView)) {
            dLayout.openDrawer(navView);
        }
    }

    private View.OnClickListener closeDrawerListenr = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            closeDrawer();
        }
    };

    private void closeDrawer() {
        if (dLayout.isDrawerOpen(navView)) {
            dLayout.closeDrawer(navView);
        }
    }


    private View.OnClickListener openProfileListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CommonMethods.intentHandler(MainActivity.this, profileActivity.class);
            closeDrawer();
        }
    };
    private View.OnClickListener openSearchListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CommonMethods.intentHandler(MainActivity.this, SearchActivity.class);
            closeDrawer();
        }
    };
    private View.OnClickListener openIntroListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CommonMethods.intentHandler(MainActivity.this, CovidIntro.class);
            closeDrawer();
        }
    };
    private View.OnClickListener openSymptomsListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CommonMethods.intentHandler(MainActivity.this, SymptomsActivity.class);
            closeDrawer();
        }
    };
    private View.OnClickListener openRemediesListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CommonMethods.intentwithTypeHandler(MainActivity.this, Remidy_PrecatuionActivity.class, "remedy");
            closeDrawer();
        }
    };
    private View.OnClickListener openPrecautionListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CommonMethods.intentwithTypeHandler(MainActivity.this, Remidy_PrecatuionActivity.class, "precaution");
            closeDrawer();
        }
    };
    private View.OnClickListener openSettingsListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CommonMethods.intentHandler(MainActivity.this, SettingsActivity.class);
            closeDrawer();
        }
    };
    private View.OnClickListener openOpenmrListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CommonMethods.intentHandler(MainActivity.this, Myths_Realities.class);
            closeDrawer();
        }
    };

}
