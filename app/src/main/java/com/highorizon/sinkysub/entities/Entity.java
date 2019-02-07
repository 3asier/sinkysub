package com.highorizon.sinkysub.entities;

import android.graphics.Point;
import android.graphics.PointF;

public abstract class Entity {

    protected PointF pos;

    public Entity(PointF pos) {
        this.pos = pos;
    }

    /**
     * Updates the entity taking into account the number of milliseconds that have passed since
     * the last update.
     *
     * @param dt
     */
    public void update(long dt) {

    }

    public void setPos(PointF pos) {
        this.pos = pos;
    }

    public PointF getPos() {
        return pos;
    }

}
