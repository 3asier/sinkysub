package com.highorizon.sinkysub;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.support.v4.graphics.ColorUtils;

import com.highorizon.sinkysub.entities.Bubble;
import com.highorizon.sinkysub.entities.Mob;

public class Sub extends Mob {

    private World world;

    private float maxYVel = 20.0f;
    private float speed = 10.0f;

    private int animCount = 0;
    private int animScale = random.nextInt(4) + 1;

    private Bitmap[] images = GameView.subs;
    private Rect hitBox;

    private boolean tap = false;

    public Sub(World world) {
        super(new PointF(world.screenSize.width() / 9, world.screenSize.height() / 3), 0.0f, 0.0f);
        this.world = world;
        hitBox = new Rect();
    }

    @Override
    public void update() {
        if (animCount >= images.length * animScale - 1) animCount = 0;
        animCount++;

        if (tap) {
            yVel -= 2.0;
        } else {
            yVel += 2.0;
        }

        if (random.nextInt(2) == 0)
            world.add(new Bubble(new PointF(pos.x, pos.y + images[0].getHeight() / 2 + 16 - (yVel * 1f) + (random.nextInt(30) - 15)), world));

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
        Paint p = new Paint();
        p.setColor(Color.RED);
        canvas.rotate(yVel, pos.x + images[0].getWidth() / 2, pos.y + images[0].getHeight() / 2);
        canvas.drawBitmap(images[(int) Math.floor(animCount / animScale)], pos.x, pos.y, null);
    }

    public float getSpeed() {
        return speed;
    }

    public PointF getSize() {
        return new PointF(images[0].getWidth(), images[0].getHeight());
    }
}
