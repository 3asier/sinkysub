package com.highorizon.sinkysub.entities;

import android.graphics.Bitmap;
import android.graphics.PointF;

import com.highorizon.sinkysub.BackgroundManager;
import com.highorizon.sinkysub.GameView;

public class Background_Top extends BackgroundImage{

    public static Bitmap image = GameView.tops[0];

    public Background_Top(PointF pos, BackgroundManager backgroundManager) {
        super(pos, GameView.tops[0], backgroundManager.world.player.getSpeed());
        image = GameView.tops[random.nextInt(GameView.tops.length - 1)];
        super.image= GameView.tops[random.nextInt(GameView.tops.length - 1)];
    }

}
