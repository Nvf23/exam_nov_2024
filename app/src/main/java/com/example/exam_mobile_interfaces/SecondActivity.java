package com.example.exam_mobile_interfaces;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Icon configuration for the toolbar.
        // This is all the configuration for making the traveling between views.
        ImageView iconBack = findViewById(R.id.moon);
        iconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Fragment instances
        TopFragment topFragment = new TopFragment();
        MiddleFragment middleFragment = new MiddleFragment();
        BottomFragment bottomFragment = new BottomFragment();

        // Text listeners
        topFragment.setOnTextSendListener(new TopFragment.OnTextSendListener() {
            @Override
            public void onTextSend(String text, int size) {
                middleFragment.updateText(text, size);
            }
        });

        // Color listener
        bottomFragment.setOnColorSendListener(new BottomFragment.OnColorSendListener() {
            @Override
            public void onColorSend(int color) {
                middleFragment.updateTextColor(color);
            }
        });

        // Fragment loader
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentTop, topFragment);
        transaction.replace(R.id.fragmentMiddle, middleFragment);
        transaction.replace(R.id.fragmentBottom, bottomFragment);
        transaction.commit();
    }
}