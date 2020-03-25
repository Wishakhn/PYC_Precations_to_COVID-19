package com.swankapps.corona.preventcorona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen extends AppCompatActivity {
Handler handler;
preferenceClass preference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        hide_statusbar();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        handler = new Handler();
        preference = new preferenceClass(SplashScreen.this);
        preference.initPreference();
        startSplashing();
    }

    private void startSplashing() {
      handler.postDelayed(new Runnable() {
          @Override
          public void run() {
              if (!preference.isFirstRun()){
                  intentHandler(IntroSlider.class);
              }
              else{
                  intentHandler(MainActivity.class);
              }
          }
      },2500);
    }

    private void intentHandler(Class target) {
        Intent move = new Intent(SplashScreen.this, target);
        startActivity(move);
        finish();
    }
    void hide_statusbar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
