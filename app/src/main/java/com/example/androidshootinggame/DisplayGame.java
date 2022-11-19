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

    Player player;
    JoyStickView joyStickView;


    public DisplayGame(Context context) {
        super(context);

    }

    public DisplayGame(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        thread.start();
        player = new Player(context);
        joyStickView = new JoyStickView(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        player.PlayerDraw(canvas);
        joyStickView.JoyStickDraw(canvas);
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            invalidate();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                break;
            }
        }

    }
}
