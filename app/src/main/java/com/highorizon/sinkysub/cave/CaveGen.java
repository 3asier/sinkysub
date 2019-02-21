package com.highorizon.sinkysub.cave;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Region;
import android.graphics.Shader;

import com.highorizon.sinkysub.World;
import com.highorizon.sinkysub.images.Images;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CaveGen {

    public int verticalBound = 250;

    private World world;
    private Random random = new Random();

    public ArrayList<PointF> topSegments = new ArrayList<>();
    public ArrayList<PointF> bottomSegments = new ArrayList<>();

    private Path top = new Path();
    private Path bottom = new Path();

    private final int shadingNumber = 20;
    private ArrayList<Point> shadingLocations = new ArrayList<>();
    private ArrayList<Path> shadingPaths = new ArrayList<>();

    public CaveGen(World world) {
        this.world = world;

        init();
    }

    /**
     * Initiate the cave by creating vertices that cover the width of the screen
     */
    private void init() {
        topSegments.add(new PointF(0, verticalBound));
        bottomSegments.add(new PointF(0, world.screenSize.height() - verticalBound));

        addVertices();
    }

    private void addVertices() {
        while(topSegments.isEmpty() || topSegments.get(topSegments.size() - 1).x < world.screenSize.width()) {
            generateTop();
        }
        while(bottomSegments.isEmpty() || bottomSegments.get(bottomSegments.size() - 1).x < world.screenSize.width()) {
            generateBottom();
        }
    }

    public void update(long dt) {
        // Move all the points to the left at the same speed as the player is moving.
        for (PointF p : topSegments) p.x -= world.player.getSpeed() * (dt / 1000000);
        for (PointF p : bottomSegments) p.x -= world.player.getSpeed() * (dt / 1000000);

        // If the second vertex in the list is off the screen, that means that the first vertex,
        // and the line it creates, is fully off the screen and can be removed.
        if (topSegments.get(1).x < 0) topSegments.remove(0);
        if (bottomSegments.get(1).x < 0) bottomSegments.remove(0);

        addVertices(); //Add any new vertices that are needed to fill the screen.

        if (random.nextInt(shadingNumber) == 0) generateShading();

        createPaths();
    }

    private void createPaths() {
        top = new Path();
        top.moveTo(0, 0);
        for (PointF p : topSegments) top.lineTo(p.x, p.y);
        top.lineTo(world.screenSize.width(), 0);
        top.close();

        bottom = new Path();
        bottom.moveTo(0, world.screenSize.height());
        for (PointF p : bottomSegments) bottom.lineTo(p.x, p.y);
        bottom.lineTo(world.screenSize.width(), world.screenSize.height());
        bottom.close();
    }

    public boolean collision(Path path) {
        Region clip = new Region(0, 0, world.screenSize.width(), world.screenSize.height());

        Region topR = new Region();
        topR.setPath(top, clip);

        Region bottomR = new Region();
        bottomR.setPath(bottom, clip);

        Region player = new Region();
        player.setPath(path, clip);

        if (!topR.quickReject(player) && topR.op(player, Region.Op.INTERSECT)) {
            return true;
        }
        if (!bottomR.quickReject(player) && bottomR.op(player, Region.Op.INTERSECT)) {
            return true;
        }
        return false;
    }

    /**
     * Generate a new vertex for the cave at the top of the screen.
     */
    private void generateTop() {
        PointF point = new PointF();
        point.x = topSegments.get(topSegments.size() - 1).x + random.nextInt(100) + 50;
        point.y = random.nextInt( verticalBound) + verticalBound / 10;
        topSegments.add(point);
    }

    /**
     * Generates a new vertex for the cave at the bottom of the screen.
     */
    private void generateBottom() {
        PointF point = new PointF();
        point.x = bottomSegments.get(bottomSegments.size() - 1).x + random.nextInt(200) + 200;
        point.y = world.screenSize.height() - (random.nextInt( verticalBound) + verticalBound / 10);
        bottomSegments.add(point);
    }

    private void generateShading() {
        int randomBound = world.screenSize.height() / 4;

        int originX = random.nextInt(world.screenSize.height());
        int originY = random.nextInt(world.screenSize.width());

        Point origin = new Point(originX, originY);

        Path newPath = new Path();

        newPath.moveTo(originX + random.nextInt(randomBound) - randomBound / 2, originY + random.nextInt(randomBound) - randomBound / 2);

        int numSides = random.nextInt(6) + 4;

        for (int i = 0; i < numSides; i++) {
            newPath.moveTo(originX + random.nextInt(randomBound) - randomBound / 2, originY + random.nextInt(randomBound) - randomBound / 2);
        }

        newPath.close();

        shadingLocations.add(origin);
        shadingPaths.add(newPath);
    }

    public void render(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(Color.BLACK);

        p.setShader(new LinearGradient(0, 0, 0, verticalBound, Color.rgb(12, 44, 82), Color.rgb(14, 30, 56), Shader.TileMode.CLAMP));
        canvas.drawPath(top, p);
        p.setShader(new LinearGradient(0, world.screenSize.height() - verticalBound, 0, world.screenSize.height(), Color.rgb(14, 30, 56), Color.rgb(12, 44, 82), Shader.TileMode.CLAMP));
        canvas.drawPath(bottom, p);

        Canvas c = new Canvas(Images.sand_0);
        c.clipPath(top);
        c.drawBitmap(Images.sand_0, 0, 0, null);
    }

}
