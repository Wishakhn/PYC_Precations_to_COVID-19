package com.oktwohundred.corona.preventcorona.Helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ShareActionProvider;

import static android.content.Context.MODE_PRIVATE;
import static com.oktwohundred.corona.preventcorona.Helpers.Constants.KEY_FIRST_RUN;
import static com.oktwohundred.corona.preventcorona.Helpers.Constants.KEY_SAVED_RADIO_BUTTON_INDEX;

public class preferenceClass {

    private Context context;
    private SharedPreferences sharedPrefs;
    private final int PREFS_MODE = Context.MODE_PRIVATE;
    private final String SHARED_PREFS_NAME = "PYC_Preference";

    private SharedPreferences.Editor editor;

    public preferenceClass(Context context) {
        this.context = context;
    }
    public void initPreference(){
        sharedPrefs = context.getSharedPreferences(SHARED_PREFS_NAME,PREFS_MODE);


    }
    public void  save_FirstRun(boolean isFirstRun){
        editor = sharedPrefs.edit();
        editor.putBoolean(KEY_FIRST_RUN,isFirstRun);
        editor.apply();
    }
    public boolean  isFirstRun(){
        boolean firstRun = sharedPrefs.getBoolean(KEY_FIRST_RUN,false);
        return firstRun;
    }

  public   void save_String (String key, String value){
        editor = sharedPrefs.edit();
        editor.putString(key,value);
        editor.apply();
    }
   public String load_String (String Key){
        String savedString = sharedPrefs.getString(Key, "");
        return savedString;
    }

   public void save_boolean (String key, boolean value){
        editor = sharedPrefs.edit();
        editor.putBoolean(key,value);
        editor.apply();
    }
    public boolean load_boolean (String Key){
        boolean savedBoolean = sharedPrefs.getBoolean(Key, false);
        return savedBoolean;
    }


    /**************************    Preferences for Language button    ****************************/
    public  void saveButtonPreferences(String key, int value, Context context) {
       editor = sharedPrefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public  void loadButtonPreferences(Context context, RadioGroup radioGroup) {
        int savedRadioIndex = sharedPrefs.getInt(KEY_SAVED_RADIO_BUTTON_INDEX, 0);
        RadioButton savedCheckedRadioButton = (RadioButton) radioGroup.getChildAt(savedRadioIndex);
        savedCheckedRadioButton.setChecked(true);
    }


}
