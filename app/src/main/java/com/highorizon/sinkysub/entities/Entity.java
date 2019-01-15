package com.highorizon.sinkysub.entities;

import android.graphics.Point;
import android.graphics.PointF;

public abstract class Entity {

    protected PointF pos;

    public Entity(PointF pos) {
        this.pos = pos;
    }

    public void setPos(PointF pos) {
        this.pos = pos;
    }

    public PointF getPos() {
        return pos;
    }

}
