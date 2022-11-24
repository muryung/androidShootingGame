package com.example.androidshootinggame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class Hp extends Enemy {
    Bitmap hpBarImg = BitmapFactory.decodeResource(getResources(), R.drawable.hp_bar);

    public Hp(Context context) {
        super(context);
    }

    public Hp(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void drawHpBar(Canvas canvas, Enemy tmpEnemy)
    {
        reduceHpBar(tmpEnemy);
        canvas.drawBitmap(hpBarImg, tmpEnemy.enemyX - 50, tmpEnemy.enemyY - 50, null);
    }

    private void reduceHpBar(Enemy tmpEnemy)
    {
        hpBarImg = Bitmap.createScaledBitmap(hpBarImg, (int) (hpBarImg.getWidth() / (tmpEnemy.enemyMaxHp / tmpEnemy.enemyCurrentHp)), hpBarImg.getHeight(), true);
    }
}
