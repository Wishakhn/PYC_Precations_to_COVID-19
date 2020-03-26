package com.swankapps.corona.preventcorona.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.swankapps.corona.preventcorona.BaseAtivity;
import com.swankapps.corona.preventcorona.Helpers.CommonMethods;
import com.swankapps.corona.preventcorona.R;

import static com.swankapps.corona.preventcorona.Helpers.Constants.FRAG_TYPE_PARAM;

public class AuthOptions extends BaseAtivity {

    @Override
    public void initViews(Bundle savedInstanceState) {
//Required
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
