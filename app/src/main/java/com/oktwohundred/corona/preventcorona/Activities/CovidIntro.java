package com.oktwohundred.corona.preventcorona.Activities;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.oktwohundred.corona.preventcorona.Adapter.IntroAdapter;
import com.oktwohundred.corona.preventcorona.BaseAtivity;
import com.oktwohundred.corona.preventcorona.Model.blogs;
import com.oktwohundred.corona.preventcorona.R;

import java.util.ArrayList;
import java.util.List;

import static com.oktwohundred.corona.preventcorona.Helpers.Constants.URL_IMF_BLOG;
import static com.oktwohundred.corona.preventcorona.Helpers.Constants.URL_UNICEF_BLOG;
import static com.oktwohundred.corona.preventcorona.Helpers.Constants.URL_WHO_BLOG;
import static com.oktwohundred.corona.preventcorona.Helpers.LocaleManager.getLocale;

public class CovidIntro extends BaseAtivity {
    ImageView backgo;
    TextView backtext;
    FrameLayout introcontainer;
    IntroAdapter iAdapter;
    List<blogs> blogItems;
    RecyclerView introCycler;


    @Override
    public void initViews(Bundle savedInstanceState) {
        getLocale(getResources());
        backgo = findViewById(R.id.backgo);
        backtext = findViewById(R.id.backtext);
        introcontainer = findViewById(R.id.introcontainer);
        introCycler = findViewById(R.id.introCycler);
        introCycler.setLayoutManager(new LinearLayoutManager(CovidIntro.this, LinearLayoutManager.VERTICAL, false));
        blogItems = new ArrayList<>();
        iAdapter = new IntroAdapter(CovidIntro.this,introcontainer,blogItems);
        loadAllblogs();

    }

    @Override
    public int getLayout() {
        return R.layout.activity_covid_intro;
    }


    @Override
    public void setListener() {
        backgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeScreen(CovidIntro.this,MainActivity.class);
            }
        });
    }

    private void loadAllblogs() {
        blogItems.clear();
        blogItems.add(new blogs(R.drawable.unicef,"Blog by:","UNICEF for Every Child.","(COVID-19) WHAT PARENTS SHOULD KNOW",getResources().getString(R.string.unicef_descrip),URL_UNICEF_BLOG));
        blogItems.add(new blogs(R.drawable.who,"Blog by:","World Health Organization","WHAT IS CORONAVIRUS COVID-19?",getResources().getString(R.string.who_descrip),URL_WHO_BLOG));
        blogItems.add(new blogs(R.drawable.imfimageis,"IMFBlog by:","Jihad Azour","THE IMPACT OF COVID-19",getResources().getString(R.string.imf_descrip),URL_IMF_BLOG));

        introCycler.setAdapter(iAdapter);
        iAdapter.notifyDataSetChanged();
    }

    @Override
    public void setToolbar() {
        backtext.setText("COVID-19");

    }

    @Override
    public void hideStatusbar() {
//Required
    }
}
