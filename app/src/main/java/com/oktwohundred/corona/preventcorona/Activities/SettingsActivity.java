package com.oktwohundred.corona.preventcorona.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.oktwohundred.corona.preventcorona.BaseAtivity;
import com.oktwohundred.corona.preventcorona.Fragments.AboutFragment;
import com.oktwohundred.corona.preventcorona.Fragments.HelpFragment;
import com.oktwohundred.corona.preventcorona.Fragments.RegisterFragment;
import com.oktwohundred.corona.preventcorona.Helpers.CommonMethods;
import com.oktwohundred.corona.preventcorona.Helpers.LocaleManager;
import com.oktwohundred.corona.preventcorona.Helpers.preferenceClass;
import com.oktwohundred.corona.preventcorona.R;

public class SettingsActivity extends BaseAtivity {


    ImageView backgo;
    TextView backtext;
    preferenceClass prefs;


    @Override
    public void initViews(Bundle savedInstanceState) {
        prefs = new preferenceClass(SettingsActivity.this);
        prefs.initPreference();
        backtext = findViewById(R.id.backtext);
        backgo = findViewById(R.id.backgo);


    }

    @Override
    public int getLayout() {
        return R.layout.activity_settings;
    }


    @Override
    public void setListener() {
        backgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeScreen(SettingsActivity.this, MainActivity.class);
            }
        });
    }

    @Override
    public void setToolbar() {
        backtext.setText("SETTINGS");

    }

    @Override
    public void hideStatusbar() {

    }

    public void GotoSavedFeeds(View view) {
        CommonMethods.intentwithTypeHandler(SettingsActivity.this, SettingOptions.class, "saved");
    }

    public void GotoLanguage(View view) {
        createDialogforLanguage();
    }

    public void GotoHelp(View view) {
        CommonMethods.intentwithTypeHandler(SettingsActivity.this, SettingOptions.class,"help");
    }

    public void GotoAbout(View view) {
        CommonMethods.intentwithTypeHandler(SettingsActivity.this, SettingOptions.class,"about");

    }



    public void GotoLogout(View view) {

    }


    private void createDialogforLanguage() {
        AlertDialog.Builder chekPer = new AlertDialog.Builder(SettingsActivity.this);
        View v = getLayoutInflater().inflate(R.layout.language_dialog, null);
        chekPer.setView(v);
        final AlertDialog dialog = chekPer.create();
        dialog.show();
        RadioGroup lanGroup = v.findViewById(R.id.lanGroup);
        CardView dontCahnge = v.findViewById(R.id.dontCahnge);
        CardView doChange = v.findViewById(R.id.doChange);
        final RadioButton setEnglish = v.findViewById(R.id.setEnglish);
        final RadioButton setUrdu = v.findViewById(R.id.setUrdu);
        final String[] Languagae = {LocaleManager.ENGLISH};
        final int[] checkedIndex = {0};
        prefs.loadButtonPreferences(SettingsActivity.this, lanGroup);


        setEnglish.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean checked) {
                if (checked) {
                    setUrdu.setChecked(false);
                }
                Languagae[0] = LocaleManager.ENGLISH;
//                setNewLocale(SettingsActivity.this, LocaleManager.ENGLISH);
            }
        });
        setUrdu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    setEnglish.setChecked(false);
                }
                Languagae[0] = LocaleManager.URDU;
//                setNewLocale(SettingsActivity.this, LocaleManager.URDU);

            }
        });
        lanGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                RadioButton checkedRadioButton = (RadioButton) group.findViewById(i);
                checkedIndex[0] = group.indexOfChild(checkedRadioButton);
            }
        });
        dontCahnge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        doChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefs.saveButtonPreferences(prefs.KEY_SAVED_RADIO_BUTTON_INDEX, checkedIndex[0], SettingsActivity.this);
                setNewLocale(SettingsActivity.this, Languagae[0]);
            }
        });


        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void setNewLocale(AppCompatActivity mContext, @LocaleManager.LocaleDef String language) {
        LocaleManager.setNewLocale(this, language);
        Intent intent = mContext.getIntent();
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}
