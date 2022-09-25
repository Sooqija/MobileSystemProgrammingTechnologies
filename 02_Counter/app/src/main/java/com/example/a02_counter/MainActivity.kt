package com.example.a02_counter

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

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

        Score = findViewById(R.id.textView)

    }
}