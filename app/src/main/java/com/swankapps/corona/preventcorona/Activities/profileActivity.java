package com.swankapps.corona.preventcorona.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.swankapps.corona.preventcorona.BaseAtivity;
import com.swankapps.corona.preventcorona.ExpenssionLayout.ExpansionLayout;
import com.swankapps.corona.preventcorona.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class profileActivity extends BaseAtivity {

    ImageView backgo;
    TextView backtext;


    CircleImageView uploadimage_prof;
    CardView btn_prof;

    ImageView prof_nameicon;
    EditText prof_nameedit;
    View prof_nameview;

    ImageView prof_mailicon;
    EditText prof_mailedit;
    View prof_mailview;
    ImageView iconeditmail;



    ImageView gendericon;
    EditText genderedit;
    View reg_genderview;
    ImageView indecator_genderP;
    ExpansionLayout gender_listP;
    TextView femaleP;
    TextView maleP;
    TextView noneeP;


    ImageView countryicon;
    EditText countryedit;
    View reg_countryview;
    ImageView indecator_timeP;
    ExpansionLayout timezoneP;
    RecyclerView timezonecylcer;

    ImageView sageicon;
    EditText stageedit;
    View reg_stageview;
    ImageView indecator_stage;
    ExpansionLayout user_liststage;
    TextView stage1;
    TextView stage2;
    TextView stage3;
    TextView stage4;
    TextView stage5;


    String profImage = "some image here";
    String gender = "Other";
    String stage = "stage1";
    @Override
    public void initViews(Bundle savedInstanceState) {
        backtext = findViewById(R.id.backtext);
        backgo = findViewById(R.id.backgo);


        gendericon= findViewById(R.id.gendericon);
        genderedit= findViewById(R.id.genderedit);
        reg_genderview= findViewById(R.id.reg_genderview);
        indecator_genderP= findViewById(R.id.indecator_genderP);
        gender_listP= findViewById(R.id.gender_listP);
        femaleP= findViewById(R.id.femaleP);
        maleP= findViewById(R.id.maleP);
        noneeP= findViewById(R.id.noneeP);

        countryicon= findViewById(R.id.countryicon);
        countryedit= findViewById(R.id.countryedit);
        reg_countryview= findViewById(R.id.reg_countryview);
        indecator_timeP= findViewById(R.id.indecator_timeP);
        timezoneP= findViewById(R.id.timezoneP);
        timezonecylcer= findViewById(R.id.timezonecylcer);

        sageicon= findViewById(R.id.sageicon);
        stageedit= findViewById(R.id.stageedit);
        reg_stageview= findViewById(R.id.reg_stageview);
        indecator_stage= findViewById(R.id.indecator_stage);
        user_liststage= findViewById(R.id.user_liststage);
        stage1= findViewById(R.id.stage1);
        stage2= findViewById(R.id.stage2);
        stage3= findViewById(R.id.stage3);
        stage4= findViewById(R.id.stage4);
        stage5= findViewById(R.id.stage5);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_profile;
    }

    @Override
    public void setListener() {
        backgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeScreen(profileActivity.this,MainActivity.class);
            }
        });

    }

    @Override
    public void setToolbar() {
        backtext.setText("PROFILE");

    }

    @Override
    public void hideStatusbar() {
//Required
    }
}
