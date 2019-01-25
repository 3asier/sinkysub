package com.highorizon.sinkysub.entities;

import android.graphics.Bitmap;
import android.graphics.PointF;

import com.highorizon.sinkysub.World;
import com.highorizon.sinkysub.images.CollisionMap;
import com.highorizon.sinkysub.images.Images;

public class Cave_Top extends World_Object {

    public static PointF size = new PointF(Images.tops[0].getWidth(), Images.tops[0].getHeight());

    public Cave_Top(PointF pos, World world) {
        super(pos, world);
        int imageNumber = random.nextInt(Images.tops.length);
        image = Images.tops[imageNumber];
        collisionMap = new CollisionMap(this, Images.tops[imageNumber]);
    }

    public void update() {
        super.update();

        if (pos.x < -image.getWidth()) { // This cave section is fully off the screen.
            world.add(new Cave_Top(new PointF(pos.x + world.screenSize.width() + size.x, 0), world));
            world.remove(this);
        }
    }

}
