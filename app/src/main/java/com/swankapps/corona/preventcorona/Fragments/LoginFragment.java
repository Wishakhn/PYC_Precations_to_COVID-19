package com.swankapps.corona.preventcorona.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.swankapps.corona.preventcorona.Activities.MainActivity;
import com.swankapps.corona.preventcorona.Helpers.CommonMethods;
import com.swankapps.corona.preventcorona.R;

public class LoginFragment extends Fragment {

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View lv = inflater.inflate(R.layout.fragment_login, container, false);
        TextView registerFragment = lv.findViewById(R.id.registerFragment);
        underline(registerFragment);
        ImageButton btn_login = lv.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonMethods.intentHandler(getContext(), MainActivity.class);
            }
        });
        registerFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callRegisterFragment();
            }
        });
        return lv;

    }
    void callRegisterFragment(){
        RegisterFragment fragment2 = new RegisterFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction =        fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_authfragment, fragment2);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    void  underline(TextView text){
        String content1 = text.getText().toString().trim();
        SpannableString spannableString1 = new SpannableString(content1);
        spannableString1.setSpan(new UnderlineSpan(), 23, content1.length(), 0);
        text.setText(spannableString1);
    };
}
