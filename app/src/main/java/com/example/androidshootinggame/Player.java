package com.example.androidshootinggame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Player extends View {
    Bitmap airPlaneImg = BitmapFactory.decodeResource(getResources(), R.drawable.airplane);;

    public static double playerX = 300f;
    public static double playerY = 1200f;


    public Player(Context context) {
        super(context);
    }

    public Player(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void PlayerDraw(Canvas canvas)
    {
        canvas.drawBitmap(airPlaneImg, (float) playerX, (float) playerY, null);
    }
}
