package com.example.androidshootinggame.InGame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.example.androidshootinggame.R;

public class FirstEnemy extends Enemy{
    Bitmap enemyImg = BitmapFactory.decodeResource(getResources(), R.drawable.enemy1);
    Bitmap hpBarImg = null;

    public FirstEnemy(Context context) {
        super(context);
        initial();
    }

    public FirstEnemy(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void initial()
    {
        enemyMaxHp = 100f;
        enemyCurrentHp = enemyMaxHp;
        enemySpeed = (int) ((Math.random() * (10 - 3)) + 3);
        enemyWidth = enemyImg.getWidth();
        enemyHeight = enemyImg.getHeight();
        enemyX = (int) (Math.random() * displaySize.x);
        enemyY = 0;
    }

    long nextEnemy = 0;
    long enemyRecoilTime = (int) ((Math.random() * (2000 - 500)) + 500);      // 적 생성 속도

    FirstEnemy enemy;
    public void instanceEnemy() {
        if (System.currentTimeMillis() > nextEnemy)
        {
            enemy = new FirstEnemy(context);
            nextEnemy = System.currentTimeMillis() + enemyRecoilTime;
            enemyArray.add(enemy);
        }
    }

    @Override
    public void drawEnemy(Canvas canvas) {
        for (int i = 0; i < enemyArray.size(); i++)
        {
            if (enemyArray != null) {
                canvas.drawBitmap(enemyArray.get(i).enemyImg, enemyArray.get(i).enemyX, enemyArray.get(i).enemyY, null);

                if (enemyArray.get(i).hpBarImg != null)
                    canvas.drawBitmap(enemyArray.get(i).hpBarImg, enemyArray.get(i).enemyX - 50, enemyArray.get(i).enemyY - 50, null);

                enemyArray.get(i).move();
                removeEnemyOutDisplay(enemyArray.get(i));
            }
        }
    }

    @Override
    public void setHpBar(FirstEnemy hitedEnemy) {
        hitedEnemy.hpBarImg = BitmapFactory.decodeResource(getResources(), R.drawable.hp_bar);
        hitedEnemy.hpBarImg = Bitmap.createScaledBitmap(hitedEnemy.hpBarImg, (int) (hitedEnemy.hpBarImg.getWidth() * (hitedEnemy.enemyCurrentHp / hitedEnemy.enemyMaxHp)), hitedEnemy.hpBarImg.getHeight(), false);
    }

    @Override
    public void move() {
        this.enemyY += this.enemySpeed;
    }
}
