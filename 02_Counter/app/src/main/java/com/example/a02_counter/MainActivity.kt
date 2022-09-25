package com.example.a02_counter

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.tan

class MainActivity : AppCompatActivity() {

    private lateinit var Score: TextView
    private lateinit var Refresh_Button: Button
    private lateinit var Increase_Button: Button
    private lateinit var Decrease_Button: Button
    private lateinit var Power_Button: Button
    private lateinit var Sin_Button: Button
    private lateinit var Cos_Button: Button
    private lateinit var Tan_Button: Button
    private lateinit var Cot_Button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)

        var counter = 0.0
        Score = findViewById(R.id.textView2)
        Refresh_Button = findViewById(R.id.RefreshButton)
        Refresh_Button.setOnClickListener {
            counter = 0.0
            Score.text = counter.toString()
        }
        Increase_Button = findViewById(R.id.IncreaseButton)
        Increase_Button.setOnClickListener {
            counter++
            Score.text = counter.toString()
        }
        Decrease_Button = findViewById(R.id.DecreaseButton)
        Decrease_Button.setOnClickListener {
            counter--
            Score.text = counter.toString()
        }
        Power_Button = findViewById(R.id.PowerButton)
        Power_Button.setOnClickListener {
            counter = counter.pow(2)
            Score.text = counter.toString()
        }
        Sin_Button = findViewById(R.id.SinButton)
        Sin_Button.setOnClickListener {
            counter = sin(counter)
            Score.text = counter.toString()
        }
        Cos_Button = findViewById(R.id.CosButton)
        Cos_Button.setOnClickListener {
            counter = cos(counter)
            Score.text = counter.toString()
        }
        Tan_Button = findViewById(R.id.TanButton)
        Tan_Button.setOnClickListener {
            counter = tan(counter)
            Score.text = counter.toString()
        }
        Cot_Button = findViewById(R.id.CotButton)
        Cot_Button.setOnClickListener {
            counter = 1 / tan(counter)
            Score.text = counter.toString()
        }
    }
}