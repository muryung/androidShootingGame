package com.example.androidshootinggame.screen;

import static java.lang.Thread.sleep;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.androidshootinggame.InGame.AllText;
import com.example.androidshootinggame.InGame.Bullet;
import com.example.androidshootinggame.InGame.ContactObject;
import com.example.androidshootinggame.InGame.FirstEnemy;
import com.example.androidshootinggame.InGame.JoyStickView;
import com.example.androidshootinggame.InGame.Player;
import com.example.androidshootinggame.InGame.PlayerHeart;
import com.example.androidshootinggame.MainActivity;
import com.example.androidshootinggame.R;

public class InGameScreen extends View{
    Bitmap backgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
    private int loopInteval = 10;

    PlayerHeart playerHeart;
    Player player;
    JoyStickView joyStickView;
    Bullet bullet;
    FirstEnemy firstEnemy;
    ContactObject contect;

    Context context;
    AttributeSet attrs;
    AllText allText;

    public static boolean onThread = false;

    public InGameScreen(Context context) {
        super(context);
    }

    public InGameScreen(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.attrs = attrs;

        playerHeart = new PlayerHeart(context);
        player = new Player(context);
        bullet = new Bullet(context);
        firstEnemy = new FirstEnemy(context);
        allText = new AllText(context);
        contect = new ContactObject();

        thread.start();
    }

    int bgY = 0, bgMoveSpeed = 3;
    private void moveBackGround()
    {
        bgY += bgMoveSpeed;

        if (bgY > backgroundBitmap.getHeight())
            bgY = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(backgroundBitmap, 0, bgY, null);
        canvas.drawBitmap(backgroundBitmap, 0, -backgroundBitmap.getHeight() + bgY, null);

        playerHeart.HeartDraw(canvas);
        player.PlayerDraw(canvas);

        if (joyStickView != null)
            joyStickView.JoyStickDraw(canvas);

        if (bullet != null)
            bullet.drawBullet(canvas);

        if (firstEnemy != null)
            firstEnemy.drawEnemy(canvas);

        if (allText != null)
            allText.drawRemainEnemyText(canvas);

        player.PlayerLose(playerHeart);

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

    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (!Thread.interrupted())
            {
                if (onThread)
                {
                    player.PlayerMove(joyStickView, getWidth(), getHeight());
                    bullet.instanceBullet(context);
                    firstEnemy.instanceEnemy();
                    contect.enemyContactBullet(firstEnemy, bullet.bullets, player);
                    contect.enemyContactPlayer(firstEnemy, player, playerHeart);
                    moveBackGround();
                    invalidate();
                }
                else if (!onThread)
                {
                    if (player != null) {
                        player.playerX = 300;
                        player.playerY = 1200;
                    }
                    if (bullet.bullets != null)
                        bullet.bullets.clear();

                    if (firstEnemy.enemyArray != null)
                        firstEnemy.enemyArray.clear();

                    if (playerHeart.heartImgs.size() < 3)
                    {
                        playerHeart.heartImgs.clear();
                        playerHeart.AddPlayerHeart();
                    }
                }
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
