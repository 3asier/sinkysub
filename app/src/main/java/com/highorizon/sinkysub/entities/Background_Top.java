package com.highorizon.sinkysub.entities;

import android.graphics.Bitmap;
import android.graphics.PointF;

import com.highorizon.sinkysub.BackgroundManager;
import com.highorizon.sinkysub.images.Images;

public class Background_Top extends BackgroundImage{

    public static Bitmap image = Images.tops[0];

    public Background_Top(PointF pos, BackgroundManager backgroundManager) {
        super(pos, Images.tops[0], backgroundManager.world.player.getSpeed());
        image = Images.tops[random.nextInt(Images.tops.length)];
        super.image = image;
    }

}
