package com.swankapps.corona.preventcorona.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.swankapps.corona.preventcorona.BaseAtivity;
import com.swankapps.corona.preventcorona.R;

public class SettingsActivity extends BaseAtivity {


    ImageView backgo;
    TextView backtext;

    @Override
    public void initViews(Bundle savedInstanceState) {
        backtext = findViewById(R.id.backtext);
        backgo = findViewById(R.id.backgo);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_settings;
    }


    @Override
    public void setListener() {
        backgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeScreen(SettingsActivity.this,MainActivity.class);
            }
        });
    }

    @Override
    public void setToolbar() {
        backtext.setText("SETTINGS");

    }

    @Override
    public void hideStatusbar() {

    }

    public void GotoSavedFeeds(View view) {
    }

    public void GotoLanguage(View view) {
    }

    public void GotoHelp(View view) {
    }

    public void GotoAbout(View view) {
    }

    public void GotoFeedback(View view) {
    }

    public void GotoLogout(View view) {
    }
}
