package com.highorizon.sinkysub.entities;

import android.graphics.Bitmap;
import android.graphics.PointF;

import com.highorizon.sinkysub.World;
import com.highorizon.sinkysub.images.CollisionMap;
import com.highorizon.sinkysub.images.Images;

public class Cave_Bottom extends World_Object{

    public static PointF size = new PointF(Images.bottoms[0].getWidth(), Images.bottoms[0].getHeight());

    public Cave_Bottom(PointF pos, World world) {
        super(pos, world);
        int imageNumber = random.nextInt(Images.bottoms.length);
        image = Images.bottoms[imageNumber];
        collisionMap = new CollisionMap(this, Images.bottoms[imageNumber]);
    }

    public void update(long dt) {
        super.update(dt);

        if (pos.x < -image.getWidth()) { // This cave section is fully off the screen.
            world.add(new Cave_Bottom(new PointF(pos.x + world.screenSize.width() + size.x, world.screenSize.height() - image.getHeight()), world));
            world.remove(this);
        }
    }

}
