package com.highorizon.sinkysub.entities;

import android.graphics.Bitmap;
import android.graphics.PointF;

import com.highorizon.sinkysub.BackgroundManager;
import com.highorizon.sinkysub.GameView;

public class Background_Bottom extends BackgroundImage{

    public static Bitmap image = GameView.bottoms[0];

    public Background_Bottom(PointF pos, BackgroundManager backgroundManager) {
        super(pos, GameView.bottoms[0], backgroundManager.world.player.getSpeed());
        image = GameView.bottoms[random.nextInt(GameView.bottoms.length)];
        super.image = image;
    }

}
