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

import org.w3c.dom.Text;


public class RegisterFragment extends Fragment {


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rv = inflater.inflate(R.layout.fragment_register, container, false);
        ImageButton btn_reg = rv.findViewById(R.id.btn_reg);
        TextView logintext = rv.findViewById(R.id.logintext);
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonMethods.intentHandler(getContext(), MainActivity.class);
            }
        });
        logintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callLoginFragment();
            }
        });
        underline(logintext);
        return rv;
    }

    void callLoginFragment() {
        LoginFragment fragment2 = new LoginFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_authfragment, fragment2);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        TextView textLabel = getActivity().findViewById(R.id.labelfragment);
        textLabel.setText("Login");
    }

    void underline(TextView text) {
        String content1 = text.getText().toString().trim();
        SpannableString spannableString1 = new SpannableString(content1);
        spannableString1.setSpan(new UnderlineSpan(), 25, content1.length(), 0);
        text.setText(spannableString1);
    }

    ;
}
