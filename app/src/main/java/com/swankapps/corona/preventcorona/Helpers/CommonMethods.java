package com.swankapps.corona.preventcorona.Helpers;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.snackbar.Snackbar;
import com.swankapps.corona.preventcorona.R;
import com.swankapps.corona.preventcorona.SplashScreen;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

import static com.swankapps.corona.preventcorona.Helpers.Constants.FRAG_TYPE_PARAM;
import static com.swankapps.corona.preventcorona.Helpers.Constants.PYC_LOG;

public class CommonMethods {

    /******************** Intent Handler ***********************/
    public static void intentHandler(Context context, Class target) {
        Intent move = new Intent(context, target);
        context.startActivity(move);

    }


    public static void intentwithTypeHandler(Context context, Class target, String type) {
        Intent move = new Intent(context, target);
        move.putExtra(FRAG_TYPE_PARAM, type);
        context.startActivity(move);

    }

    /******************** Layoutselector ***********************/

    public static void selectLayout(Context context, ImageView icon, View view, int srcImage){
        icon.setImageResource(srcImage);
        view.setBackgroundColor(context.getResources().getColor(R.color.btn_zinc));
    }

    public static void unselectLayout(Context context, ImageView icon, View view, int srcImage){
        icon.setImageResource(srcImage);
        view.setBackgroundColor(context.getResources().getColor(R.color.grey));
    }

/******************** Decode to String ***********************/
    public static String deCode(String base64) {
        byte[] data1 = Base64.decode(base64, Base64.DEFAULT);
        String text = new String(data1, StandardCharsets.UTF_8);
        Log.i(PYC_LOG, "DECODED DATA IS " + text);
        return text;
    }


    public static boolean IsBase64Encoded(String value)
    {
        try {
            byte[] decodedString = Base64.decode(value, Base64.DEFAULT);
            String text = new String(decodedString, StandardCharsets.UTF_8);
            return true;
        } catch (Exception e) {
            return false;
        }
    }



    /**************************    Handle Editor    ****************************/

    public static void startEditing(EditText inputEditText, Context context) {

        inputEditText.requestFocus();
        inputEditText.setFocusableInTouchMode(true);

        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(inputEditText, InputMethodManager.SHOW_FORCED);
    }

    public static void stopEditing(EditText stopEdittext, Context contxt) {
        InputMethodManager inputMethodManager = (InputMethodManager)
                contxt.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(stopEdittext.getWindowToken(), 0);
    }
    /**************************   Bitmap to String    ****************************/
    public static String imageToString(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
        byte[] imageBytes = stream.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }
    /**************************    Alert Dialog    ****************************/
    public static void makeAlert(Context context, String title, String message) {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle);
            builder.setTitle(title);
            builder.setMessage(message);
            builder.setPositiveButton(context.getResources().getString(R.string.close), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();

                }
            });
            builder.show();
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    /**************************   Validate Email   ****************************/
    public  static boolean isValidEmail(CharSequence target) {
        try {
            if (target == null)
                return false;

            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        } catch (Exception e) {
            Log.i(PYC_LOG,"Exception is "+e);
            return false;
        }
    }

    /**************************    Make Snakebar    ****************************/
    public static void makeSnakbar(String message, CoordinatorLayout corLayout) {
        Snackbar snackbar = Snackbar
                .make(corLayout, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    /**************************    Check Internet Connection    ****************************/

    public static boolean isNetworkConnected(Context context) {
        boolean result = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (cm != null) {
                    NetworkCapabilities capabilities = cm.getNetworkCapabilities(cm.getActiveNetwork());
                    if (capabilities != null) {
                        if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                            result = true;
                        } else  {
                            result = false;
                        }
                    }
                }
            } else {
                if (cm != null) {
                    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                    if (activeNetwork != null) {
                        if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI || activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                            result = true;
                        } else {
                            result = false;
                        }
                    }
                }
            }
        } catch (Exception e) {
            Log.i(PYC_LOG, "Connectivity Exception" + e.getMessage());
        }
        return result;
    }



}
