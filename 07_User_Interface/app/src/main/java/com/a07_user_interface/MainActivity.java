package com.a07_user_interface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {
    int light_id = 2;
    boolean isGreen = true;
    boolean flag_plus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView redLight = findViewById(R.id.RedLight);
        TextView yellowLight = findViewById(R.id.YellowLight);
        TextView greenLight = findViewById(R.id.GreenLight);
        ImageView Human = findViewById(R.id.Human);

        TextView[] lights = {redLight, yellowLight, greenLight};
        int[]  lightsSource = {R.drawable.red_light, R.drawable.yellow_light, R.drawable.green_light};
        Thread thread = new Thread(() -> {
            while (true)  {
                if (light_id == 2) {
                    if (isGreen) {
                        Animation forwardAnim = AnimationUtils.loadAnimation(this, R.anim.human_go_forward);
                        Human.startAnimation(forwardAnim);
                    } else {
                        Animation backwardAnim = AnimationUtils.loadAnimation(this, R.anim.human_go_backward);
                        Human.startAnimation(backwardAnim);
                    }
                    flag_plus = false;
                } else if (light_id == 0) {
                    flag_plus = true;
                }
                lights[light_id].setBackgroundResource(lightsSource[light_id]);
                if (light_id == 0) {
                    if (isGreen) {
                        Human.setRotationY(180);
                        isGreen = false;
                    }
                    else {
                        Human.setRotationY(0);
                        isGreen = true;
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lights[light_id].setBackgroundResource(R.drawable.circle);

                if (flag_plus)
                    light_id++;
                else light_id--;
            }
        });
        thread.start();


        Button bNextPage = findViewById(R.id.NextPage);
        bNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TextMotion.class);
                startActivity(intent);
            }
        });
    }
}