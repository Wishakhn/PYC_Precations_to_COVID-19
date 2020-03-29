package com.oktwohundred.corona.preventcorona;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.oktwohundred.corona.preventcorona.Helpers.LocaleManager;

public class PycApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.setLocale(base));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleManager.setLocale(this);
    }
}
