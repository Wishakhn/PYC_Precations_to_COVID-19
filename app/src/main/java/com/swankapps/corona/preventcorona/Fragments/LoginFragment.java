package com.swankapps.corona.preventcorona.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.swankapps.corona.preventcorona.Activities.MainActivity;
import com.swankapps.corona.preventcorona.Helpers.CommonMethods;
import com.swankapps.corona.preventcorona.R;

public class LoginFragment extends Fragment {



ImageView log_mailicon;
    ImageView log_passicon;
    View log_passview;
    View log_mailview;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View lv = inflater.inflate(R.layout.fragment_login, container, false);
        TextView registerFragment = lv.findViewById(R.id.registerFragment);
        EditText logmailedit = lv.findViewById(R.id.logmailedit);
        EditText log_passedit = lv.findViewById(R.id.log_passedit);
        log_mailicon = lv.findViewById(R.id.log_mailicon);
        log_passicon = lv.findViewById(R.id.log_passicon);
        log_passview = lv.findViewById(R.id.log_passview);
        log_mailview = lv.findViewById(R.id.log_mailview);
        underline(registerFragment);

        logmailedit.addTextChangedListener(mailListener);
        log_passedit.addTextChangedListener(passListener);
        log_passedit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE){
                    CommonMethods.unselectLayout(getContext(),log_passicon,log_passview,R.drawable.lock);
                }
                return false;
            }
        });
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
    private TextWatcher mailListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            CommonMethods.selectLayout(getContext(),log_mailicon,log_mailview,R.drawable.mail_selected);
            CommonMethods.unselectLayout(getContext(),log_passicon,log_passview,R.drawable.lock);
        }
    };
    private TextWatcher passListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            CommonMethods.selectLayout(getContext(),log_passicon,log_passview,R.drawable.lock_selected);
            CommonMethods.unselectLayout(getContext(),log_mailicon,log_mailview,R.drawable.mail);
        }
    };

    public LoginFragment() {
        // Required empty public constructor
    }
    void callRegisterFragment(){
        RegisterFragment fragment2 = new RegisterFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction =        fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container_authfragment, fragment2);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        TextView textLabel = getActivity().findViewById(R.id.labelfragment);
        textLabel.setText("Register");
    }

    void  underline(TextView text){
        String content1 = text.getText().toString().trim();
        SpannableString spannableString1 = new SpannableString(content1);
        spannableString1.setSpan(new UnderlineSpan(), 23, content1.length(), 0);
        text.setText(spannableString1);
    };
}
