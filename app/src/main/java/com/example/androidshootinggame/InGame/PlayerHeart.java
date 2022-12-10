package com.example.androidshootinggame.InGame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.example.androidshootinggame.R;

import java.util.ArrayList;

public class PlayerHeart extends Player{
    public ArrayList<Bitmap> heartImgs = new ArrayList<>();

    public PlayerHeart(Context context) {
        super(context);
        AddPlayerHeart();
    }

    public PlayerHeart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public void AddPlayerHeart()
    {
        for (int i = 0; i < playerHeart; i++)
            heartImgs.add(BitmapFactory.decodeResource(getResources(), R.drawable.heart));
    }

    public void HeartDraw(Canvas canvas)
    {
        int heartY = 300;
        for(Bitmap heartImg : heartImgs)
        {
            canvas.drawBitmap(heartImg, 0, 1920 - heartY, null);
            heartY += 125;
        }
    }
}
