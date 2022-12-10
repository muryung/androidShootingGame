package com.example.androidshootinggame.screen;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.androidshootinggame.R;

public class HelpScreen extends View {
    Bitmap backgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
    Bitmap windowBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.windoww);
    Bitmap guide1Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.guide1);
    Bitmap guide2Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.guide2);

    Paint textPaint;
    Paint mainTextPaint;

    public HelpScreen(Context context) {
        super(context);
    }

    public HelpScreen(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initial();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(backgroundBitmap, 0, 0, null);
        drawWindow(canvas);
        drawGuide(canvas);
        canvas.drawText("HELP", 430, 220, mainTextPaint);
        canvas.drawText("화면을 터치하면", 450, 550, textPaint);
        canvas.drawText("조이스틱이 활성화됩니다.", 450, 650, textPaint);
        canvas.drawText("적에겐 HP가 있고", 200, 1050, textPaint);
        canvas.drawText("자동 총알로 적을 처치할 수 있습니다.", 200, 1150, textPaint);
    }

    private void drawWindow(Canvas canvas)
    {
        windowBitmap = Bitmap.createScaledBitmap(windowBitmap, this.getWidth() - 200, this.getHeight() - 200, false);
        canvas.drawBitmap(windowBitmap, 100,100, null);
    }

    private void drawGuide(Canvas canvas)
    {
        canvas.drawBitmap(guide1Bitmap, 150,500, null);
        canvas.drawBitmap(guide2Bitmap, 600,1200, null);
    }

    private void initial()
    {
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        AssetManager am = this.getContext().getAssets();
        Typeface plain = Typeface.createFromAsset( am, "pfstardust.ttf" );
        textPaint.setTypeface(Typeface.create( plain, Typeface.BOLD ));

        textPaint.setTypeface(Typeface.createFromAsset(am, "pfstardust.ttf"));
        textPaint.setTextSize(50f);
        textPaint.setColor(Color.WHITE);

        mainTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mainTextPaint.setTypeface(Typeface.create( plain, Typeface.BOLD ));

        mainTextPaint.setTypeface(Typeface.createFromAsset(am, "pfstardust.ttf"));
        mainTextPaint.setTextSize(75f);
        mainTextPaint.setColor(Color.WHITE);
    }
}
