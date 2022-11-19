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

public class FountainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particles_on_touch);

        ViewGroup main = findViewById(R.id.ParticlesOnTouch);

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            final Bitmap allPossibleParticles = Utils.createCircleBitmap(Color.BLACK, 20);
            final ParticlesGenerator particlesGenerator = new ParticlesGenerator() {
                @Override
                public Particles generateParticles(Random random) {
                    final Bitmap bitmap = allPossibleParticles;
                    return new BitmapParticles(bitmap);
                }
            };

            final int containerMiddleX = main.getWidth() / 2;
            final int containerMiddleY = main.getHeight() *3/4;
            final ParticlesSource particlesSource = new ParticlesSource(containerMiddleX, containerMiddleY);

            ParticlesManager particlesManager = new ParticlesManager(FountainActivity.this, particlesGenerator, particlesSource, main)
                    .setEmissionDuration(3000)
                    .setEmissionRate(100)
                    .setVelocityX(0)
                    .setVelocityY(-720)
                    .setAccelerationX(0, 100)
                    .setAccelerationY(400)
                    .setRotationalVelocity(180, 180)
                    .animate();
            }
        });

        Button bPreviousPage = findViewById(R.id.PreviousPage);
        bPreviousPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FountainActivity.this, ParticlesActivity.class);
                startActivity(intent);
            }
        });

        Button bNext = findViewById(R.id.NextPage);
        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FountainActivity.this, MusicActivity.class);
                startActivity(intent);
            }
        });
    }
}