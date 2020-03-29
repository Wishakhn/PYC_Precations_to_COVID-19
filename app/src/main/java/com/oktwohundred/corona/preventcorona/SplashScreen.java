package com.oktwohundred.corona.preventcorona;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.oktwohundred.corona.preventcorona.Activities.AuthOptions;
import com.oktwohundred.corona.preventcorona.Activities.IntroSlider;
import com.oktwohundred.corona.preventcorona.Activities.MainActivity;
import com.oktwohundred.corona.preventcorona.Helpers.CommonMethods;
import com.oktwohundred.corona.preventcorona.Helpers.preferenceClass;

import static com.oktwohundred.corona.preventcorona.Helpers.Constants.KEY_USER_IS_ACTIVE;

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
                  if (preference.load_boolean(KEY_USER_IS_ACTIVE)){
                      CommonMethods.intentHandler(SplashScreen.this, MainActivity.class);

                  }
                  else {
                      CommonMethods.intentHandler(SplashScreen.this, AuthOptions.class);

                  }
                  finish();
              }
          }
      },2500);
    }



}
