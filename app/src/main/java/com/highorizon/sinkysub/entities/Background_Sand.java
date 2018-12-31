package com.highorizon.sinkysub.entities;

import android.graphics.Bitmap;
import android.graphics.Point;

import com.highorizon.sinkysub.BackgroundManager;

public class Background_Sand extends BackgroundImage {

    public static float speed = 0.5f;

    public static Bitmap sand_0;

    public Background_Sand(Point pos) {
        super(pos, sand_0, speed);
    }

}
