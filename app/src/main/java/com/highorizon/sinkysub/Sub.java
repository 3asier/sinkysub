package com.highorizon.sinkysub;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.highorizon.sinkysub.entities.Mob;

public class Sub extends Mob implements GameObject{

    private float maxYVel = 5.0f;

    private Bitmap image;
    private Rect hitBox;

    private boolean tap;

    public Sub(Point pos, Bitmap image) {
        super(pos, 2.0f, 0.0f);
        this.image = image;
        hitBox = new Rect();
    }

    @Override
    public void update() {
        if (tap) {
            yVel -= 0.4;
        } else {
            yVel += 0.4;
        }

        clampVel();

        super.move();
        System.out.println(yVel);
    }

    private void clampVel() {
        if (yVel > maxYVel) yVel = maxYVel;
        if (yVel < -maxYVel) yVel = -maxYVel;
    }

    public void setTap(boolean tap) {
        this.tap = tap;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, pos.x, pos.y, null);
    }
}
