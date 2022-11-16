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


        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            final List<Bitmap> allPossibleParticles = Utils.generateParticlesBitmaps(new int[] { Color.BLACK }, 20 /* size */);
            // Alternatively, we provide some helper methods inside `Utils` to generate square, circle,
            // and triangle bitmaps.
            // Utils.generateParticlesBitmaps(new int[] { Color.BLACK }, 20 /* size */);

            final int numParticles = allPossibleParticles.size();
            final ParticlesGenerator particlesGenerator = new ParticlesGenerator() {
                @Override
                public Particles generateParticles(Random random) {
                    final Bitmap bitmap = allPossibleParticles.get(random.nextInt(numParticles));
                    return new BitmapParticles(bitmap);
                }
            };
//                CommonParticles.rainingParticles(main, new int[] {
//                        Color.BLACK,Color.BLUE,Color.GREEN,Color.RED,Color.YELLOW
//                }).oneShot();

                final int containerMiddleX = main.getWidth() / 2;
                final int containerMiddleY = main.getHeight() / 2;
                final ParticlesSource particlesSource = new ParticlesSource(containerMiddleX, containerMiddleY);
                ParticlesManager particlesManager = new ParticlesManager(ParticlesActivity.this, particlesGenerator, particlesSource, main)
                        .setEmissionDuration(1000)
                        .setEmissionRate(100)
                        .setVelocityX(20, 10)
                        .setVelocityY(100)
                        .setRotationalVelocity(180, 180)
                        .animate();
                particlesManager.setEmissionRate(100)
                        .animate();

                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        particlesManager.   setEmissionRate(20).animate();
                    }
                }, 3000);
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
                Intent intent = new Intent(ParticlesActivity.this, MusicActivity.class);
                startActivity(intent);
            }
        });
    }
}