package com.example.androidshootinggame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Bullet extends View {
    Bitmap bulletImg = BitmapFactory.decodeResource(getResources(), R.drawable.bullet);

    public ArrayList<Bullet> bullets = new ArrayList<>();

    int bulletX, bulletY;
    int bulletSpeed = 30;        // 총알의 속도

    int bulletWidth = bulletImg.getWidth();
    int bulletHeight = bulletImg.getHeight();


    public Bullet(Context context) {
        super(context);
        bulletX = Player.playerX + 100;
        bulletY = Player.playerY - 30;
    }

    public Bullet(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    long nextFire = 0;
    long fireRecoilTime = 200;      // 총알 생성 속도

    public void instanceBullet(Context context) {
        if (System.currentTimeMillis() > nextFire)
        {
            nextFire = System.currentTimeMillis() + fireRecoilTime;
            Bullet bullet = new Bullet(context);
            bullets.add(bullet);
        }

    }


    public void drawBullet(Canvas canvas) {
        for (int i = 0; i < bullets.size(); i++) {
            Bullet tmpBullet = bullets.get(i);

            if (tmpBullet != null) {
                canvas.drawBitmap(tmpBullet.bulletImg, tmpBullet.bulletX, tmpBullet.bulletY, null);

                tmpBullet.fire();
                removeBulletOutDisplay(tmpBullet, i);
            }
        }
    }

    private void removeBulletOutDisplay(Bullet bullet, int i)
    {
        if (bullet.bulletY < 0) {
            bullets.remove(i);
        }
    }

    private void fire() {
        bulletY -= bulletSpeed;
    }
}

