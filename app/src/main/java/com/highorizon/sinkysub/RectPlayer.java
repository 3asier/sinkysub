package com.highorizon.sinkysub;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class RectPlayer implements GameObject {

    private Rect rect;
    private int color;

    public RectPlayer(Rect rect, int color) {
        this.rect = rect;
        this.color = color;
    }

    @Override
    public void update() {

    }

    public void update(Point point) {
        //Clockwise from left.
        rect.set(point.x - rect.width() / 2, point.y - rect.height() / 2, point.x + rect.width() / 2, point.y + rect.height() / 2);
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rect, paint);
    }
}
