package com.swankapps.corona.preventcorona;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public abstract class BaseAtivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusbar();
        setContentView(getLayout());
        initViews(savedInstanceState);
        setToolbar();
        setListener();

    }



    public abstract void initViews(Bundle savedInstanceState);
    public abstract int getLayout();
    public abstract void setListener();
    public abstract void setToolbar();
    public  abstract void hideStatusbar();
    public void closeScreen(Context sender, Class target) {
        ActivityManager mngr = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        assert mngr != null;
        List<ActivityManager.RunningTaskInfo> taskList = mngr.getRunningTasks(10);
        if (taskList.get(0).topActivity != null) {
            if (taskList.get(0).numActivities == 1 &&
                    taskList.get(0).topActivity.getClassName().equals(this.getClass().getName())) {
                Intent backtent = new Intent(sender, target);
                startActivity(backtent);
                finish();
            } else {
                finish();
            }

        }

    }
}
