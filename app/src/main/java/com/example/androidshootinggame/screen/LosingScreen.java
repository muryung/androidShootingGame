package com.example.androidshootinggame.screen;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

import com.example.androidshootinggame.InGame.ContactObject;
import com.example.androidshootinggame.InGame.Player;
import com.example.androidshootinggame.MainActivity;
import com.example.androidshootinggame.R;

public class LosingScreen extends View {
    Bitmap backgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
    Bitmap windowBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.windoww);

    Bitmap losingTextBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.losing_text);
    Bitmap scoreTextBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.score_text);

    Rect replayRect = new Rect();
    Bitmap replayButtonBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.replay_btn);

    Paint scoreTextPaint;

    public LosingScreen(Context context) {
        super(context);
    }

    public LosingScreen(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(backgroundBitmap, 0, 0, null);


        drawWindow(canvas);
        drawLoseText(canvas);
        drawScoreText(canvas);
        drawReplayButton(canvas);

        initial();
        canvas.drawText(String.valueOf(ContactObject.enemyCount), 520, 800, scoreTextPaint);

        Player.playerHeart = 3;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int touchX = (int) event.getX();
        int touchY = (int) event.getY();
        switch (event.getAction()) {
            // 터치했을 때 1회
            case MotionEvent.ACTION_DOWN:
                if (replayRect.contains(touchX, touchY))
                {
                    MainActivity.viewFlipper.setDisplayedChild(0);
                }
                break;

            default:
                break;
        }
        return true;
    }

    private void initial()
    {
        scoreTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        AssetManager am = this.getContext().getAssets();
        Typeface plain = Typeface.createFromAsset( am, "pfstardust.ttf" );
        scoreTextPaint.setTypeface(Typeface.create( plain, Typeface.BOLD ));

        scoreTextPaint.setTypeface(Typeface.createFromAsset(am, "pfstardust.ttf"));
        scoreTextPaint.setTextSize(100f);
        scoreTextPaint.setColor(Color.WHITE);
    }

    private void drawWindow(Canvas canvas)
    {
        windowBitmap = Bitmap.createScaledBitmap(windowBitmap, this.getWidth() - 200,  1600, false);
        canvas.drawBitmap(windowBitmap, 100,100, null);
    }

    private void drawLoseText(Canvas canvas)
    {
        losingTextBitmap = Bitmap.createScaledBitmap(losingTextBitmap, 600,  100, false);
        canvas.drawBitmap(losingTextBitmap, imageXSetCenter(losingTextBitmap.getWidth()),140, null);
    }

    private void drawScoreText(Canvas canvas)
    {
        scoreTextBitmap = Bitmap.createScaledBitmap(scoreTextBitmap, 300,  70, false);
        canvas.drawBitmap(scoreTextBitmap, imageXSetCenter(scoreTextBitmap.getWidth()),350, null);
    }

    private void drawReplayButton(Canvas canvas)
    {
        int left = imageXSetCenter(replayButtonBitmap.getWidth());
        int up = imageYSetCenter(replayButtonBitmap.getHeight()) + 400;
        int right = imageXSetCenter(replayButtonBitmap.getWidth()) + replayButtonBitmap.getWidth();
        int bottom = imageYSetCenter(replayButtonBitmap.getHeight()) + replayButtonBitmap.getHeight() + 400;

        replayButtonBitmap = Bitmap.createScaledBitmap(replayButtonBitmap, 200, 200, false);
        canvas.drawBitmap(replayButtonBitmap,  left, up, null);
        replayRect.set(left, up, right, bottom);
    }

    private int imageXSetCenter(int width)
    {
        return (this.getWidth() / 2) - (width / 2);
    }

    private int imageYSetCenter(int height)
    {
        return (this.getHeight() / 2) - (height / 2);
    }
}
