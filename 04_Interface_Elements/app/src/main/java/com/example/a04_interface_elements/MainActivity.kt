package com.example.a04_interface_elements

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var pressedButton: Button
    private lateinit var nextPageButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pressedButton = findViewById(R.id.PressedButton)
        pressedButton.setOnClickListener {
            pressedButton.setBackgroundResource(R.drawable.pressed_button)
        }
        nextPageButton = findViewById(R.id.NextPage)
        nextPageButton.setOnClickListener {
            val intent = Intent(this@MainActivity, PressedOrReleasedActivity::class.java)
            startActivity(intent)
        }
    }
}