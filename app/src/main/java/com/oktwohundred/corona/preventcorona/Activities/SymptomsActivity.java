package com.oktwohundred.corona.preventcorona.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.oktwohundred.corona.preventcorona.Adapter.symptomsAdapter;
import com.oktwohundred.corona.preventcorona.BaseAtivity;
import com.oktwohundred.corona.preventcorona.Model.child;
import com.oktwohundred.corona.preventcorona.R;

import java.util.ArrayList;
import java.util.List;

public class SymptomsActivity extends BaseAtivity {

    ImageView backgo;
    TextView backtext;
   RecyclerView sympCycler;
   RecyclerView stageCycler;
    List<child> getsymps;
    List<child> getstages;
    symptomsAdapter sAdapter;
    symptomsAdapter sAdapter1;

    @Override
    public void initViews(Bundle savedInstanceState) {
        backtext = findViewById(R.id.backtext);
        backgo = findViewById(R.id.backgo);
        sympCycler = findViewById(R.id.sympCycler);
        sympCycler.setLayoutManager(new LinearLayoutManager(SymptomsActivity.this, LinearLayoutManager.VERTICAL, false));
        stageCycler = findViewById(R.id.stageCycler);
        stageCycler.setLayoutManager(new LinearLayoutManager(SymptomsActivity.this, LinearLayoutManager.VERTICAL, false));

        getsymps = getallSymptoms();
        getstages = getallStages();
        sAdapter = new symptomsAdapter(getsymps);
        sAdapter1 = new symptomsAdapter(getstages);
        stageCycler.setAdapter(sAdapter1);
        sAdapter1.notifyDataSetChanged();
        sympCycler.setAdapter(sAdapter);
        sAdapter.notifyDataSetChanged();

    }

    private List<child> getallStages() {
        List<child> stages = new ArrayList<>();
        stages.add(new child("Patient Can \n Experience","Macroscopy: pleurisy, pericarditis, lung consolidation and pulmonary oedema"));
        stages.add(new child("Severity Level 1","minor pneumonia: minor serous exudation, minor fibrin exudation"));
        stages.add(new child("Severity Level 2","mild pneumonia: pulmonary oedema, pneumocyte hyperplasia, large atypical pneumocytes, interstitial inflammation with lymphocytic infiltration and multinucleated giant cell formation."));
        stages.add(new child("Severity Level 3","severe pneumonia: diffuse alveolar damage (DAD) with diffuse alveolar exudates. This diffuse DAD is responsible of the acute respiratory distress syndrome (ARDS) and severe hypoxemia observed in this disease."));
        stages.add(new child("Severity Level 4","healing pneumonia: organization of exudates in alveolar cavities, and pulmonary interstitial fibrosis \n plasmocytosis in BAL."));
        stages.add(new child("Sever Most","Liver: microvesicular steatosis"));
        return stages;
    }

    private List<child> getallSymptoms() {
        List<child> symps = new ArrayList<>();
        symps.add(new child("Fever","a low-grade fever that gradually increases in temperature, you feel hot to touch on your chest or back."));
        symps.add(new child("Fatigue", "an overall feeling of tiredness or lack of energy. It isn’t the same as simply feeling drowsy or sleepy."));
        symps.add(new child("Dry Cough"," new continuous cough - this means you’ve started coughing repeatedly."));
        symps.add(new child("Shortness \n of breath", "you find it hard breathing while walking and climbing."));
        symps.add(new child("Runny or \n stuffy nose","your nose can be extremely dry or may countinuous running like in Flu."));
        symps.add(new child("Sneezing","continuous sneezing."));
        symps.add(new child("Sore Throat","scratchiness or irritation of the throat that often worsens when you swallow."));
        symps.add(new child("Chills \n & Headache","can vary from mild to sever in intensity."));
        symps.add(new child("Trouble \n breathing"," Severe breathing trouble is observed in almost all patients in last stage of COVID-19."));
        symps.add(new child("Persistent pain \n or pressure \n in the chest","chest pain or discomfort caused when your heart muscle doesn't get enough oxygen-rich blood."));
        symps.add(new child("New confusion \n or inability \n to arouse"," sudden changes in consciousness or state of arousal, such as feeling drowsy or agitated."));
        symps.add(new child("Bluish lips \n or face","poor oxygen circulation in the blood that causes."));
        return symps;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_symptoms;
    }


    @Override
    public void setListener() {

        backgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeScreen(SymptomsActivity.this,MainActivity.class);
            }
        });
    }

    @Override
    public void setToolbar() {
        backtext.setText("SYMPTOMS & STAGES");

    }

    @Override
    public void hideStatusbar() {

    }


}
