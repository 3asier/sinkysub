package com.highorizon.sinkysub;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;

import com.highorizon.sinkysub.entities.Bubble;
import com.highorizon.sinkysub.entities.Entity;
import com.highorizon.sinkysub.entities.Stal_Top;

import java.util.ArrayList;
import java.util.Random;

public class World {

    public GameView parent;

    private Random random = new Random();

    public Rect screenSize;

    public Sub player;

    private BackgroundManager backgroundManager;

    private ArrayList<Entity> entities = new ArrayList<>();

    private ArrayList<Entity> newEntities = new ArrayList<>();
    private ArrayList<Entity> oldEntities = new ArrayList<>();

    private ArrayList<Bubble> bubbles = new ArrayList<>();

    private ArrayList<Bubble> newBubbles= new ArrayList<>();
    private ArrayList<Bubble> oldBubbles = new ArrayList<>();

    private ArrayList<Stal_Top> stals = new ArrayList<>();

    private ArrayList<Stal_Top> newStals= new ArrayList<>();
    private ArrayList<Stal_Top> oldStals = new ArrayList<>();




    public World(Rect screenSize, GameView parent) {
        this.screenSize = screenSize;
        this.parent = parent;

        player = new Sub(this);
        backgroundManager = new BackgroundManager(this);
    }

    public void update() {

        backgroundManager.update();
        for (Bubble b : bubbles) b.update();
        for (Stal_Top s : stals) s.update();

        player.update();


        // Add the stalactites.
        if (random.nextInt((int) (500 / player.getSpeed())) == 0) {
            if (stals.isEmpty() || (screenSize.width() - stals.get(stals.size() - 1).getPos().x) > Stal_Top.image.getWidth() * 2) {
                stals.add(new Stal_Top(new PointF(screenSize.width(), random.nextInt(20)), this));
            }
        }

        // Collision
        for (Stal_Top st : stals) {
            if (player.collision(st)) parent.exitToMenu();
        }

        // Clean Up the new and old entities
        entities.removeAll(oldEntities);
        entities.addAll(newEntities);

        bubbles.removeAll(oldBubbles);
        bubbles.addAll(newBubbles);

        stals.removeAll(oldStals);
        stals.addAll(newStals);

        newBubbles.clear();
        oldBubbles.clear();
        oldStals.clear();
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
        for (Stal_Top s : stals) s.render(canvas);

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
