package com.a07_user_interface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class Stopwatch extends AppCompatActivity {
    int second = 0;
    int minute = 0;
    int hour = 0;
    boolean isStart = true;
    Timer Stopwatch = new Timer();
    int second_rotation = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activit_stopwatch);

        Button bStart = findViewById(R.id.Start);
        TextView tStopwatch = findViewById(R.id.Stopwatch);
        CustomAnalogClock clock = findViewById(R.id.analog_clock);
        clock.setTime(1000*60*60*(-3));
        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isStart) {
                    Stopwatch = new Timer();
                    Stopwatch.schedule((TimerTask) (new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread((Runnable) (new Runnable() {
                                public void run() {
                                    String time = (hour < 10) ? "0" + String.valueOf(hour) + ":" : String.valueOf(hour) + ":";
                                    time += (minute < 10) ? "0" + String.valueOf(minute) + ":" : String.valueOf(minute) + ":";
                                    time += (second < 10) ? "0" + String.valueOf(second) : String.valueOf(second);
                                    second++;
                                    hour = (hour + (minute + second / 60) / 60) % 24;
                                    minute = (minute + second / 60) % 60;
                                    second %= 60;
                                    tStopwatch.setText(time);
                                    clock.setTime(1000*60*60*(-3) + 60*1000*(second-1) + minute*5*1000*60);
                                }
                            }));
                        }
                    }), 0L, 1000L);
                    isStart = false;
                } else {
                    Stopwatch.cancel();
                    isStart = true;
                }
            }
        });
        Button bPreviousPage = findViewById(R.id.PreviousPage);
        bPreviousPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Stopwatch.this, ButtonsBegin.class);
                startActivity(intent);
            }
        });
        Button bNext = findViewById(R.id.NextPage);
        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Stopwatch.this, PageStack.class);
                startActivity(intent);
            }
        });
    }
}
