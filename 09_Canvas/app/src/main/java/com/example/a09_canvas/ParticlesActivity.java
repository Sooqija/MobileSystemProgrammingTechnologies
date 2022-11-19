package com.example.a09_canvas;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.tutorials.android.particles.CommonParticles;
import com.tutorials.android.particles.ParticlesGenerator;
import com.tutorials.android.particles.ParticlesManager;
import com.tutorials.android.particles.ParticlesSource;
import com.tutorials.android.particles.Utils;
import com.tutorials.android.particles.particles.BitmapParticles;
import com.tutorials.android.particles.particles.Particles;

import java.util.List;
import java.util.Random;

import me.ibrahimsn.particle.ParticleView;

public class ParticlesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particles_on_touch);

        ViewGroup main = findViewById(R.id.ParticlesOnTouch);

        main.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int x = (int)event.getX();
                int y = (int)event.getY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        final Bitmap allPossibleParticles = Utils.createCircleBitmap(Color.BLACK, 20);
                        final ParticlesGenerator particlesGenerator = new ParticlesGenerator() {
                            @Override
                            public Particles generateParticles(Random random) {
                                final Bitmap bitmap = allPossibleParticles;
                                return new BitmapParticles(bitmap);
                            }
                        };
                        final int containerMiddleX = x;
                        final int containerMiddleY = y;
                        final ParticlesSource particlesSource = new ParticlesSource(containerMiddleX, containerMiddleY);
                        float length = (float) Math.sqrt(180*180 + 180*180);
                        for(int i = 0; i < 360; i++) {
                            new ParticlesManager(ParticlesActivity.this, particlesGenerator, particlesSource, main)
                                    .setNumInitialCount(1)
                                    .setVelocityX((float) (length * Math.cos(i)))
                                    .setVelocityY((float) (length * Math.sin(i)))
                                    .animate();
                        }
                    }

                    case MotionEvent.ACTION_MOVE:
                    case MotionEvent.ACTION_UP:
                }
                return false;
            }
        });

        Button bPreviousPage = findViewById(R.id.PreviousPage);
        bPreviousPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParticlesActivity.this, SpriteActivity.class);
                startActivity(intent);
            }
        });

        Button bNext = findViewById(R.id.NextPage);
        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ParticlesActivity.this, FountainActivity.class);
                startActivity(intent);
            }
        });
    }
}