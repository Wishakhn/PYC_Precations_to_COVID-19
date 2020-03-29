package com.oktwohundred.corona.preventcorona.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.oktwohundred.corona.preventcorona.Adapter.Adapter;
import com.oktwohundred.corona.preventcorona.BaseAtivity;
import com.oktwohundred.corona.preventcorona.Model.Item;
import com.oktwohundred.corona.preventcorona.R;
import com.oktwohundred.corona.preventcorona.Helpers.preferenceClass;

import java.util.ArrayList;
import java.util.List;

import static com.oktwohundred.corona.preventcorona.Helpers.LocaleManager.getLocale;

public class IntroSlider extends BaseAtivity {
    ViewPager viewPager;
    Adapter adapter;
    List<Item> slidings;
    RadioGroup indicator_group;
    preferenceClass preference;


    @Override
    public void initViews(Bundle savedInstanceState) {
        getLocale(getResources());

        initViews();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_intro_slider;
    }

    @Override
    public void setListener() {
        viewPager.addOnPageChangeListener(viewPagerListener);
    }

    @Override
    public void setToolbar() {
//Required
    }


    @Override
    public void hideStatusbar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    private ViewPager.OnPageChangeListener viewPagerListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (position <= (adapter.getCount() - 1)) {
                indicator_group.check(position + 1);

            } else {
                indicator_group.check(position - 1);
            }

            System.out.println("Viewpager position is " + position);
            indicator_group.setOnCheckedChangeListener(RadioCheckListener);
        }

        private RadioGroup.OnCheckedChangeListener RadioCheckListener = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton btn = group.findViewById(checkedId);
                btn.setChecked(true);
            }
        };

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void initViews() {
        preference = new preferenceClass(IntroSlider.this);
        preference.initPreference();
        viewPager = findViewById(R.id.viewPager);
        indicator_group = findViewById(R.id.indicator_group);
        slidings = getListofSliders();
        System.out.println("The List returning to slidings  " + getListofSliders());
        adapter = new Adapter(slidings, IntroSlider.this);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(150, 0, 125, 0);


    }

    private List<Item> getListofSliders() {
        List<Item> slider = new ArrayList<>();
        slider.add(new Item(0, "image1", R.drawable.slideimage1));
        slider.add(new Item(1, "image2", R.drawable.slideimage2));
        slider.add(new Item(2, "image3", R.drawable.slideimage3));
        System.out.println("The List returning is " + slider);
        return slider;
    }

    public void skipintro(View view) {
        preference.save_FirstRun(true);
        Intent move = new Intent(IntroSlider.this, AuthOptions.class);
        startActivity(move);
        finish();
    }
}
