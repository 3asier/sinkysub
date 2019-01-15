package com.highorizon.sinkysub.entities;

import android.graphics.Bitmap;
import android.graphics.PointF;

import com.highorizon.sinkysub.GameView;

public class Background_Rock extends BackgroundImage {

    public static float speed = 5.0f;

    public static Bitmap image = GameView.rock_0;

    public Background_Rock(PointF pos) {
        super(pos, image, speed);
    }

}
