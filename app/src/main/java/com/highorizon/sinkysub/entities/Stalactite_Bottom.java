package com.highorizon.sinkysub.entities;

import android.graphics.PointF;

import com.highorizon.sinkysub.World;
import com.highorizon.sinkysub.images.CollisionMap;
import com.highorizon.sinkysub.images.Images;

public class Stalactite_Bottom extends Stalactite {

    public Stalactite_Bottom(World world) {
        super(world);

        image = Images.stalBot;
        collisionMap = new CollisionMap(this, Images.stalBot_collisionMap);

        float range = (world.screenSize.height() - image.getHeight()) / 2.0f;

        pos.y = world.screenSize.height() - random.nextInt((int) Cave_Bottom.size.y) - image.getHeight();
    }

}
