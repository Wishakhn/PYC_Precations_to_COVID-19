package com.swankapps.corona.preventcorona.Helpers;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.swankapps.corona.preventcorona.R;
import com.swankapps.corona.preventcorona.SplashScreen;

import static com.swankapps.corona.preventcorona.Helpers.Constants.FRAG_TYPE_PARAM;

public class CommonMethods {

    //Intent Hanlder
    public static void intentHandler(Context context, Class target) {
        Intent move = new Intent(context, target);
        context.startActivity(move);

    }

    public static void intentwithTypeHandler(Context context, Class target, String type) {
        Intent move = new Intent(context, target);
        move.putExtra(FRAG_TYPE_PARAM, type);
        context.startActivity(move);

    }

    public static void selectLayout(Context context, ImageView icon, View view, int srcImage){
        icon.setImageResource(srcImage);
        view.setBackgroundColor(context.getResources().getColor(R.color.btn_zinc));
    }
    public static void unselectLayout(Context context, ImageView icon, View view, int srcImage){
        icon.setImageResource(srcImage);
        view.setBackgroundColor(context.getResources().getColor(R.color.grey));
    }

}
