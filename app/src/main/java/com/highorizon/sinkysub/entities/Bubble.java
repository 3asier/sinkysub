package com.highorizon.sinkysub.entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;

import com.highorizon.sinkysub.images.Images;
import com.highorizon.sinkysub.World;

public class Bubble extends Mob {

    private World world;

    public float speed;
    private float sway = 0;

    private int age = 0;
    private int animScale = random.nextInt(4) + 100;

    private Bitmap[] images = Images.bubbles;

    public Bubble(PointF pos, World world) {
        super(pos, 0, 0);
        this.world = world;
    }

    public void update(long dt) {
        speed = world.player.getSpeed();
        age += (int) (dt / 1000000);
        if (age >= images.length * animScale - 1) world.remove(this);
        if (pos.x < -images[0].getWidth() || pos.y < -images[0].getHeight()) world.remove(this);

        yVel -= 0.01f;

        sway += random.nextDouble() * 0.5;
        xVel = (float) Math.sin(sway) * 0.2f  - speed;

        super.move(dt);
    }

    public void render(Canvas canvas) {
        canvas.drawBitmap(Bitmap.createScaledBitmap(images[(int) (Math.floor(age / animScale) % images.length)], 16, 16, false), pos.x, pos.y, null);
    }

}
