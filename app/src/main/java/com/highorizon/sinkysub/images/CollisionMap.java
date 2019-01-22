package com.highorizon.sinkysub.images;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.highorizon.sinkysub.entities.Mob;

public class CollisionMap {

    private Mob parent;
    private Bitmap map;

    public int left, right, top, bottom;

    public CollisionMap(Mob parent, Bitmap map) {
        this.parent = parent;
        this.map = map;

        left = (int) parent.getPos().x;
        right = (int) parent.getPos().x + map.getWidth();
        top = (int) parent.getPos().y;
        bottom = (int) parent.getPos().y + map.getHeight();
    }

    public void update() {
        left = (int) parent.getPos().x;
        right = (int) parent.getPos().x + map.getWidth();
        top = (int) parent.getPos().y;
        bottom = (int) parent.getPos().y + map.getHeight();
    }

    public boolean isFilled(int x, int y) {
        if (map.getPixel(x, y) == Color.TRANSPARENT) return false;
        return true;
    }

    public Bitmap getCollisionMap() {
        return map;
    }

}
