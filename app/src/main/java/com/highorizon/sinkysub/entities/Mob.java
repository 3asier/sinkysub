package com.highorizon.sinkysub.entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.PointF;

import com.highorizon.sinkysub.images.CollisionMap;

import java.util.Random;

public class Mob extends Entity {

    protected float xVel, yVel;
    protected Random random = new Random();

    protected CollisionMap collisionMap = null;

    public Mob(PointF pos, float xVel, float yVel) {
        super(pos);
        this.xVel = xVel;
        this.yVel = yVel;
    }

    public void update() {

    }

    protected void move() {
        pos.x += xVel;
        pos.y += yVel;
    }

    public boolean collision(Mob mob) {
        // Updating collision maps...
        this.collisionMap.update();
        mob.collisionMap.update();

        if (coarseCollision(mob)) { // Coarse Collision
            if (pixelCollision(mob)) // Pixel Collision
                return true;
        }

        return false;
    }

    protected boolean coarseCollision(Mob mob) {
        // If either of the mobs has no collision map, collision can not occur.
        if (mob.collisionMap == null|| this.collisionMap == null) return false;

        // Else, both mobs have collision maps.
        // Check to see if there's any overlap between the two.
        if (collisionMap.left < mob.collisionMap.right // Left side of 1st is less than Right side of the Other...
        && collisionMap.right > mob.collisionMap.left // AND Right side of 1st is greater than Left side of the Other...
        && collisionMap.top < mob.collisionMap.bottom // Similar for the top and bottom.
        && collisionMap.bottom > mob.collisionMap.top) {
            return true;
        }

        return false;
    }

    protected boolean pixelCollision(Mob mob) {
        // The top-left corner of the collision always lies at the greatest x any y locations...
        Point collisionStartPos = new Point(
                Math.max(collisionMap.left, mob.collisionMap.left),
                Math.max(collisionMap.top, mob.collisionMap.top));
        // The bottom-right corner of the collision always lies at the smallest right/bottom locations...
        Point collisionEndPos = new Point(
                Math.min(collisionMap.right, mob.collisionMap.right),
                Math.min(collisionMap.bottom, mob.collisionMap.bottom));

        /*
            Now that we have a start location in screen coords, we can calculate an offset for each
            of the collision maps, so we can correctly access the location of pixels inside them.
         */
        Point offsetThis = new Point(-collisionMap.left, -collisionMap.top); // The offset is just the top-left position as a negative.
        Point offsetOther = new Point(-mob.collisionMap.left, -mob.collisionMap.top);

        // Now we need to loop through the collision area and check to see if 2 pixels in the same location are filled...
        for (int x = collisionStartPos.x; x < collisionEndPos.x; x++) {
            for (int y = collisionStartPos.y; y < collisionEndPos.y; y++) {
                if (collisionMap.isFilled(x + offsetThis.x, y + offsetThis.y) // This mobs collision map is filled.
                && mob.collisionMap.isFilled(x + offsetOther.x, y + offsetOther.y)) { // And the Other mobs collision map is filled.
                    return true;
                }
            }
        }
        return false;
    }

    public void render(Canvas Canvas) {

    }

    public CollisionMap getCollisionMap() {
        return collisionMap;
    }
}
