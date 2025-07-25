package com.example.androidshootinggame;

import static java.lang.Thread.sleep;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class DisplayGame extends View{
    private int loopInteval = 10;

    Player player;
    JoyStickView joyStickView;
    Bullet bullet;
    FirstEnemy firstEnemy;
    ContectObject contect;

    Context context;
    AttributeSet attrs;
    AllText allText;

    public DisplayGame(Context context) {
        super(context);
    }

    public DisplayGame(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.attrs = attrs;
        player = new Player(context);
        bullet = new Bullet(context);
        firstEnemy = new FirstEnemy(context);
        allText = new AllText(context);
        contect = new ContectObject();

        thread.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        player.PlayerDraw(canvas);

        if (joyStickView != null)
            joyStickView.JoyStickDraw(canvas);

        if (bullet != null)
            bullet.drawBullet(canvas);

        if (firstEnemy != null)
            firstEnemy.drawEnemy(canvas);

        if (allText != null)
            allText.drawRemainEnemyText(canvas);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int touchX = (int) event.getX();
        int touchY = (int) event.getY();

        switch (event.getAction()) {
            // 터치했을 때 1회
            case MotionEvent.ACTION_DOWN:
                joyStickView = new JoyStickView(context, attrs, touchX, touchY);
                break;

            // 터치하고 움직일 때
            case MotionEvent.ACTION_MOVE:
                joyStickView.joyStickPosX = touchX;
                joyStickView.joyStickPosY = touchY;
                joyStickView.keepStickOutCircle();
                break;

            // 손 땔 떄
            case MotionEvent.ACTION_UP:
                joyStickView = null;
                break;

            default:
                break;
        }
        return true;
    }

    private Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (!Thread.interrupted())
            {
                player.PlayerMove(joyStickView, getWidth(), getHeight());
                bullet.instanceBullet(context);
                firstEnemy.instanceEnemy();
                contect.enemyContectBullet(firstEnemy, bullet.bullets, player);
                invalidate();
                try {
                    Thread.sleep(loopInteval);
                } catch (InterruptedException e)
                {
                    break;
                }
            }
        }
    });

}
