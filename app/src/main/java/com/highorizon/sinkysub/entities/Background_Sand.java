package com.highorizon.sinkysub.entities;

import android.graphics.Bitmap;
import android.graphics.PointF;

import com.highorizon.sinkysub.images.Images;

public class Background_Sand extends BackgroundImage {

    public static float speed = 10.0f;

    public static Bitmap image = Images.sand_0;

    public Background_Sand(PointF pos) {
        super(pos, image, speed);
    }

}
