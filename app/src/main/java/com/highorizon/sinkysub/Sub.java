package com.highorizon.sinkysub;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;

import com.highorizon.sinkysub.entities.Bubble;
import com.highorizon.sinkysub.entities.Mob;

public class Sub extends Mob {

    private World world;

    private float maxYVel = 20.0f;

    private Bitmap image = GameView.sub_0;
    private Rect hitBox;

    private boolean tap = false;

    public Sub(World world) {
        super(new PointF(world.screenSize.width() / 9, world.screenSize.height() / 3), 0.0f, 0.0f);
        this.world = world;
        hitBox = new Rect();
    }

    @Override
    public void update() {
        if (tap) {
            yVel -= 2.0;
        } else {
            yVel += 2.0;
            if (random.nextInt(2) == 0) world.add(new Bubble(new PointF(pos.x + 80, pos.y + 20), world));
        }

        clampVel();

        super.move();
    }

    private void clampVel() {
        if (yVel > maxYVel) yVel = maxYVel;
        if (yVel < -maxYVel) yVel = -maxYVel;
    }

    public void tapDown() {
        this.tap = true;
    }

    public void tapUp() {
        this.tap = false;
    }

    @Override
    public void render(Canvas canvas) {
        canvas.drawBitmap(image, pos.x, pos.y, null);
    }
}
