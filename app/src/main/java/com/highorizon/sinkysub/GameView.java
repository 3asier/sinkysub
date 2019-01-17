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
import android.widget.Toast;

public class GameView extends View {

    Handler handler; //Schedules a runnable after a certain delay
    Runnable runnable;

    // Frame Limiter Variables
    private final int MAX_FPS = 30;
    private double averageFPS;

    long startTime;
    long timeMillis = 1000 / MAX_FPS;
    long waitTime;
    int frameCount = 0;
    long totalTime = 0;
    long targetTime = 1000 / MAX_FPS;

    Bitmap background_0;

    Display display;
    Point point;
    int dWidth, dHeight; //Displays width and height
    Rect screenSize;

    //Background Textures.
    public static Bitmap ocean;
    public static Bitmap sand_0;
    public static Bitmap rock_0;

    public static Bitmap[] tops;

    //Bubble Textures.
    public static Bitmap[] bubbles;

    //Sub Textures.
    public static Bitmap[] subs;

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
        ocean = BitmapFactory.decodeResource(getResources(), R.drawable.ocean_background);
        sand_0 = BitmapFactory.decodeResource(getResources(), R.drawable.sand);
        rock_0 = BitmapFactory.decodeResource(getResources(), R.drawable.rock_0);

        tops = new Bitmap[] {
                BitmapFactory.decodeResource(getResources(), R.drawable.top_0),
                BitmapFactory.decodeResource(getResources(), R.drawable.top_1),
                BitmapFactory.decodeResource(getResources(), R.drawable.top_2),
        };

        bubbles = new Bitmap[]{
                BitmapFactory.decodeResource(getResources(), R.drawable.bubble_0),
                BitmapFactory.decodeResource(getResources(), R.drawable.bubble_1),
                BitmapFactory.decodeResource(getResources(), R.drawable.bubble_2),
                BitmapFactory.decodeResource(getResources(), R.drawable.bubble_3),
                BitmapFactory.decodeResource(getResources(), R.drawable.bubble_4),
                BitmapFactory.decodeResource(getResources(), R.drawable.bubble_5),
                BitmapFactory.decodeResource(getResources(), R.drawable.bubble_6),
                BitmapFactory.decodeResource(getResources(), R.drawable.bubble_7)
        };

        subs = new Bitmap[]{
                BitmapFactory.decodeResource(getResources(), R.drawable.sub_0),
                BitmapFactory.decodeResource(getResources(), R.drawable.sub_1),
                BitmapFactory.decodeResource(getResources(), R.drawable.sub_2),
                BitmapFactory.decodeResource(getResources(), R.drawable.sub_3)
        };

        display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
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

        startTime = System.nanoTime();

        // Start Calling Things
        world.update();

        canvas.drawBitmap(ocean, null, screenSize, null);
        world.render(canvas);

        // Stop Calling things

        timeMillis = (System.nanoTime() - startTime) / 1000000;
        waitTime = targetTime - timeMillis;

        if (waitTime > 0) { // Wait the required time.
                this.postDelayed(runnable, waitTime);
        }

        totalTime += System.nanoTime() - startTime + waitTime * 1000000;
        frameCount++;
        if (frameCount == MAX_FPS) {
            //System.out.println(totalTime / frameCount);
            averageFPS = (int) 1000 / ((totalTime / frameCount) / 1000000);
            frameCount = 0;
            totalTime = 0;
            System.out.println("AVERAGE FPS: " + averageFPS);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
	int pointerCount = event.getPointerCount();

	if(pointerCount == 5) {
	    Toast.makeText(getContext(), "Looks like you got ligma!", Toast.LENGTH_SHORT).show();
	}
	if (action == MotionEvent.ACTION_DOWN) { //A tap on the screen;
            world.tapDown(new PointF(event.getX(), event.getY()));
        }
        if (action == MotionEvent.ACTION_UP) { //Input released from screen
            world.tapUp(new PointF(event.getX(), event.getY()));
        }

        return true;
    }

}
