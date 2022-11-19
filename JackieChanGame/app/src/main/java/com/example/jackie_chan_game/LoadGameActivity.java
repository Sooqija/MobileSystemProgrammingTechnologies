package com.example.jackie_chan_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LoadGameActivity extends AppCompatActivity {

    Button start_game;
    Button instraction;
    Button autors;
    Button exit;
    ImageView shadowenie;
    Boolean start_flag = false;
    Animation anim4;
    float plus = 0.0F;

    private void slololo() {
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                shadowenie.setAlpha(plus);
                plus += 0.01F;
                if (plus > 1.F) {
                    Intent intent_next = new Intent(LoadGameActivity.this, MainActivity.class);
                    startActivity(intent_next);
                    executorService.shutdownNow();
                }
            }
        }, 0, 30, TimeUnit.MILLISECONDS);
    }

//    private void recur(float plus) {
//        System.out.println("oo");
//        anim4 = AnimationUtils.loadAnimation(LoadGameActivity.this, R.anim.sleep666);
//        shadowenie.startAnimation(anim4);
//        anim4.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) { }
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                System.out.println("AAAA");
//                if (start_flag) {
//                    shadowenie.setAlpha(0.5F);
//                    System.out.println("AAAA");
//                    start_flag = false;
//                }
//                else shadowenie.setAlpha(0.9F);
//                if (plus != 0.99F) {
//                    shadowenie.setAlpha(plus);
//                    recur(plus + 0.11F);
//                } else {
//                    Intent intent_next = new Intent(LoadGameActivity.this, MainActivity.class);
//                    startActivity(intent_next);
//                }
//            }
//            @Override
//            public void onAnimationRepeat(Animation animation) { }
//        });
//    }

    private void goo() {
        start_game.setEnabled(false);
        autors.setEnabled(false);
        instraction.setEnabled(false);
        exit.setEnabled(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_game);

        start_game = findViewById(R.id.start_game);
        autors = findViewById(R.id.autors);
        instraction = findViewById(R.id.instraction);
        exit = findViewById(R.id.exit);
        shadowenie = findViewById(R.id.shadowenie);

        start_game.setEnabled(true);
        autors.setEnabled(true);
        instraction.setEnabled(true);
        exit.setEnabled(true);

        start_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start_flag = true;
                goo();
                slololo();
            }
        });
        autors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goo();
                Intent intent_next = new Intent(LoadGameActivity.this, RulesActivity.class);
                startActivity(intent_next);
            }
        });
        instraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_next = new Intent(LoadGameActivity.this, RulesActivity.class);
                startActivity(intent_next);
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadGameActivity.this.finish();
            }
        });
    }
}