package com.highorizon.sinkysub.entities;

import android.graphics.PointF;

import com.highorizon.sinkysub.World;

public class Stalactite extends World_Object {

    public static int timer = 0;

    public Stalactite(World world) {
        super(new PointF(world.screenSize.width(), 0), world);
    }

    public void update() {
        super.update();
    }

}
