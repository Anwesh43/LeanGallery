package com.anwesome.ui.leangallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

/**
 * Created by anweshmishra on 19/04/17.
 */
public class ContainerLayout extends ViewGroup {
    private MainImageContainer mainImageContainer;
    private int i =0,w,h;
    private LeanGalleryLayout leanGalleryLayout;
    private HorizontalScrollView horizontalScrollView;
    public ContainerLayout(Context context) {
        super(context);
        mainImageContainer = new MainImageContainer(context);
        leanGalleryLayout = new LeanGalleryLayout(context,this);
        horizontalScrollView = new HorizontalScrollView(context);
        Point size = DimensionUtil.getDimension(context);
        w = size.x;
        h = size.y;
    }
    public void onLayout(boolean reloaded,int a,int b,int w,int h) {
        for(int i=0;i<getChildCount();i++) {
            View child = getChildAt(i);
            if(child instanceof MainImageContainer) {
                child.layout(0,0,w,h);
            }
            else if(child instanceof HorizontalScrollView) {
                child.layout(0,3*h/4,child.getMeasuredWidth(),h);
            }
        }
    }
    public void changeMainImageContainerBitmap(Bitmap bitmap) {
        mainImageContainer.transitionToNewBitmap(Bitmap.createScaledBitmap(bitmap,w,h,true));
    }
    public void addViews() {
        addView(mainImageContainer,new LayoutParams(w,h));
        horizontalScrollView.addView(leanGalleryLayout,new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        addView(horizontalScrollView,new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
    }
    public void addImage(Bitmap bitmap) {
        if(i == 0) {
            mainImageContainer.setImageBitmap(bitmap);
        }
        leanGalleryLayout.addImage(bitmap);
        i++;
    }
    public void onMeasure(int wspec,int hspec) {
        for(int i=0;i<getChildCount();i++) {
            View child = getChildAt(i);
            measureChild(child,wspec,hspec);
        }
        setMeasuredDimension(w,h);
    }
}
