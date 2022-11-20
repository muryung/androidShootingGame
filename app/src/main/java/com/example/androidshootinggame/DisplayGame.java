package com.example.androidshootinggame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class DisplayGame extends View implements Runnable{

    private Thread thread = new Thread(this);

    private int loopInteval = 10;

    Player player;
    JoyStickView joyStickView;
    Context context;

    public DisplayGame(Context context) {
        super(context);

    }

    public DisplayGame(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        player = new Player(context);

        thread.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        player.PlayerDraw(canvas);

        if (joyStickView != null)
            joyStickView.JoyStickDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int touchX = (int) event.getX();
        int touchY = (int) event.getY();

        switch (event.getAction()) {
            // 터치했을 때 1회
            case MotionEvent.ACTION_DOWN:
                joyStickView = new JoyStickView(context, touchX, touchY);
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

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            post(() -> {
                player.PlayerMove(joyStickView, this.getWidth(), this.getHeight());
                invalidate();
            });
            try {
                Thread.sleep(loopInteval);
            } catch (InterruptedException e) {
                break;
            }
        }

    }
}
