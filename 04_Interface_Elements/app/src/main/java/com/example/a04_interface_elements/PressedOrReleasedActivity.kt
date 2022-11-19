package com.example.a04_interface_elements

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView

class PressedOrReleasedActivity : AppCompatActivity() {
    private lateinit var pressedOrReleasedButton: Button
    private lateinit var pressedOrReleasedText: TextView
    private lateinit var pressCounter: TextView
    private lateinit var nextPageButton: Button
    private lateinit var previousPageButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pressed_or_released)

        pressedOrReleasedButton = findViewById(R.id.PressedOrReleasedButton)
        pressedOrReleasedText = findViewById(R.id.PressedOrReleased)
        pressCounter = findViewById(R.id.PressCounter)

        var counter = 0

        pressedOrReleasedButton.setOnTouchListener(object: View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        pressedOrReleasedText.setText(R.string.pressed)
                    }
                    MotionEvent.ACTION_UP -> {
                        pressedOrReleasedText.setText(R.string.released)
                        pressCounter.text = "Number of clicks: " + (++counter).toString()
                    }
                }
                return v?.onTouchEvent(event) ?: true
            }
        })

        nextPageButton = findViewById(R.id.NextPage)
        nextPageButton.setOnClickListener {
            val intent = Intent(this@PressedOrReleasedActivity, DateActivity::class.java)
            startActivity(intent)
        }
        previousPageButton = findViewById(R.id.PreviousPage)
        previousPageButton.setOnClickListener {
            val intent = Intent(this@PressedOrReleasedActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}