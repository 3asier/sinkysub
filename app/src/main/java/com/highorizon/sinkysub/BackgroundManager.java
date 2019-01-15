package com.highorizon.sinkysub;

import android.graphics.Canvas;
import android.graphics.PointF;

import com.highorizon.sinkysub.entities.Background_Rock;
import com.highorizon.sinkysub.entities.Background_Sand;

import java.util.ArrayList;
import java.util.Random;

public class BackgroundManager {

    private World world;
    private Random random = new Random();

    public ArrayList<Background_Sand> sands = new ArrayList<>();
    public ArrayList<Background_Rock> rocks = new ArrayList<>();

    public BackgroundManager(World world) {
        this.world = world;

        init();
    }

    /**
     * Calculates how many of each background image need to be placed to tile the entire width.
     */
    private void init() {
        int widthNum = (int) Math.ceil((float) world.screenSize.width() / Background_Sand.image.getWidth()) + 1;
        for (int i = 0; i < widthNum; i++) {
            sands.add(new Background_Sand(new PointF(Background_Sand.image.getWidth() * i, world.screenSize.height() - Background_Sand.image.getHeight())));
        }
    }

    /**
     * Checks to see if any backgrounds have moved such that they are no longer in frame.
     * If so, they are removed and a new background tile is added to the right side of the screen.
     */
    public void update() {
        for (Background_Sand sand : sands) sand.update();

        if (sands.get(0).getPos().x < -Background_Sand.image.getWidth()) {
            sands.remove(0);
            sands.add(new Background_Sand(new PointF(sands.get(sands.size() - 1).getPos().x + Background_Sand.image.getWidth(), world.screenSize.height() - Background_Sand.image.getHeight())));
        }

        for (Background_Rock r : rocks) r.update();

        if (rocks.size() != 0 && rocks.get(0).getPos().x < -Background_Rock.image.getWidth()) {
            rocks.remove(0);
        }

        if (random.nextInt(200) == 0) { //If random AND this roch wont overlap any current one.
            if (rocks.size() != 0) {
                if (rocks.get(rocks.size() - 1).getPos().x < world.screenSize.width() - Background_Rock.image.getWidth()) {
                    rocks.add(new Background_Rock(new PointF(world.screenSize.width(), world.screenSize.height() - Background_Rock.image.getHeight())));
                }
            } else {
                rocks.add(new Background_Rock(new PointF(world.screenSize.width(), world.screenSize.height() - Background_Rock.image.getHeight())));
            }
        }
    }

    public void render(Canvas canvas) {
        for (Background_Rock r : rocks) r.render(canvas);
        for (Background_Sand sand : sands) sand.render(canvas);
    }


}
