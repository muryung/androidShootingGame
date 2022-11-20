package com.example.androidshootinggame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.View;

public class JoyStickView extends View{

    private int backGroundCenterX, backGroundCenterY;
    private int touchX, touchY;

    private float joyStickBackGroundRatio = 0.75f;
    private float stickRatio = 0.25f;

    private float joyStickBackGroundRadius;
    private float stickRadius;


    public int joyStickPosX, joyStickPosY;

    Bitmap joyStickBgImg = BitmapFactory.decodeResource(getResources(), R.drawable.joysticksplitted);
    Bitmap joyStickImg = BitmapFactory.decodeResource(getResources(), R.drawable.smallhandlefilled);

    public JoyStickView(Context context, int touchX, int touchY) {
        super(context);
        this.touchX = touchX;
        this.touchY = touchY;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int joyStickBgImgWidth = joyStickBgImg.getWidth();
        int joyStickBgImgHeight = joyStickBgImg.getHeight();
        setMeasuredDimension(joyStickBgImgWidth, joyStickBgImgHeight);
    }


    private void initPosition() {
        backGroundCenterX = touchX;
        backGroundCenterY = touchY;
    }

    private void initRadius(int width, int height) {
        int d = Math.min(width, height);
        stickRadius = d / 2 * stickRatio;
        joyStickBackGroundRadius = d / 2 * joyStickBackGroundRatio;

    }

    // 시계 반대 방향으로 각도 구하기
    public int getAngle() {

        int angle = (int) Math.toDegrees(Math.atan2(backGroundCenterY - joyStickPosY, joyStickPosX - backGroundCenterX));
        // 안드로이드 뷰는 좌표계가 y축과 반대
        // atan2는 음수, 양수 따져서 라디안 값 반환
        // toDegrees는 라디안 값을 각도로 변환
        return angle < 0 ? angle + 360 : angle;
                      // -180 ~ 180을 360도로 바꾸기
    }

    // 버튼과 원점 사이의 거리
    public double getStrength() {
        return Math.sqrt((joyStickPosX - backGroundCenterX) * (joyStickPosX - backGroundCenterX) +
                (joyStickPosY - backGroundCenterY) * (joyStickPosY - backGroundCenterY));
    }

    public void keepStickOutCircle() {
        double length = getStrength();

        if (length > joyStickBackGroundRadius) {
            joyStickPosX = (int) ((joyStickPosX - backGroundCenterX) * joyStickBackGroundRadius / length + backGroundCenterX);
            joyStickPosY = (int) ((joyStickPosY - backGroundCenterY) * joyStickBackGroundRadius / length + backGroundCenterY);
        }
    }


    public void JoyStickDraw(Canvas canvas)
    {
        initPosition();
        initRadius(joyStickBgImg.getWidth(), joyStickBgImg.getHeight());
        canvas.drawBitmap(joyStickBgImg, touchX - joyStickBgImg.getWidth() / 2 + joyStickImg.getWidth() / 2, touchY - joyStickBgImg.getHeight() / 2 + joyStickImg.getHeight() / 2, null);
        canvas.drawBitmap(joyStickImg, joyStickPosX, joyStickPosY,null);

    }




}
