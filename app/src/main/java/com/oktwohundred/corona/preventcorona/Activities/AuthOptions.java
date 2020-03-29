package com.oktwohundred.corona.preventcorona.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.oktwohundred.corona.preventcorona.BaseAtivity;
import com.oktwohundred.corona.preventcorona.Helpers.CommonMethods;
import com.oktwohundred.corona.preventcorona.R;

import static com.oktwohundred.corona.preventcorona.Helpers.Constants.FRAG_TYPE_PARAM;
import static com.oktwohundred.corona.preventcorona.Helpers.LocaleManager.getLocale;

public class AuthOptions extends BaseAtivity {

    @Override
    public void initViews(Bundle savedInstanceState) {
        getLocale(getResources());

    }

    @Override
    public int getLayout() {
        return R.layout.activity_auth_options;
    }

    @Override
    public void setListener() {
//Required
    }

    @Override
    public void setToolbar() {
//Required
    }

    @Override
    public void hideStatusbar() {
        hide_statusbar();
    }


    /*******************   Change Status bar*******************/
    void hide_statusbar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void UserAuthOptionHandler(View view) {
        if (view.getId() == R.id.logbtn){
            CommonMethods.intentwithTypeHandler(AuthOptions.this,UserAuthentication.class,"login");
        }
        else{
            CommonMethods.intentwithTypeHandler(AuthOptions.this,UserAuthentication.class,"register");

        }
    }

}
