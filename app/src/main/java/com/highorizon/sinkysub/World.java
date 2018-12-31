package com.highorizon.sinkysub;

import com.highorizon.sinkysub.Sub;
import com.highorizon.sinkysub.entities.Entity;

import java.util.ArrayList;

public class World {

    private Sub player;

    private ArrayList<Entity> entities = new ArrayList<>();

    private ArrayList<Entity> newEntities = new ArrayList<>();
    private ArrayList<Entity> oldEntities = new ArrayList<>();

    public World() {

    }

    private void update() {


        entities.removeAll(oldEntities);
        entities.addAll(newEntities);
    }

    public void add(Entity e) {
        newEntities.add(e);
    }

    public void remove(Entity e) {
        oldEntities.add(e);
    }

    private void render() {

    }

}
