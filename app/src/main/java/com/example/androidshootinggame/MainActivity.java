package com.example.androidshootinggame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

import com.example.androidshootinggame.screen.InGameScreen;
import com.example.androidshootinggame.screen.IntroScreen;

public class MainActivity extends AppCompatActivity {

    public static ViewFlipper viewFlipper;
    Thread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewFlipper = (ViewFlipper) findViewById(R.id.vFliper);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Thread.interrupted();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }


    @Override
    public void onBackPressed() {
        thread = new Thread();
        switch (viewFlipper.getDisplayedChild()) {
            case 3:
                viewFlipper.setDisplayedChild(0);
                break;
            case 2:
                viewFlipper.setDisplayedChild(0);
            break;
            case 1:
                viewFlipper.setDisplayedChild(0);
                InGameScreen.onThread = false;
            break;
        }
    }
}