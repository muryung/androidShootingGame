package com.example.androidshootinggame;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Enemy extends View{
    Bitmap enemyImg = BitmapFactory.decodeResource(getResources(), R.drawable.enemy1);

    Display display =  ((Activity) getContext()).getWindowManager().getDefaultDisplay();
    Point displaySize = new Point();

    public ArrayList<Enemy> enemys = new ArrayList<>();
    public Hp hp;

    public static final float enemyMaxHp = 100f;
    public float enemyCurrentHp = enemyMaxHp;
    int enemySpeed = 5;

    int enemyWidth = enemyImg.getWidth();
    int enemyHeight = enemyImg.getHeight();
    int enemyX = 0;
    int enemyY = 0;

    public Enemy(Context context) {
        super(context);
        hp = new Hp(context);
        display.getRealSize(displaySize);
        enemyX = (int) (Math.random() * displaySize.x);
    }

    public Enemy(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    long nextEnemy = 0;
    long enemyRecoilTime = 1000;      // 적 생성 속도

    public void instanceEnemy(Context context) {
        if (System.currentTimeMillis() > nextEnemy)
        {
            nextEnemy = System.currentTimeMillis() + enemyRecoilTime;
            Enemy enemy = new Enemy(context);
            enemys.add(enemy);
        }

    }

    public void drawEnemy(Canvas canvas) {
        for (int i = 0; i < enemys.size(); i++) {
            Enemy tmpEnemy = enemys.get(i);

            if (tmpEnemy != null) {
                if (ContectObject.isAttacked)
                    hp.drawHpBar(canvas, tmpEnemy);

                canvas.drawBitmap(tmpEnemy.enemyImg, tmpEnemy.enemyX, tmpEnemy.enemyY, null);
                tmpEnemy.move();

                removeEnemyOutDisplay(tmpEnemy, i);
            }
        }
    }


    private void removeEnemyOutDisplay(Enemy enemy, int i)
    {
        if (enemy.enemyY > displaySize.y) {
            enemys.remove(i);
        }
    }

    private void move() {
        enemyY += enemySpeed;
    }

}
