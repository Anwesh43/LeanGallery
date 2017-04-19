package com.anwesome.ui.leangallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
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
    public LeanGalleryLayout(Context context,ContainerLayout containerLayout) {
        super(context);
        initDimension(context);
        this.containerLayout = containerLayout;
    }
    public void initDimension(Context context) {
        Point size = DimensionUtil.getDimension(context);
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
        requestLayout();
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
                        if(bitmap!=null) {
                            containerLayout.changeMainImageContainerBitmap(bitmap);
                        }
                    }
                }
            }
        }
        return true;
    }
}
