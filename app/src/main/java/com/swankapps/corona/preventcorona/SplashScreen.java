package com.swankapps.corona.preventcorona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.swankapps.corona.preventcorona.Activities.AuthOptions;
import com.swankapps.corona.preventcorona.Activities.IntroSlider;
import com.swankapps.corona.preventcorona.Activities.MainActivity;
import com.swankapps.corona.preventcorona.Helpers.CommonMethods;
import com.swankapps.corona.preventcorona.Helpers.preferenceClass;

public class SplashScreen extends BaseAtivity {
Handler handler;
preferenceClass preference;

    @Override
    public void initViews(Bundle savedInstanceState) {
        handler = new Handler();
        preference = new preferenceClass(SplashScreen.this);
        preference.initPreference();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_splash_screen;
    }

    @Override
    public void setListener() {
        startSplashing();
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

    private void startSplashing() {
      handler.postDelayed(new Runnable() {
          @Override
          public void run() {
              if (!preference.isFirstRun()){
                  CommonMethods.intentHandler(SplashScreen.this,IntroSlider.class);
                  finish();
              }
              else{
                  CommonMethods.intentHandler(SplashScreen.this, AuthOptions.class);
                  finish();
              }
          }
      },2500);
    }



}
