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

import com.highorizon.sinkysub.images.Images;

public class GameView extends View {

    Handler handler; //Schedules a runnable after a certain delay
    Runnable runnable;

    public static float BASE_SCALE = 1.5f;
    public static float SCALE = BASE_SCALE;

    // Frame Limiter Variables
    private final int MAX_FPS = 30;
    private double averageFPS;

    long startTime;
    long timeMillis = 1000 / MAX_FPS;
    long waitTime;
    int frameCount = 0;
    long totalTime = 0;
    long targetTime = 1000 / MAX_FPS;

    long lastTime = System.nanoTime();
    long thisTime = lastTime;
    long dt = thisTime - lastTime; // The change in time
    long st = System.nanoTime();

    Bitmap background_0;

    Display display;
    Point point;
    int dWidth, dHeight; //Displays width and height
    Rect screenSize;

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

        display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        point = new Point();
        display.getRealSize(point);
        dWidth = point.x;
        dHeight = point.y;

        screenSize = new Rect(0, 0, dWidth, dHeight);
        SCALE = BASE_SCALE * screenSize.height() / 1080;

        Images.getInstance().init(getResources());

        world = new World(screenSize, this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        startTime = System.nanoTime();

        // Calculate the amount of time passed since the last update.
        thisTime = System.nanoTime();
        dt = thisTime - lastTime;
        lastTime = System.nanoTime(); // Set lastTime to keep track of the starting time for the next loop.

        // Making changes to 'dt' or replacing it with a different variable will cause the time scale of the game to change.

        world.update(dt); // Update the game world respective of how much time has passed.


        // Draw the background
        //canvas.drawBitmap(Images.ocean, null, screenSize, null);

        //Draw the world
        world.render(canvas);

        // --- Stop Calling things ---

        timeMillis = (System.nanoTime() - startTime) / 1000000;
        waitTime = targetTime - timeMillis;

        /*if (waitTime > 0) { // Wait the required time.
            this.postDelayed(runnable, waitTime);
        } else {
            this.postDelayed(runnable, 0);
        }

        totalTime += System.nanoTime() - startTime + waitTime * 1000000;
        frameCount++;
        if (frameCount == MAX_FPS) {
            //System.out.println(totalTime / frameCount);
            averageFPS = (int) 1000 / ((totalTime / frameCount) / 1000000);
            frameCount = 0;
            totalTime = 0;
            System.out.println("AVERAGE FPS: " + averageFPS);
        }*/
        frameCount++;
        if (st + 1000000000 < System.nanoTime()) {
            System.out.println(frameCount);
            frameCount = 0;
            st = System.nanoTime();
        }

        this.post(runnable);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int pointerCount = event.getPointerCount();

        if (pointerCount == 5) {
            Toast.makeText(getContext(), "Looks like you got ligma!", Toast.LENGTH_SHORT).show();
        }
        if (action == MotionEvent.ACTION_UP) { //Input released from screen
            world.tapUp(new PointF(event.getX(), event.getY()));
        } else if (action == MotionEvent.ACTION_DOWN) { //A tap on the screen;
            world.tapDown(new PointF(event.getX(), event.getY()));
        }

        return true;
    }

    public void exitToMenu() {
        ((Activity) getContext()).finish();
    }

}
