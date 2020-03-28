package com.swankapps.corona.preventcorona.Helpers;

import android.app.Activity;
import android.content.Context;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import static com.swankapps.corona.preventcorona.Helpers.Constants.REQUEST_PERMISSION_CODE;

public class PermissionHandler {

    /*******************   Check Permissions   *******************/
    public static boolean checkCameraPermissions(Context contxt) {
        int WritePermission = ContextCompat.checkSelfPermission(contxt, "android.permission.READ_EXTERNAL_STORAGE");
        int ReadPermission = ContextCompat.checkSelfPermission(contxt, "android.permission.WRITE_EXTERNAL_STORAGE");
        int CameraPermission = ContextCompat.checkSelfPermission(contxt, "android.permission.CAMERA");
        return WritePermission == 0 && ReadPermission == 0 && CameraPermission == 0;
    }


    /*******************   Request Permissions   *******************/
    public static void requestPermissions(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{
                "android.permission.CAMERA",
                "android.permission.WRITE_EXTERNAL_STORAGE",
                "android.permission.READ_EXTERNAL_STORAGE",
        }, REQUEST_PERMISSION_CODE);
    }


}
