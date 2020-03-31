package com.oktwohundred.corona.preventcorona.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.oktwohundred.corona.preventcorona.BaseAtivity;
import com.oktwohundred.corona.preventcorona.ExpenssionLayout.ExpansionLayout;
import com.oktwohundred.corona.preventcorona.LocalDatabase.DbManager;
import com.oktwohundred.corona.preventcorona.R;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.oktwohundred.corona.preventcorona.Helpers.LocaleManager.getLocale;

public class profileActivity extends BaseAtivity {

    ImageView backgo;
    TextView backtext;


    CircleImageView uploadimage_prof;
    CardView btn_prof;

    EditText prof_nameedit;

    EditText prof_mailedit;
    ImageView iconeditmail;



    EditText genderedit;
    ImageView indecator_genderP;
    ExpansionLayout gender_listP;
    TextView femaleP;
    TextView maleP;
    TextView noneeP;
    TextView prof_dobedit;


    EditText countryedit;
    ImageView indecator_timeP;
    ExpansionLayout timezoneP;
    RecyclerView timezonecylcer;

    EditText stageedit;
    ImageView indecator_stage;
    ExpansionLayout user_liststage;
    TextView stage1;
    TextView stage2;
    TextView stage3;
    TextView stage4;
    TextView stage5;


    String profImage = "";
    String gender = "";
    String stage = "";
    String name ="";
    String country ="";
    String email = "";
    String dob = "";

    @Override
    public void initViews(Bundle savedInstanceState) {
        getLocale(getResources());
        DbManager database = new DbManager(profileActivity.this);
        profImage = ""+database.getUserData().getUserImage();
        gender = ""+ database.getUserData().getUserGender();
        stage = ""+database.getUserData().getUserStage();
        name = ""+database.getUserData().getUserName();
        country = ""+database.getUserData().getUserCountry();
        email = ""+database.getUserData().getUserMail();
        dob =""+database.getUserData().getUserDob();


        backtext = findViewById(R.id.backtext);
        backgo = findViewById(R.id.backgo);

         prof_nameedit= findViewById(R.id.prof_nameedit);
        prof_dobedit= findViewById(R.id.prof_dobedit);
         prof_mailedit= findViewById(R.id.prof_mailedit);
         iconeditmail= findViewById(R.id.iconeditmail);

        genderedit= findViewById(R.id.genderedit);
        indecator_genderP= findViewById(R.id.indecator_genderP);
        gender_listP= findViewById(R.id.gender_listP);
        femaleP= findViewById(R.id.femaleP);
        maleP= findViewById(R.id.maleP);
        noneeP= findViewById(R.id.noneeP);

        countryedit= findViewById(R.id.countryedit);
        indecator_timeP= findViewById(R.id.indecator_timeP);
        timezoneP= findViewById(R.id.timezoneP);
        timezonecylcer= findViewById(R.id.timezonecylcer);

        stageedit= findViewById(R.id.stageedit);
        indecator_stage= findViewById(R.id.indecator_stage);
        user_liststage= findViewById(R.id.user_liststage);
        stage1= findViewById(R.id.stage1);
        stage2= findViewById(R.id.stage2);
        stage3= findViewById(R.id.stage3);
        stage4= findViewById(R.id.stage4);
        stage5= findViewById(R.id.stage5);

//        if (profImage.isEmpty() || profImage.equalsIgnoreCase("default")){
//            Glide.with(profileActivity.this).load(R.drawable.profile_image).into(uploadimage_prof);
//        }
//        else {
//            Glide.with(profileActivity.this).load(profImage).into(uploadimage_prof);
//
//        }
        indecator_stage.setVisibility(View.INVISIBLE);
        indecator_timeP.setVisibility(View.INVISIBLE);
        indecator_genderP.setVisibility(View.INVISIBLE);
        genderedit.setText(gender);
        countryedit.setText(country);
        stageedit.setText(stage);
        prof_mailedit.setText(email);
        prof_nameedit.setText(name);
        prof_dobedit.setText(dob);


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
