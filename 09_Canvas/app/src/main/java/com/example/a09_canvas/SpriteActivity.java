package com.example.a09_canvas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

public class SpriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sprite);

        Button bPreviousPage = findViewById(R.id.PreviousPage);
        bPreviousPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SpriteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Button bNext = findViewById(R.id.NextPage);
        bNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SurfaceView smt = findViewById(R.id.Smt);
                smt.onFinishTemporaryDetach();
                Intent intent = new Intent(SpriteActivity.this, ParticlesActivity.class);
                startActivity(intent);

            }
        });
    }
}