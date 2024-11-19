package com.example.exam_mobile_interfaces;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView sunView;
    private FrameLayout skyView;
    private View seaView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sunView = findViewById(R.id.sunView);
        skyView = findViewById(R.id.skyView);
        seaView = findViewById(R.id.seaView);

        // Icon configuration for the toolbar.
        // This is all the configuration for making the traveling between views.
        ImageView iconNavigate = findViewById(R.id.moon);
        iconNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        ImageView iconBack = findViewById(R.id.face);
        iconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        // Starts the animation.
        skyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSunsetAnimation();
            }
        });
    }

    private void startSunsetAnimation() {
        // Calculates the position when the sun is fully covered.
        float seaTop = seaView.getTop();
        float sunFinalPosition = seaTop - sunView.getHeight() / 2f;

        // Sun starting position.
        float sunInitialPosition = sunView.getTop();

        // Resets the sun position.
        if (sunView.getTranslationY() != 0) {
            sunView.setTranslationY(0);
        }

        // Sun animation.
        ObjectAnimator sunAnimator = ObjectAnimator.ofFloat(sunView, "translationY", sunFinalPosition);
        sunAnimator.setDuration(3000);

        // Color change animation. ( day, midday, night )
        int startColor = Color.parseColor("#87CEEB");
        int midColor = Color.parseColor("#FF8C00");
        int endColor = Color.parseColor("#191970");

        ValueAnimator skyAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), startColor, midColor, endColor);
        skyAnimator.setDuration(4500);
        skyAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                skyView.setBackgroundColor((int) animation.getAnimatedValue());
            }
        });

        // Starts the animations.
        sunAnimator.start();
        skyAnimator.start();
    }
}