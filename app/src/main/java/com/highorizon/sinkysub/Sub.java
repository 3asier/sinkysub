package com.highorizon.sinkysub;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;

import com.highorizon.sinkysub.entities.Bubble;
import com.highorizon.sinkysub.entities.Entity;
import com.highorizon.sinkysub.entities.Mob;
import com.highorizon.sinkysub.entities.World_Object;
import com.highorizon.sinkysub.images.CollisionMap;
import com.highorizon.sinkysub.images.Images;

public class Sub extends Mob {

    private World world;

    private float maxYVel = 2.0f;
    private float speed = 0.2f;

    private int animCount = 0;
    private int animScale = random.nextInt(4) + 100;

    private Bitmap[] images = Images.subs;

    private boolean tap = false;

    private Path boundry = new Path();

    public Sub(World world) {
        super(new PointF(world.screenSize.width() / 9, world.screenSize.height() / 3), 0.0f, 0.0f);
        this.world = world;
        collisionMap = new CollisionMap(this, Images.sub_collisionMap);

        boundry.moveTo(pos.x, pos.y + getSize().y / 3);
        boundry.lineTo(pos.x + getSize().x / 2, pos.y + getSize().y / 4);
        boundry.lineTo(pos.x + getSize().x, pos.y + getSize().y / 3);
        boundry.lineTo(pos.x + getSize().x, pos.y + getSize().y - getSize().y / 3);
        boundry.lineTo(pos.x + getSize().x / 2, pos.y + getSize().y - getSize().y / 8);
        boundry.lineTo(pos.x, pos.y + getSize().y - getSize().y / 4);
        boundry.close();
    }

    @Override
    public void update(long dt) {
        speed += 0.0005f;
        if (animCount >= images.length * animScale - 1) animCount = 0;
        animCount += (int) (dt / 1000000);

        if (tap) {
            yVel -= 0.08;
        } else {
            yVel += 0.08;
        }

        //if (random.nextInt(20) == 0)
        //    world.add(new Bubble(new PointF(pos.x, pos.y + images[0].getHeight() / 2 + 16 - (yVel * 1f) + (random.nextInt(30) - 15)), world));

        clampVel();

        super.move(dt);

        Matrix translateMatrix = new Matrix();
        translateMatrix.setTranslate(xVel * (dt / 1000000),yVel * (dt / 1000000));
        boundry.transform(translateMatrix);

        checkCollision();
    }

    public void checkCollision() {
        for (Entity e : world.getEntities()) {
            if (e instanceof World_Object) {
                World_Object wo = (World_Object) e;
                if (collision(wo)) wo.onCollision(this);
            }
        }

        if (world.cave.collision(boundry)) world.remove(this);
    }

    private void clampVel() {
        if (yVel > maxYVel) yVel = maxYVel;
        if (yVel < -maxYVel) yVel = -maxYVel;
    }

    public void tapDown() {
        this.tap = true;
    }

    public void tapUp() {
        this.tap = false;
    }

    @Override
    public void render(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(Color.argb(128, 255, 0, 0));
        canvas.rotate(yVel * 10, pos.x + images[0].getWidth() / 2, pos.y + images[0].getHeight() / 2);
        canvas.drawBitmap(images[(int) (Math.floor(animCount / animScale) % images.length)], pos.x, pos.y, null);
        canvas.rotate(-yVel * 10, pos.x + images[0].getWidth() / 2, pos.y + images[0].getHeight() / 2);

        canvas.drawPath(boundry, p);
    }

    public float getSpeed() {
        return speed;
    }

    public PointF getSize() {
        return new PointF(images[0].getWidth(), images[0].getHeight());
    }
}
