package com.highorizon.sinkysub;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;

import com.highorizon.sinkysub.entities.Bubble;
import com.highorizon.sinkysub.entities.Entity;

import java.util.ArrayList;

public class World {

    public Rect screenSize;

    public Sub player;

    private BackgroundManager backgroundManager;

    private ArrayList<Entity> entities = new ArrayList<>();

    private ArrayList<Entity> newEntities = new ArrayList<>();
    private ArrayList<Entity> oldEntities = new ArrayList<>();

    private ArrayList<Bubble> bubbles = new ArrayList<>();

    private ArrayList<Bubble> newBubbles= new ArrayList<>();
    private ArrayList<Bubble> oldBubbles = new ArrayList<>();




    public World(Rect screenSize) {
        this.screenSize = screenSize;

        player = new Sub(this);
        backgroundManager = new BackgroundManager(this);
    }

    public void update() {
        backgroundManager.update();
        for (Bubble b : bubbles) b.update();

        player.update();

        entities.removeAll(oldEntities);
        entities.addAll(newEntities);

        bubbles.removeAll(oldBubbles);
        bubbles.addAll(newBubbles);

        newBubbles.clear();
        oldBubbles.clear();
    }

    public void add(Entity e) {
        newEntities.add(e);
    }

    public void remove(Entity e) {
        oldEntities.add(e);
    }

    public void add(Bubble b) {
        newBubbles.add(b);
    }

    public void remove(Bubble b) {
        oldBubbles.add(b);
    }

    public void render(Canvas canvas) {
        backgroundManager.render(canvas);
        for (Bubble b : bubbles) b.render(canvas);

        player.render(canvas);
    }

    public void tapDown(PointF pointF) {
        player.tapDown();
    }

    public void tapUp(PointF pointF) {
        player.tapUp();
    }

}
