package com.highorizon.sinkysub;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.highorizon.sinkysub.entities.BackgroundImage;

import java.util.ArrayList;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;

    private RectPlayer player;
    private Point playerPoint;
    private Sub p;
    private Bitmap sub_Image = BitmapFactory.decodeResource(getResources(), R.drawable.sub);
    private Bitmap sand = BitmapFactory.decodeResource(getResources(), R.drawable.sand);

    private ArrayList<BackgroundImage> sands = new ArrayList<>();

    private boolean tap;

    public GamePanel(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        player = new RectPlayer(new Rect(100, 100, 200, 200), Color.rgb(255, 0, 0));
        playerPoint = new Point(150, 150);

        p = new Sub(new Point(300, 300), sub_Image);

        int sandTile = (int) Math.ceil(super.getHeight() / sand.getWidth());

        for (int i = 0; i < sandTile; i++) {
            sands.add(new BackgroundImage(new Point(sand.getWidth() * i, super.getWidth()), sand));
        }

        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (Exception e) {
                retry = false;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                p.setTap(true);
                break;
            case MotionEvent.ACTION_UP:
                p.setTap(false);
                break;
            case MotionEvent.ACTION_MOVE:
                playerPoint.set((int) event.getX(), (int) event.getY());
        }

        return true;
        //return super.onTouchEvent(event);
    }

    public void update() {
        player.update(playerPoint);
        p.update();
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.drawColor(Color.BLUE);

        player.draw(canvas);
        p.draw(canvas);
    }

}
