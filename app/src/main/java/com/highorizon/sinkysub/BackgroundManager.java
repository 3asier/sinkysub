package com.highorizon.sinkysub;

import android.graphics.Canvas;

import com.highorizon.sinkysub.entities.BackgroundImage;
import com.highorizon.sinkysub.entities.Background_Sand;
import com.highorizon.sinkysub.entities.Entity;

import java.util.ArrayList;

public class BackgroundManager {

    public ArrayList<BackgroundImage> backgrounds = new ArrayList<>();

    public BackgroundManager(World world) {

    }

    private void init() {
        int widthNum = (int) Math.ceil(Background_Sand.sand_0.getWidth() / );
    }

    /**
     * Checks to see if any backgrounds have moved such that they are no longer in frame.
     * If so, they are removed and a new background tile is added to the right side of the screen.
     */
    public void update() {
        for background
    }

    public void render(Canvas canvas) {

    }


}
