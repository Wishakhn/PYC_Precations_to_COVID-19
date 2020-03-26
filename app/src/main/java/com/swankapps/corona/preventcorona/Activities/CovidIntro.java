package com.swankapps.corona.preventcorona.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.swankapps.corona.preventcorona.BaseAtivity;
import com.swankapps.corona.preventcorona.R;

public class CovidIntro extends BaseAtivity {
    ImageView backgo;
    TextView backtext;


    @Override
    public void initViews(Bundle savedInstanceState) {
        backgo = findViewById(R.id.backgo);
        backtext = findViewById(R.id.backtext);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_covid_intro;
    }

    @Override
    public void setListener() {

    }

    @Override
    public void setToolbar() {

    }

    @Override
    public void hideStatusbar() {

    }
}
