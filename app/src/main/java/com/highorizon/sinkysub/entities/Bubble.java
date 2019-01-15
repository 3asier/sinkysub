package com.highorizon.sinkysub.entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;

import com.highorizon.sinkysub.GameView;
import com.highorizon.sinkysub.World;

public class Bubble extends Mob {

    private World world;

    public float speed = 20.0f;
    private float sway = 0;

    private int age = 0;
    private int animScale = random.nextInt(4) + 1;

    private Bitmap[] images = GameView.bubbles;

    public Bubble(PointF pos, World world) {
        super(pos, 0, 0);
        this.world = world;
    }

    public void update() {
        if (age >= images.length * animScale - 1) world.remove(this);
        age++;

        yVel -= 2.0f;

        sway += random.nextDouble() * 0.5;
        xVel = (float) Math.sin(sway) * 5  - speed;

        super.move();
    }

    public void render(Canvas canvas) {
        canvas.drawBitmap(Bitmap.createScaledBitmap(images[(int) Math.floor(age / animScale)], 16, 16, false), pos.x, pos.y, null);
    }

}
