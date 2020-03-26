package com.swankapps.corona.preventcorona.Helpers;

import android.content.Context;
import android.content.Intent;

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

}
