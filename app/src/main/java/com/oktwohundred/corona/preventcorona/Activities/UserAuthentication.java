package com.oktwohundred.corona.preventcorona.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.oktwohundred.corona.preventcorona.BaseAtivity;
import com.oktwohundred.corona.preventcorona.Fragments.LoginFragment;
import com.oktwohundred.corona.preventcorona.Fragments.RegisterFragment;
import com.oktwohundred.corona.preventcorona.R;

import static com.oktwohundred.corona.preventcorona.Helpers.Constants.FRAG_TYPE_PARAM;

public class UserAuthentication extends BaseAtivity {

ImageView finishscreen;
TextView labelfragment;
FrameLayout containerauthfragment;
    FragmentManager manager;


    @Override
    public void initViews(Bundle savedInstanceState) {
        manager = getSupportFragmentManager();
        finishscreen = findViewById(R.id.finishscreen);
        labelfragment = findViewById(R.id.labelfragment);
        containerauthfragment = findViewById(R.id.container_authfragment);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_user_authentication;
    }

    @Override
    public void setListener() {
        Intent tent = getIntent();
        if (tent != null){
            String type = tent.getStringExtra(FRAG_TYPE_PARAM);
            if (type.equalsIgnoreCase("login")){
                callLoginFragment();
                labelfragment.setText("Login");
            }
            else {
                callRegisterFragment();
                labelfragment.setText("Register");

            }
        }
        finishscreen.setOnClickListener(finishscreenlistner);
    }

    @Override
    public void setToolbar() {
//Required
    }

    void  callLoginFragment(){
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        LoginFragment frag1 = new LoginFragment();
        fragmentTransaction.replace(containerauthfragment.getId(), frag1, "Login  Fragment");
        fragmentTransaction.commit();
    }

    void  callRegisterFragment(){
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        RegisterFragment frag1 = new RegisterFragment();
        fragmentTransaction.replace(containerauthfragment.getId(), frag1, "Register  Fragment");
        fragmentTransaction.commit();
    }
    @Override
    public void hideStatusbar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    private View.OnClickListener finishscreenlistner= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            closeScreen(UserAuthentication.this,AuthOptions.class);
        }
    };

}
