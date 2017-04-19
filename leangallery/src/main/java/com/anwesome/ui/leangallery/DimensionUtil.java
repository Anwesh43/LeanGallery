package com.anwesome.ui.leangallery;

import android.content.Context;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.view.Display;

/**
 * Created by anweshmishra on 19/04/17.
 */
public class DimensionUtil {
    public static Point getDimension(Context context) {
        Point size = new Point();
        DisplayManager displayManager = (DisplayManager)context.getSystemService(Context.DISPLAY_SERVICE);
        Display display = displayManager.getDisplay(0);
        display.getRealSize(size);
        return size;
    }
}
