package com.oktwohundred.corona.preventcorona.Activities;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.oktwohundred.corona.preventcorona.BaseAtivity;
import com.oktwohundred.corona.preventcorona.R;

import static com.oktwohundred.corona.preventcorona.Helpers.LocaleManager.getLocale;

public class Myths_Realities extends BaseAtivity {

    ImageView backgo;
    TextView backtext;

    @Override
    public void initViews(Bundle savedInstanceState) {
        getLocale(getResources());

        backtext = findViewById(R.id.backtext);
        backgo = findViewById(R.id.backgo);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_myths__realities;
    }


    @Override
    public void setListener() {
        backgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeScreen(Myths_Realities.this,MainActivity.class);
            }
        });
    }

    @Override
    public void setToolbar() {
        backtext.setText("MYTHS VS REALITIES");

    }

    @Override
    public void hideStatusbar() {

    }
}
