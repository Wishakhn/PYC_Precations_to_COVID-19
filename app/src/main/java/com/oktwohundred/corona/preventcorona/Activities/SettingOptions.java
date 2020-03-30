package com.oktwohundred.corona.preventcorona.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.oktwohundred.corona.preventcorona.BaseAtivity;
import com.oktwohundred.corona.preventcorona.Fragments.AboutFragment;
import com.oktwohundred.corona.preventcorona.Fragments.HelpFragment;
import com.oktwohundred.corona.preventcorona.Fragments.SaveFeedFragment;
import com.oktwohundred.corona.preventcorona.R;

import static com.oktwohundred.corona.preventcorona.Helpers.Constants.FRAG_TYPE_PARAM;
import static com.oktwohundred.corona.preventcorona.Helpers.LocaleManager.getLocale;

public class SettingOptions extends BaseAtivity {

    ImageView backgo;
    TextView backtext;
    FrameLayout container_settFragment;
    FragmentManager manager;
    String type;
    @Override
    public void initViews(Bundle savedInstanceState) {
        getLocale(getResources());
        backtext = findViewById(R.id.backtext);
        backgo = findViewById(R.id.backgo);
        container_settFragment = findViewById(R.id.container_settFragment);
        manager = getSupportFragmentManager();
        Intent gtent = getIntent();
        if (gtent != null) {
        type = gtent.getStringExtra(FRAG_TYPE_PARAM);
        if (type.equalsIgnoreCase("help")){
            callHelpFragment();
        }
        else if (type.equalsIgnoreCase("saved")){
            callSaveFeedFragment();
        }
        else {
            callAboutFragment();
        }
        }

    }

    @Override
    public int getLayout() {
        return R.layout.activity_setting_options;
    }

    @Override
    public void setListener() {
        backgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeScreen(SettingOptions.this, SettingsActivity.class);
            }
        });
    }

    @Override
    public void setToolbar() {
        if (type.equalsIgnoreCase("help")){
            backtext.setText("HELP");
        }
        else if (type.equalsIgnoreCase("saved")){
            backtext.setText("SAVED FEEDS");
        }
        else {
            backtext.setText("ABOUT");

        }

    }

    @Override
    public void hideStatusbar() {

    }

    void  callHelpFragment(){
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        HelpFragment frag1 = new HelpFragment();
        fragmentTransaction.replace(container_settFragment.getId(), frag1, "Help  Fragment");
        fragmentTransaction.commit();
    }
    void  callAboutFragment(){
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        AboutFragment frag1 = new AboutFragment();
        fragmentTransaction.replace(container_settFragment.getId(), frag1, "About  Fragment");
        fragmentTransaction.commit();
    }
    private void callSaveFeedFragment() {
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        SaveFeedFragment frag1 = new SaveFeedFragment(SettingOptions.this);
        fragmentTransaction.replace(container_settFragment.getId(), frag1, "Save Feed Fragment");
        fragmentTransaction.commit();
    }
}
