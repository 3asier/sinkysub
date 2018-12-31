package com.highorizon.sinkysub.entities;

import android.graphics.Point;

public abstract class Entity {

    protected Point pos;

    public Entity(Point pos) {
        this.pos = pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }

    public Point getPos() {
        return pos;
    }

}
