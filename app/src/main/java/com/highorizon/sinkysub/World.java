package com.highorizon.sinkysub;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;

import com.highorizon.sinkysub.cave.CaveGen;
import com.highorizon.sinkysub.entities.Bubble;
import com.highorizon.sinkysub.entities.Cave_Bottom;
import com.highorizon.sinkysub.entities.Cave_Top;
import com.highorizon.sinkysub.entities.Entity;
import com.highorizon.sinkysub.entities.Mob;
import com.highorizon.sinkysub.entities.Stalactite;
import com.highorizon.sinkysub.entities.Stalactite_Bottom;
import com.highorizon.sinkysub.entities.Stalactite_Top;

import java.util.ArrayList;
import java.util.Random;

public class World {

    public GameView parent;

    private Random random = new Random();

    public Rect screenSize;

    public Sub player;

    private ArrayList<Entity> entities = new ArrayList<>();

    private ArrayList<Entity> newEntities = new ArrayList<>();
    private ArrayList<Entity> oldEntities = new ArrayList<>();

    public CaveGen cave;

    public World(Rect screenSize, GameView parent) {
        this.screenSize = screenSize;
        this.parent = parent;

        //Generate the cave
        cave = new CaveGen(this);

        // PLAYER IS ALWAYS THE FIRST ENTITY
        player = new Sub(this);
        entities.add(player);

        //initCave();
    }

    /**
     * Tiles the initial set of cave tops and bottoms across the screen.
     */
    private void initCave() {
        int tileNumber = (int) Math.ceil((float) screenSize.width() / Cave_Top.size.x) + 1;

        for (int i = 0; i < tileNumber; i++) {
            entities.add(new Cave_Top(new PointF(i * Cave_Top.size.x, 0.0f), this));
        }

        tileNumber = (int) Math.ceil((float) screenSize.width() / Cave_Bottom.size.x) + 1;
        for (int i = 0; i < tileNumber; i++) {
            entities.add(new Cave_Bottom(new PointF(i * Cave_Bottom.size.x, screenSize.height() - Cave_Bottom.size.y), this));
        }
    }

    public void update(long dt) {

        // Update all entities in the world.
        for (Entity e : entities) {
            e.update(dt);
        }


        // Add the stalactites.
        Stalactite.timer += (dt / 1000000);
        if (Stalactite.timer > 1000 / player.getSpeed()) {
            if (random.nextBoolean()) entities.add(new Stalactite_Top(this));
            else entities.add(new Stalactite_Bottom(this));
            Stalactite.timer = 0;
        }

        cave.update(dt);

        // Clean Up the new and old entities
        entities.removeAll(oldEntities);
        entities.addAll(newEntities);

        for (Entity e : oldEntities) e = null;

        oldEntities.clear();
        newEntities.clear();

        // Check to see if the player has been removed from the world.
        if (!entities.contains(player)) parent.exitToMenu();
    }

    public void add(Entity e) {
        newEntities.add(e);
    }

    public void remove(Entity e) {
        oldEntities.add(e);
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void render(Canvas canvas) {
        for (Entity e : entities) {
            if (e instanceof Stalactite) {
                ((Mob) e).render(canvas);
            }
        }
        for (Entity e : entities) {
            if (e instanceof Mob && !(e instanceof Sub) && !(e instanceof Stalactite)) {
                ((Mob) e).render(canvas);
            }
        }
        // Render the player last
        player.render(canvas);

        cave.render(canvas);
    }

    public void tapDown(PointF pointF) {
        player.tapDown();
    }

    public void tapUp(PointF pointF) {
        player.tapUp();
    }

}
