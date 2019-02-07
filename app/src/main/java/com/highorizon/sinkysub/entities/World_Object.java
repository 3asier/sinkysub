package com.highorizon.sinkysub.entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.media.Image;

import com.highorizon.sinkysub.World;

/**
 * For all objects that are physically in the world i.e have a location / collision / need to move with the player.
 */

public abstract class World_Object extends Mob {

    protected World world;

    protected Bitmap image;

    public World_Object(PointF pos, World world) {
        super(pos, -world.player.getSpeed(), 0.0f);

        this.world = world;
    }

    @Override
    public void update(long dt) {
        xVel = -world.player.getSpeed();
        super.move(dt);
    }

    public void onCollision(Mob mob) {
        world.remove(mob);
    }

    @Override
    public void render(Canvas canvas) {
        canvas.drawBitmap(Bitmap.createScaledBitmap(image, image.getWidth(), image.getHeight(), false), pos.x, pos.y, null);
    }

    public Bitmap getImage() {
        return image;
    }

}
