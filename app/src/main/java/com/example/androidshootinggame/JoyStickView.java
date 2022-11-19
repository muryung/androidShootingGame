package com.example.androidshootinggame;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class JoyStickView extends View implements Runnable{
    private int backGroundColor;
    private int stickColor;
    private boolean useSpring;

    private int backGroundCenterX, backGroundCenterY;
    private int touchPosX, touchPosY;
    private int loopInterval = 10;

    private float backGroundRatio;
    private float stickRatio;

    private float backGroundRadius;
    private float stickRadius;

    private Paint backGroundPaint = new Paint();
    private Paint stickPaint = new Paint();

    private Thread thread = new Thread(this);



    public interface OnMoveListener {
        void onMove(int angle, int strength);
    }
    private OnMoveListener moveListener;

    public JoyStickView(Context context) {
        super(context);

    }

    public JoyStickView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.Joystick, 0, 0);
        try {
            backGroundColor = styledAttributes.getColor(R.styleable.Joystick_joystickBaseColor, Color.YELLOW);
            stickColor = styledAttributes.getColor(R.styleable.Joystick_joystickStickColor, Color.BLUE);
            backGroundRatio = styledAttributes.getFraction(R.styleable.Joystick_joystickBaseRatio, 1, 1, 0.75f);
            stickRatio = styledAttributes.getFraction(R.styleable.Joystick_joystickStickRatio, 1, 1, 0.25f);
            useSpring = styledAttributes.getBoolean(R.styleable.Joystick_joystickUseSpring, true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            styledAttributes.recycle();
        }

        backGroundPaint.setAntiAlias(true);
        backGroundPaint.setColor(backGroundColor);
        backGroundPaint.setStyle(Paint.Style.FILL);

        stickPaint.setAntiAlias(true);
        stickPaint.setColor(stickColor);
        stickPaint.setStyle(Paint.Style.FILL);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int d = Math.min(measure(widthMeasureSpec), measure(heightMeasureSpec));
        setMeasuredDimension(d, d);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        initPosition();
        initRadius(w, h);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        touchPosX = (int) event.getX();
        touchPosY = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (thread.isAlive())
                    thread.interrupt();

                thread = new Thread(this);
                thread.start();


                if (moveListener != null) {
                    moveListener.onMove(getAngle(), getStrength());
                }

                break;

            case MotionEvent.ACTION_UP:
                thread.interrupt();
                resetStickPosition();

                if (moveListener != null)
                    moveListener.onMove(getAngle(), getStrength());
                break;

            default:
                break;
        }
        keepStickOutCircle();
        invalidate();

        return true;
    }

    private void initPosition() {
        backGroundCenterX = touchPosX = (getWidth() / 2);
        backGroundCenterY = touchPosY = (getWidth() / 2);
    }

    private void initRadius(int width, int height) {
        int d = Math.min(width, height);
        stickRadius = (d / 2 * stickRatio);
        backGroundRadius = (d / 2 * backGroundRatio);

    }

    // 시계 반대 방향으로 각도 구하기
    private int getAngle() {
        int angle = (int) Math.toDegrees(Math.atan2(backGroundCenterX - touchPosY, touchPosX - backGroundCenterX));
        // 안드로이드 뷰는 좌표계가 y축과 반대
        // atan2는 음수, 양수 따져서 라디안 값 반환
        // toDegrees는 라디안 값을 각도로 변환
        return angle < 0 ? angle + 360 : angle;
                      // -180 ~ 180을 360도로 바꾸기
    }

    // 버튼과 원점 사이의 거리
    private int getStrength() {
        return (int) (100 * Math.sqrt((touchPosX - backGroundCenterX) * (touchPosX - backGroundCenterX) +
                        (touchPosY - backGroundCenterY) * (touchPosY - backGroundCenterY)) / backGroundRadius);
    }

    private void resetStickPosition() {
        touchPosX = backGroundCenterX;
        touchPosY = backGroundCenterY;
    }

    // 터치가 원 밖으로 나가도 스틱은 원 안에 유지
    private void keepStickOutCircle() {
        double length = Math.sqrt((touchPosX - backGroundCenterX) * (touchPosX - backGroundCenterX) +
                (touchPosY - backGroundCenterY) * (touchPosY - backGroundCenterY));

        if (length > backGroundRadius) {
            touchPosX = (int) ((touchPosX - backGroundCenterX) * backGroundRadius / length + backGroundCenterX);
            touchPosY = (int) ((touchPosY - backGroundCenterY) * backGroundRadius / length + backGroundCenterY);
        }
    }

    private int measure(int measureSpec) {
        if (MeasureSpec.getMode(measureSpec) == MeasureSpec.UNSPECIFIED) {
            return 200;
        } else {
            return MeasureSpec.getSize(measureSpec);
        }
    }

    public void setOnMoveListener(OnMoveListener l, int loopInterval) {
        moveListener = l;
        this.loopInterval = loopInterval;
    }

    public void JoyStickDraw(Canvas canvas)
    {

        canvas.drawCircle(getWidth() / 2, getWidth() / 2, backGroundRadius, backGroundPaint);   //배경 원 위치
        canvas.drawCircle(touchPosX, touchPosY, stickRadius, stickPaint);                              //스틱 위치
    }


    int playerSpeed = 5;
    @Override
    public void run() {
        while (!Thread.interrupted()) {
            post(() -> {
                Player.playerX += Math.cos(Math.toRadians(getAngle())) * playerSpeed;
                Player.playerY -= Math.sin(Math.toRadians(getAngle())) * playerSpeed;
                if (moveListener != null)
                    moveListener.onMove(getAngle(), getStrength());
            });
            try {
                Thread.sleep(loopInterval);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
