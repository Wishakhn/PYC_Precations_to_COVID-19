package com.oktwohundred.corona.preventcorona.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.oktwohundred.corona.preventcorona.BaseAtivity;
import com.oktwohundred.corona.preventcorona.Fragments.LoginFragment;
import com.oktwohundred.corona.preventcorona.Fragments.PrecautionFragment;
import com.oktwohundred.corona.preventcorona.Fragments.RemedyFragment;
import com.oktwohundred.corona.preventcorona.Fragments.SaveFeedFragment;
import com.oktwohundred.corona.preventcorona.R;

import static com.oktwohundred.corona.preventcorona.Helpers.Constants.FRAG_TYPE_PARAM;
import static com.oktwohundred.corona.preventcorona.Helpers.LocaleManager.getLocale;

public class Remidy_PrecatuionActivity extends BaseAtivity {
    FragmentManager manager;
    FrameLayout rpFragmentconatiner;
    ImageView backgo;
    TextView backtext;
    String type ="";
    @Override
    public void initViews(Bundle savedInstanceState) {
        getLocale(getResources());

        backtext = findViewById(R.id.backtext);
        backgo = findViewById(R.id.backgo);
        rpFragmentconatiner = findViewById(R.id.rpFragmentconatiner);
        manager = getSupportFragmentManager();

        Intent tent = getIntent();
        if (tent != null){
             type = tent.getStringExtra(FRAG_TYPE_PARAM);
            if (type.equalsIgnoreCase("remedy")){
                callRemedyFragment();
            }
            else {
                callPrecautionFragment();

            }
        }


    }

    private void callPrecautionFragment() {
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        PrecautionFragment frag1 = new PrecautionFragment();
        fragmentTransaction.replace(rpFragmentconatiner.getId(), frag1, "Precaution  Fragment");
        fragmentTransaction.commit();
    }

    private void callRemedyFragment() {
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        RemedyFragment frag1 = new RemedyFragment();
        fragmentTransaction.replace(rpFragmentconatiner.getId(), frag1, "Remedy  Fragment");
        fragmentTransaction.commit();
    }


    @Override
    public int getLayout() {
        return R.layout.activity_remidy;
    }

    @Override
    public void setListener() {
        backgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeScreen(Remidy_PrecatuionActivity.this,MainActivity.class);
            }
        });
    }

    @Override
    public void setToolbar() {
        if (type.equalsIgnoreCase("remedy")){
            backtext.setText("REMEDIES");
        }

        else {
            backtext.setText("PRECAUTIONS");
        }

    }

    @Override
    public void hideStatusbar() {
        //Required

    }
}
