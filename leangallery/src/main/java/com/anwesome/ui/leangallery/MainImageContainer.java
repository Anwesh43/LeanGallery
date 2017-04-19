package com.anwesome.ui.leangallery;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by anweshmishra on 19/04/17.
 */
public class MainImageContainer extends ImageView implements Animator.AnimatorListener,ValueAnimator.AnimatorUpdateListener{
    private int animDir = 0;
    private Bitmap newBitmap;
    private ValueAnimator expandAnim = ValueAnimator.ofFloat(0,1),shrinkAnim = ValueAnimator.ofFloat(1,0);
    public void start(Bitmap bitmap) {
        if(animDir == 0) {
            animDir = -1;
            this.newBitmap = bitmap;
            shrinkAnim.start();
        }
    }
    public MainImageContainer(Context context) {
        super(context);
        expandAnim.setDuration(500);
        shrinkAnim.setDuration(500);
        shrinkAnim.addUpdateListener(this);
        expandAnim.addUpdateListener(this);
        shrinkAnim.addListener(this);
        expandAnim.addListener(this);
    }
    public void onAnimationUpdate(ValueAnimator valueAnimator) {

    }
    public void onAnimationEnd(Animator animator) {
        if(animDir == -1) {
            animDir = 1;
            setImageBitmap(newBitmap);
            expandAnim.start();
        }
        else {
            animDir = 0;
        }
    }
    public void onAnimationCancel(Animator animator) {

    }
    public void onAnimationRepeat(Animator animator) {

    }
    public void onAnimationStart(Animator animator) {

    }
}
