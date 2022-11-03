package com.a07_user_interface;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class TextMotion extends AppCompatActivity {
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_motion);
        TextView txt = findViewById(R.id.textView);
        txt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            @SuppressLint("ResourceType")
            public boolean onTouch(View view, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        txt.setTextColor(Color.parseColor(getString(R.color.blue)));
                        Animation descend = AnimationUtils.loadAnimation(TextMotion.this, R.anim.text_descend);
                        txt.startAnimation(descend);
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        Animation ascend = AnimationUtils.loadAnimation(TextMotion.this, R.anim.text_ascend);
                        txt.startAnimation(ascend);
                        txt.setTextColor(Color.parseColor(getString(R.color.black)));
                        break;
                    }
                }
                return true;
            }
        });

        Button bPreviousPage = findViewById(R.id.PreviousPage);
        bPreviousPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TextMotion.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Button bNextPage = findViewById(R.id.NextPage);
        bNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TextMotion.this, ButtonsBegin.class);
                startActivity(intent);
            }
        });
    }
}