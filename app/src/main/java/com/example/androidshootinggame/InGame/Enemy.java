package com.example.androidshootinggame.InGame;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public abstract class Enemy extends View {

    Display display =  ((Activity) getContext()).getWindowManager().getDefaultDisplay();
    Point displaySize = new Point();

    public ArrayList<FirstEnemy> enemyArray = new ArrayList<>();
    public Context context;

    float enemyMaxHp = 100f;
    float enemyCurrentHp = enemyMaxHp;

    int enemySpeed;
    int enemyWidth;
    int enemyHeight;
    int enemyX;
    int enemyY;

    public Enemy(Context context) {
        super(context);
        this.context = context;
        display.getRealSize(displaySize);
    }

    public Enemy(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    abstract public void drawEnemy(Canvas canvas);

    abstract public void setHpBar(FirstEnemy hitedEnemy);

    abstract  public void move();

    public void removeEnemyOutDisplay(FirstEnemy enemy) {
        if (enemy.enemyY > displaySize.y) {
            enemyArray.remove(enemy);
        }
    }

}
