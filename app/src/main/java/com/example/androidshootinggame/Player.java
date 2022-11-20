package com.example.androidshootinggame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;

import androidx.annotation.Nullable;

public class Player extends View {

    Bitmap airPlaneImg = BitmapFactory.decodeResource(getResources(), R.drawable.airplane);

    private int playerX = 300;
    private int playerY = 1200;

    final int playerSpeed = 5;

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


    public void PlayerMove(JoyStickView joyStickView, int viewWidth, int viewHeight)
    {
        if (joyStickView != null)
        {
            System.out.println(viewWidth);
            playerX += Math.cos(Math.toRadians(joyStickView.getAngle())) * playerSpeed;
            playerY -= Math.sin(Math.toRadians(joyStickView.getAngle())) * playerSpeed;
            if (playerX < -100)
                playerX = -99;
            if (playerX >= viewWidth - 120)
                playerX = viewWidth - 119;
            if (playerY < 0)
                playerY = 1;
            if (playerY >= viewHeight - 188)
                playerY = viewHeight - 187;
        }
    }

}
