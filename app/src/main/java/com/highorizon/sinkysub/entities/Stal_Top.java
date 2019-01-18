package com.highorizon.sinkysub.entities;

import android.graphics.Bitmap;
import android.graphics.PointF;

import com.highorizon.sinkysub.GameView;
import com.highorizon.sinkysub.World;

public class Stal_Top extends BackgroundImage {

    private Bitmap image;

    public Stal_Top(PointF pos, World world) {
        super(pos, GameView.stalTop, world.player.getSpeed());
    }

}
