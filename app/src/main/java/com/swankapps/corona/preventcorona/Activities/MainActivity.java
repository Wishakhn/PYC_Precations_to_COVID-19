package com.swankapps.corona.preventcorona.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.swankapps.corona.preventcorona.BaseAtivity;
import com.swankapps.corona.preventcorona.Helpers.CommonMethods;
import com.swankapps.corona.preventcorona.R;

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



    @Override
    public void initViews(Bundle savedInstanceState) {
        navView = findViewById(R.id.navView);
        dLayout = findViewById(R.id.dLayout);
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
        shownavigation.setOnClickListener(openDrawerListenr);
        closedrawer_btn.setOnClickListener(closeDrawerListenr);
        openProfile.setOnClickListener(openProfileListner);
        openProfile.setOnClickListener(openProfileListner);
        openSearch.setOnClickListener(openSearchListner);
        openIntro.setOnClickListener(openIntroListner);
        openSymptoms.setOnClickListener(openSymptomsListner);
        openRemedies.setOnClickListener(openRemediesListner);
        openprecaution.setOnClickListener(openPrecautionListner);

    }

    @Override
    public void setToolbar() {
//Required

    }

    @Override
    public void hideStatusbar() {

    }

    private View.OnClickListener openDrawerListenr = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          openDrawer();
        }
    };

    private void openDrawer() {
        if (!dLayout.isDrawerOpen(navView)){
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
        if (dLayout.isDrawerOpen(navView)){
            dLayout.closeDrawer(navView);
        }
    }


    private View.OnClickListener openProfileListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CommonMethods.intentHandler(MainActivity.this,profileActivity.class);
        }
    };
    private View.OnClickListener openSearchListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CommonMethods.intentHandler(MainActivity.this,SearchActivity.class);
        }
    };
    private View.OnClickListener openIntroListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CommonMethods.intentHandler(MainActivity.this,CovidIntro.class);
        }
    };
    private View.OnClickListener openSymptomsListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CommonMethods.intentHandler(MainActivity.this,SymptomsActivity.class);
        }
    };
    private View.OnClickListener openRemediesListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CommonMethods.intentwithTypeHandler(MainActivity.this,Remidy_PrecatuionActivity.class,"remedy");
        }
    };
    private View.OnClickListener openPrecautionListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            CommonMethods.intentwithTypeHandler(MainActivity.this,Remidy_PrecatuionActivity.class,"precaution");
        }
    };

}
