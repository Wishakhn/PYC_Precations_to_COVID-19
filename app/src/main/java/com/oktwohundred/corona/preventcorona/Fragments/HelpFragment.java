package com.oktwohundred.corona.preventcorona.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.oktwohundred.corona.preventcorona.Helpers.CommonMethods;
import com.oktwohundred.corona.preventcorona.R;

public class HelpFragment extends Fragment {
CardView sendbtn;
EditText sendermail;
EditText descripmailEdit;
Context context;

    public HelpFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fv = inflater.inflate(R.layout.fragment_help, container, false);
        sendbtn = fv.findViewById(R.id.sendbtn);
        sendermail = fv.findViewById(R.id.sendermail);
        descripmailEdit = fv.findViewById(R.id.descripmailEdit);
        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendermail.setText("");
                descripmailEdit.setText("");
                CommonMethods.makeAlert(context,"THANKS !!","Your feedback has successfully sent ." );
            }
        });
        return fv;
    }
}
