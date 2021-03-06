package com.anwesome.ui.leangallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by anweshmishra on 19/04/17.
 */
public class LeanGalleryLayout extends ViewGroup {
    private int w,h;
    private ContainerLayout containerLayout;
    private GestureDetector gestureDetector;
    public LeanGalleryLayout(Context context,ContainerLayout containerLayout) {
        super(context);
        gestureDetector = new GestureDetector(context,new TapGestureListener());
        initDimension(context);
        this.containerLayout = containerLayout;
    }
    public void initDimension(Context context) {
        Point size = DimensionUtil.getDimension(context);
        w = size.x;
        h = size.y;
    }
    public void onMeasure(int wspec,int hspec) {
        int wNew = Math.min(w,h)/32;
        for(int i=0;i<getChildCount();i++) {
            View child = getChildAt(i);
            measureChild(child,wspec,hspec);
            wNew += child.getMeasuredWidth()+Math.min(w,h)/32;
        }
        setMeasuredDimension(wNew,h);
    }
    public void addImage(Bitmap bitmap) {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageBitmap(bitmap);
        addView(imageView,new LayoutParams(w/4,h/4));
        requestLayout();
    }
    public void onLayout(boolean reloaded,int a,int b,int w,int h) {
        int x = Math.min(w,h)/32;
        for(int i=0;i<getChildCount();i++) {
            View child = getChildAt(i);
            child.layout(x,h/30,x+child.getMeasuredWidth(),h/30+child.getMeasuredHeight());
            x+=child.getMeasuredHeight()+Math.min(w,h)/32;
        }
    }
    private boolean handleTap(View view,float x,float y) {
        return x>=view.getX() && x<=view.getX()+view.getMeasuredWidth() && y>=view.getY() && y<=view.getY()+view.getMeasuredHeight();
    }
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
    private class TapGestureListener extends GestureDetector.SimpleOnGestureListener {
        public boolean onDown(MotionEvent event) {
            return true;
        }
        public boolean onSingleTapUp(MotionEvent event) {
            for(int i=0;i<getChildCount();i++) {
                View child = getChildAt(i);
                boolean condition = handleTap(child,event.getX(),event.getY());
                if(condition && child instanceof ImageView) {
                    ImageView imageView = (ImageView)child;
                    BitmapDrawable bitmapDrawable = (BitmapDrawable)imageView.getDrawable();
                    if(bitmapDrawable!=null) {
                        Bitmap bitmap = bitmapDrawable.getBitmap();
                        if(bitmap!=null) {
                            containerLayout.changeMainImageContainerBitmap(bitmap);
                        }
                    }
                }
            }
            return true;
        }
    }
}
