package com.highorizon.sinkysub.entities;

import android.graphics.Point;

public class Mob extends Entity {

    protected float xVel, yVel;

    public Mob(Point pos, float xVel, float yVel) {
        super(pos);
        this.xVel = xVel;
        this.yVel = yVel;
    }

    protected void move() {
        pos.x += xVel;
        pos.y += yVel;
    }

}
