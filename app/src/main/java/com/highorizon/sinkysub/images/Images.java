package com.highorizon.sinkysub.images;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.highorizon.sinkysub.R;

public class Images {

    private static final Images instance = new Images();

    private Resources resources;

    //Background Textures.
    public static Bitmap ocean;
    public static Bitmap sand_0;
    public static Bitmap rock_0;

    public static Bitmap[] tops;
    public static Bitmap[] bottoms;

    public static Bitmap stalTop;
    public static Bitmap stalTop_collisionMap;

    //Bubble Textures.
    public static Bitmap[] bubbles;

    //Sub Textures.
    public static Bitmap[] subs;
    public static Bitmap sub_collisionMap;

    private Images() {

    }

    public void init(Resources resources) {
        this.resources = resources;

        //LOADING IMAGES
        ocean = scaleImage(R.drawable.ocean_background);
        sand_0 = scaleImage(R.drawable.sand);
        rock_0 = scaleImage(R.drawable.rock_0);

        stalTop = scaleImage(R.drawable.stal_top);
        stalTop_collisionMap = scaleImage(R.drawable.stal_top_collision_map);

        tops = new Bitmap[] {
                scaleImage(R.drawable.top_0),
                scaleImage(R.drawable.top_1),
                scaleImage(R.drawable.top_2),
        };
        bottoms = new Bitmap[] {
                scaleImage(R.drawable.bottom_0),
                scaleImage(R.drawable.bottom_1),
        };

        bubbles = new Bitmap[]{
                scaleImage(R.drawable.bubble_0),
                scaleImage(R.drawable.bubble_0),
                scaleImage(R.drawable.bubble_1),
                scaleImage(R.drawable.bubble_2),
                scaleImage(R.drawable.bubble_3),
                scaleImage(R.drawable.bubble_4),
                scaleImage(R.drawable.bubble_5),
                scaleImage(R.drawable.bubble_6),
                scaleImage(R.drawable.bubble_7)
        };

        subs = new Bitmap[]{
                scaleImage(R.drawable.sub_0),
                scaleImage(R.drawable.sub_1),
                scaleImage(R.drawable.sub_2),
                scaleImage(R.drawable.sub_3)
        };
        sub_collisionMap = scaleImage(R.drawable.sub_collision_map);
    }

    private Bitmap scaleImage(int picId) {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inScaled = false;
        Bitmap temp = BitmapFactory.decodeResource(resources, picId, o);
        return temp;
        //return Bitmap.createScaledBitmap(temp, temp.getWidth(), temp.getHeight(), false);
    }

    public static Images getInstance() {
        return instance;
    }

}
