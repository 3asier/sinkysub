package com.highorizon.sinkysub;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {

    Handler handler; //Schedules a runnable after a certain delay
    Runnable runnable;
    final int UPDATE_MILLIS = 30;

    Bitmap background_0;

    Display display;
    Point point;
    int dWidth, dHeight; //Displays width and height
    Rect screenSize;

    //Background Textures.
    public static Bitmap sand_0;

    //Bubble Textures.
    public static Bitmap[] bubbles;

    //Sub Textures.
    public static Bitmap sub_0;

    World world;

    public GameView(Context context) {
        super(context);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate(); //Calls onDraw()
            }
        };

        //LOADING IMAGES
        sand_0 = BitmapFactory.decodeResource(getResources(), R.drawable.sand);

        bubbles = new Bitmap[] {
                BitmapFactory.decodeResource(getResources(), R.drawable.bubble_0),
                BitmapFactory.decodeResource(getResources(), R.drawable.bubble_1),
                BitmapFactory.decodeResource(getResources(), R.drawable.bubble_2),
                BitmapFactory.decodeResource(getResources(), R.drawable.bubble_3),
                BitmapFactory.decodeResource(getResources(), R.drawable.bubble_4),
                BitmapFactory.decodeResource(getResources(), R.drawable.bubble_5),
                BitmapFactory.decodeResource(getResources(), R.drawable.bubble_6),
                BitmapFactory.decodeResource(getResources(), R.drawable.bubble_7)
        };

        sub_0 = BitmapFactory.decodeResource(getResources(), R.drawable.sub);

        display = ((Activity)getContext()).getWindowManager().getDefaultDisplay();
        point = new Point();
        display.getSize(point);
        dWidth = point.x;
        dHeight = point.y;

        screenSize = new Rect(0, 0, dWidth, dHeight);

        world = new World(screenSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        world.update();

        world.render(canvas);

        handler.postDelayed(runnable, UPDATE_MILLIS);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) { //A tap on the screen;
            world.tapDown(new PointF(event.getX(), event.getY()));
        }
        if (action == MotionEvent.ACTION_UP) { //Input released from screen
            world.tapUp(new PointF(event.getX(), event.getY()));
        }

        return true;
    }

}