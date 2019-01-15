package com.highorizon.sinkysub;

import android.graphics.Canvas;
import android.graphics.PointF;

import com.highorizon.sinkysub.entities.Background_Sand;

import java.util.ArrayList;

public class BackgroundManager {

    private World world;

    public ArrayList<Background_Sand> sands = new ArrayList<>();

    public BackgroundManager(World world) {
        this.world = world;

        init();
    }

    /**
     * Calculates how many of each background image need to be placed to tile the entire width.
     */
    private void init() {
        int widthNum = (int) Math.ceil((float) world.screenSize.width() / Background_Sand.image.getWidth());
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

        if (sands.get(0).getPos().x < 0) {
            sands.remove(0);
            sands.add(new Background_Sand(new PointF(sands.get(sands.size() - 1).getPos().x + Background_Sand.image.getWidth(), world.screenSize.height() - Background_Sand.image.getHeight())));
        }
    }

    public void render(Canvas canvas) {
        for (Background_Sand sand : sands) sand.render(canvas);
    }


}
