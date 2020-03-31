package com.oktwohundred.corona.preventcorona.Activities;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.oktwohundred.corona.preventcorona.Adapter.symptomsAdapter;
import com.oktwohundred.corona.preventcorona.BaseAtivity;
import com.oktwohundred.corona.preventcorona.Model.child;
import com.oktwohundred.corona.preventcorona.R;

import java.util.ArrayList;
import java.util.List;

import static com.oktwohundred.corona.preventcorona.Helpers.LocaleManager.getLocale;

public class Myths_Realities extends BaseAtivity {

    ImageView backgo;
    TextView backtext;
    RecyclerView mythCycler;
    RecyclerView realCycler;
    symptomsAdapter sAdapter;
    symptomsAdapter sAdapter1;
    List<child> getrealities;
    List<child> getmyths;

    @Override
    public void initViews(Bundle savedInstanceState) {
        getLocale(getResources());

        backtext = findViewById(R.id.backtext);
        backgo = findViewById(R.id.backgo);
        realCycler = findViewById(R.id.realCycler);
        realCycler.setLayoutManager(new LinearLayoutManager(Myths_Realities.this, LinearLayoutManager.VERTICAL, false));

        mythCycler = findViewById(R.id.mythCycler);
        mythCycler.setLayoutManager(new LinearLayoutManager(Myths_Realities.this, LinearLayoutManager.VERTICAL, false));

        getrealities = getallRealities();
         getmyths = getallMyths();
        sAdapter = new symptomsAdapter(getmyths);
        sAdapter1 = new symptomsAdapter(getrealities);
        mythCycler.setAdapter(sAdapter);
        sAdapter.notifyDataSetChanged();
        realCycler.setAdapter(sAdapter1);
        sAdapter1.notifyDataSetChanged();

    }

    @Override
    public int getLayout() {
        return R.layout.activity_myths__realities;
    }


    @Override
    public void setListener() {
        backgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeScreen(Myths_Realities.this,MainActivity.class);
            }
        });
    }

    @Override
    public void setToolbar() {
        backtext.setText("MYTHS VS REALITIES");

    }

    @Override
    public void hideStatusbar() {

    }

    private List<child> getallMyths() {
        List<child> myths = new ArrayList<>();
      myths.add(new child("1)","Drinking alcohol kills coronavirus"));
      myths.add(new child("2)","The flu vaccine will protect you against coronavirus"));
      myths.add(new child("3)"," Pets can catch and spread coronavirus"));
      myths.add(new child("4)","Eating and drinking hot things will kill it"));
      myths.add(new child("5)","Children cannot catch COVID-19"));
      myths.add(new child("6)","Only older adults and young people are at risk"));
      myths.add(new child("7)","Everyone with COVID-19 dies"));
      myths.add(new child("8)","Face masks protect against coronavirus"));
      myths.add(new child("9)","You have to be with someone for 10 minutes to catch the virus"));
      myths.add(new child("10)","You can protect yourself by gargling bleach"));
      myths.add(new child("11)","Antibiotics kill coronavirus"));
      myths.add(new child("12)","Parcels from China can spread coronavirus"));
      myths.add(new child("13)","You can catch coronavirus from eating Chinese food at Home"));
      myths.add(new child("14)","You can catch coronavirus from urine and feces"));
      myths.add(new child("15)","The virus will die off when temperatures rise in the spring"));
      myths.add(new child("16)","Flu and pneumonia vaccines protect against COVID-19"));
      myths.add(new child("17)","The outbreak began because people ate bat soup"));
        return myths;
    }
    private List<child> getallRealities() {
        List<child> realities = new ArrayList<>();
      realities.add(new child("1)","Diseases can make anyone sick regardless of their race or ethnicity."));
      realities.add(new child("2)","COVID-19 is not a fatal disease the survivor rate is more then the death rate, the paitent only needs to be careful during the treatment."));
      realities.add(new child("3)","Fear and anxiety about COVID-19 can cause people to avoid or reject others even though they are not at risk for spreading the virus."));
      realities.add(new child("4)","For most people, the immediate risk of becoming seriously ill from the virus that causes COVID-19 is thought to be low."));
      realities.add(new child("5)","Someone who has completed quarantine or has been released from isolation does not pose a risk of infection to other people."));
      realities.add(new child("6)","You can help stop COVID-19 by knowing the signs and symptoms:\n" +
              "Fever\n" +
              "Cough\n" +
              "Shortness of breath"));

        return realities;
    }

}
