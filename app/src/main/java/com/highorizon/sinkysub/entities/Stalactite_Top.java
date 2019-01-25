package com.highorizon.sinkysub.entities;

import android.graphics.Bitmap;
import android.graphics.PointF;

import com.highorizon.sinkysub.images.CollisionMap;
import com.highorizon.sinkysub.images.Images;
import com.highorizon.sinkysub.Sub;
import com.highorizon.sinkysub.World;

public class Stalactite_Top extends Stalactite {

    public Stalactite_Top(World world) {
        super(world);

        image = Images.stalTop;
        collisionMap = new CollisionMap(this, Images.stalTop_collisionMap);

        float range = (world.screenSize.height() - image.getHeight()) / 2.0f;

        pos.y = random.nextInt((int) Cave_Top.size.y);
    }

}
