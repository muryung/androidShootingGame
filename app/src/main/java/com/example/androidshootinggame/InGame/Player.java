package com.example.androidshootinggame.InGame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.androidshootinggame.MainActivity;
import com.example.androidshootinggame.R;
import com.example.androidshootinggame.screen.InGameScreen;

import java.util.ArrayList;
import java.util.List;

public class Player extends View{

    Bitmap airPlaneImg = BitmapFactory.decodeResource(getResources(), R.drawable.airplane);

    public static int playerX = 300;
    public static int playerY = 1200;
    public int playerWidth, playerHeight;

    final int playerSpeed = 5;
    public float playerAttack = 25f;
    public static int playerHeart = 3;


    public Player(Context context) {
        super(context);
    }

    public Player(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        playerWidth = airPlaneImg.getWidth();
        playerHeight = airPlaneImg.getHeight();
    }

    public void PlayerDraw(Canvas canvas)
    {
        canvas.drawBitmap(airPlaneImg, (float) playerX, (float) playerY, null);
    }


    public void PlayerMove(JoyStickView joyStickView, int viewWidth, int viewHeight)
    {
        if (joyStickView != null)
        {
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

    public void PlayerLose(PlayerHeart playerHeart)
    {
        if (playerHeart.heartImgs.size() < 1)
        {
            MainActivity.viewFlipper.setDisplayedChild(3);
            InGameScreen.onThread = false;
        }

    }

}
