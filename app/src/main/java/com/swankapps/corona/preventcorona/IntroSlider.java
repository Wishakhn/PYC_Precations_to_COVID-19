package com.swankapps.corona.preventcorona;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;


import java.util.ArrayList;
import java.util.List;

public class IntroSlider extends AppCompatActivity {
    ViewPager viewPager;
    Adapter adapter;
    List<Item> slidings;
    RadioGroup indicator_group;
    preferenceClass preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        hide_statusbar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_slider);
        initViews();
    }

    private void initViews() {
        preference = new preferenceClass(IntroSlider.this);
        preference.initPreference();
        viewPager= findViewById(R.id.viewPager);
        indicator_group = findViewById(R.id.indicator_group);
        slidings = getListofSliders();
        System.out.println("The List returning to slidings  "+getListofSliders());
        adapter = new Adapter(slidings, IntroSlider.this);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(150, 0, 125, 0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }
    /*******************   Change Status bar*******************/
    void hide_statusbar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private List<Item> getListofSliders() {
        List<Item> slider = new ArrayList<>();
        slider.add(new Item(0, "image1",  R.drawable.slideimage1));
        slider.add(new Item(1, "image2",  R.drawable.slideimage2));
        slider.add(new Item(2, "image3",  R.drawable.slideimage3));
System.out.println("The List returning is "+slider);
        return slider;
    }

    public void skipintro(View view) {
        Intent move = new Intent(IntroSlider.this, AuthOptions.class);
        startActivity(move);
        preference.save_FirstRun(true);
    }
}
