package com.example.a02_interface_construction

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button

class SquaresActivity: Activity() {
    private lateinit var NextPage_Button: Button
    private lateinit var PreviousPage_Button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_squares)
        NextPage_Button = findViewById(R.id.NextPage)
        NextPage_Button.setOnClickListener {
            val intent = Intent(this@SquaresActivity, AnimationActivity::class.java)
            startActivity(intent)
        }
        PreviousPage_Button = findViewById(R.id.PreviousPage)
        PreviousPage_Button.setOnClickListener {
            val intent = Intent(this@SquaresActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}