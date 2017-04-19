package com.anwesome.ui.leangallerydemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anwesome.ui.leangallery.LeanGallery;

public class MainActivity extends AppCompatActivity {
    private int images[] = {R.drawable.back1,R.drawable.back2,R.drawable.back3,R.drawable.back4};
    private Bitmap[] bitmaps = new Bitmap[4];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LeanGallery leanGallery = new LeanGallery(this);
        for(int i=0;i<4;i++) {
            bitmaps[i] = BitmapFactory.decodeResource(getResources(),images[i]);
        }
        for(int i=0;i<images.length*3;i++) {
            leanGallery.addImage(bitmaps[i%4]);
        }
        leanGallery.show();
    }
}
