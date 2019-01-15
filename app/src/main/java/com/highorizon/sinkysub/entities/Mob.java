package com.highorizon.sinkysub.entities;

import android.graphics.Canvas;
import android.graphics.PointF;

import java.util.Random;

public class Mob extends Entity {

    protected float xVel, yVel;
    protected Random random = new Random();

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

    public void render(Canvas Canvas) {

    }

}
