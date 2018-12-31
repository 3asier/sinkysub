package com.highorizon.sinkysub.entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

import com.highorizon.sinkysub.GameObject;

public class BackgroundImage extends Mob implements GameObject {

    public Bitmap image;

    public BackgroundImage(Point pos, Bitmap image, float speed) {
        super(pos, -2.0f, 0.0f);
        this.image = image;
    }

    @Override
    public void update() {


        super.move();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, pos.x, pos.y, null);
    }

}
