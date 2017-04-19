package com.anwesome.ui.leangallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.display.DisplayManager;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by anweshmishra on 19/04/17.
 */
public class LeanGalleryLayout extends ViewGroup {
    private int w,h;
    public LeanGalleryLayout(Context context) {
        super(context);
        initDimension(context);
    }
    public void initDimension(Context context) {
        DisplayManager displayManager = (DisplayManager)context.getSystemService(Context.DISPLAY_SERVICE);
        Display display = displayManager.getDisplay(0);
        Point size = new Point();
        display.getRealSize(size);
        w = size.x;
        h = size.y;
    }
    public void onMeasure(int wspec,int hspec) {
        int wNew = w/16;
        for(int i=0;i<getChildCount();i++) {
            View child = getChildAt(i);
            measureChild(child,wspec,hspec);
            wNew += child.getMeasuredWidth()+w/16;
        }
        setMeasuredDimension(Math.max(w,wNew),h);
    }
    public void addImage(Bitmap bitmap) {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageBitmap(bitmap);
        addView(imageView,new LayoutParams(w/4,h/4));
    }
    public void onLayout(boolean reloaded,int a,int b,int w,int h) {
        int x = w/16;
        for(int i=0;i<getChildCount();i++) {
            View child = getChildAt(i);
            child.layout(x,h/30,x+child.getMeasuredWidth(),h/30+child.getMeasuredHeight());
            x+=child.getMeasuredHeight()+w/16;
        }
    }
    private boolean handleTap(View view,float x,float y) {
        return x>=view.getX() && x<=view.getX()+view.getMeasuredWidth() && y>=view.getY() && y<=view.getY()+view.getMeasuredHeight();
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            for(int i=0;i<getChildCount();i++) {
                View child = getChildAt(i);
                boolean condition = handleTap(child,event.getX(),event.getY());
                if(condition && child instanceof ImageView) {
                    ImageView imageView = (ImageView)child;
                    BitmapDrawable bitmapDrawable = (BitmapDrawable)imageView.getDrawable();
                    if(bitmapDrawable!=null) {
                        Bitmap bitmap = bitmapDrawable.getBitmap();
                    }
                }
            }
        }
        return true;
    }
}
