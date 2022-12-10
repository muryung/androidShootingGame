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

import com.example.androidshootinggame.InGame.PlayerHeart;
import com.example.androidshootinggame.MainActivity;
import com.example.androidshootinggame.R;

public class IntroScreen extends View {
    Bitmap backgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg);

    Rect startButtonRect = new Rect();
    Bitmap startButtonBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.start_btn);

    Rect helpButtonRect = new Rect();
    Bitmap helpButtonBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.faq_btn);

    Paint mainTextPaint;

    public IntroScreen(Context context) {
        super(context);

    }

    public IntroScreen(Context context, AttributeSet attrs) {
        super(context, attrs);
        initial();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(backgroundBitmap, 0, 0, null);

        drawStartButton(canvas);
        drawHelpButton(canvas);

        canvas.drawText("1901285  최무령", 180, 500, mainTextPaint);

        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int touchX = (int) event.getX();
        int touchY = (int) event.getY();
        switch (event.getAction()) {
            // 터치했을 때 1회
            case MotionEvent.ACTION_DOWN:
                if (startButtonRect.contains(touchX, touchY))
                {
                    MainActivity.viewFlipper.setDisplayedChild(1);
                    InGameScreen.onThread = true;
                }

                if (helpButtonRect.contains(touchX, touchY))
                    MainActivity.viewFlipper.setDisplayedChild(2);
                break;

            default:
                break;
        }
        return true;
    }

    private void drawStartButton(Canvas canvas)
    {
        int left = imageXSetCenter(startButtonBitmap.getWidth());
        int up = imageYSetCenter(startButtonBitmap.getHeight());
        int right = imageXSetCenter(startButtonBitmap.getWidth()) + startButtonBitmap.getWidth();
        int bottom = imageYSetCenter(startButtonBitmap.getHeight()) + startButtonBitmap.getHeight();

        startButtonBitmap = Bitmap.createScaledBitmap(startButtonBitmap, 400, 200, false);
        canvas.drawBitmap(startButtonBitmap,  left, up, null);
        startButtonRect.set(left, up, right, bottom);

    }

    private void drawHelpButton(Canvas canvas)
    {
        int left = imageXSetCenter(helpButtonBitmap.getWidth());
        int up = imageYSetCenter(helpButtonBitmap.getHeight()) + 300;
        int right = imageXSetCenter(helpButtonBitmap.getWidth()) + helpButtonBitmap.getWidth();
        int bottom = imageYSetCenter(helpButtonBitmap.getHeight()) + helpButtonBitmap.getHeight() + 300;

        helpButtonBitmap = Bitmap.createScaledBitmap(helpButtonBitmap, 200, 200, false);
        canvas.drawBitmap(helpButtonBitmap,  left, up, null);
        helpButtonRect.set(left, up, right, bottom);

    }

    private void drawRectPaint(Canvas canvas, Rect rect)
    {
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);

        canvas.drawRect(rect, paint);
    }

    private void initial()
    {
        AssetManager am = this.getContext().getAssets();
        Typeface plain = Typeface.createFromAsset( am, "pfstardust.ttf" );

        mainTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mainTextPaint.setTypeface(Typeface.create( plain, Typeface.BOLD ));

        mainTextPaint.setTypeface(Typeface.createFromAsset(am, "pfstardust.ttf"));
        mainTextPaint.setTextSize(100);
        mainTextPaint.setColor(Color.WHITE);
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
