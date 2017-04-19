package com.anwesome.ui.leangallery;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by anweshmishra on 19/04/17.
 */
public class LeanGallery {
    private boolean isShown = false;
    private ContainerLayout containerLayout;
    private Activity activity;
    public LeanGallery(Activity activity) {
        this.activity = activity;
        this.containerLayout = new ContainerLayout(activity);
    }
    public void addImage(Bitmap bitmap) {
        if(!isShown) {
            containerLayout.addImage(bitmap);
        }
    }
    public void show() {
        if(!isShown) {
            if(activity instanceof AppCompatActivity) {
               ActionBar actionBar = ((AppCompatActivity)activity).getSupportActionBar();
                if(actionBar!=null) {
                    actionBar.hide();
                }
            }
            else {
                android.app.ActionBar actionBar = activity.getActionBar();
                actionBar.hide();
            }
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            containerLayout.addViews();
            activity.setContentView(containerLayout);
            isShown = true;
        }
    }
}
