package com.highorizon.sinkysub.entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;

import com.highorizon.sinkysub.GameView;
import com.highorizon.sinkysub.Images;
import com.highorizon.sinkysub.Sub;
import com.highorizon.sinkysub.World;

public class Stal_Top extends BackgroundImage {

    public static Bitmap image = Images.stalTop;
    private World world;

    public Stal_Top(PointF pos, World world) {
        super(pos, Images.stalTop, world.player.getSpeed());

        this.world = world;
    }

    public void update() {
        super.update();
        collision();
    }

    private void collision() {
        Sub p = world.player;

        // Simple collision checking the 4 corners of the sub against the corners of this texture.

        // Top Left
        if (p.pos.x >= pos.x && p.pos.x < pos.x + image.getWidth() && p.pos.y > pos.y && p.pos.y < pos.y + image.getHeight()) {
            world.parent.exitToMenu();
        }

        // Top Right
        if (p.pos.x + p.getSize().x >= pos.x && p.pos.x + p.getSize().x < pos.x + image.getWidth() && p.pos.y > pos.y && p.pos.y < pos.y + image.getHeight()) {
            world.parent.exitToMenu();
        }

        // Bottom Left
        if (p.pos.x >= pos.x && p.pos.x < pos.x + image.getWidth() && p.pos.y + p.getSize().y > pos.y && p.pos.y + p.getSize().y < pos.y + image.getHeight()) {
            world.parent.exitToMenu();
        }

        // Bottom Right
        if (p.pos.x + p.getSize().x >= pos.x && p.pos.x + p.getSize().x < pos.x + image.getWidth() && p.pos.y + p.getSize().y > pos.y && p.pos.y + p.getSize().y < pos.y + image.getHeight()) {
            world.parent.exitToMenu();
        }
    }

}
