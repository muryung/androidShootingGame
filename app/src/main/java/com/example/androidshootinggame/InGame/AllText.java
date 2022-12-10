package com.example.androidshootinggame.InGame;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;

import androidx.annotation.Nullable;

public class AllText extends View {
    Paint remainEnemyTextStyle;
    Display display =  ((Activity) getContext()).getWindowManager().getDefaultDisplay();
    Point displaySize = new Point();

    private final int FONT_SIZE = 60;

    public AllText(Context context) {
        super(context);
        display.getRealSize(displaySize);

        setRemainEnemyTextStyle();
    }

    public AllText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    private void setRemainEnemyTextStyle()
    {
        remainEnemyTextStyle = new Paint(Paint.ANTI_ALIAS_FLAG);
        remainEnemyTextStyle.setColor(Color.rgb(153,50,204));
        remainEnemyTextStyle.setTextSize(FONT_SIZE);
    }

    public void drawRemainEnemyText(Canvas canvas)
    {
//        canvas.drawText("다음 스테이지", 0, displaySize.y - 600, remainEnemyTextStyle);
//        canvas.drawText("잡은 몬스터 수 : " + ContactObject.enemyCount, 0, displaySize.y - 500, remainEnemyTextStyle);
    }
}
