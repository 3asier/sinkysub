package com.highorizon.sinkysub.entities;

import android.graphics.Bitmap;
import android.graphics.PointF;

import com.highorizon.sinkysub.BackgroundManager;
import com.highorizon.sinkysub.images.Images;

public class Background_Bottom extends BackgroundImage{

    public static Bitmap image = Images.bottoms[0];

    public Background_Bottom(PointF pos, BackgroundManager backgroundManager) {
        super(pos, Images.bottoms[0], backgroundManager.world.player.getSpeed());
        image = Images.bottoms[random.nextInt(Images.bottoms.length)];
        super.image = image;
    }

}
