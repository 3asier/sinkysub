package com.highorizon.sinkysub.entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;

public class BackgroundImage extends Mob {

    public Bitmap image;

    public BackgroundImage(PointF pos, Bitmap image, float speed) {
        super(pos, -speed, 0.0f);
        this.image = image;
    }

    @Override
    public void update() {
        super.move();
    }

    @Override
    public void render(Canvas canvas) {
        canvas.drawBitmap(Bitmap.createScaledBitmap(image, image.getWidth(), image.getHeight(), false), pos.x, pos.y, null);
    }

}
