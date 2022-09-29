package com.example.a02_interface_construction

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView

class AnimationActivity: Activity() {

    private lateinit var NextPage_Button: Button
    private lateinit var PreviousPage_Button: Button
    private lateinit var Animated_Shape: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)

        Animated_Shape = findViewById(R.id.BlackShape)
        Animated_Shape.setOnClickListener {
            Animated_Shape.startAnimation(AnimationUtils.loadAnimation(this, R.anim.my_animation))
        }

        NextPage_Button = findViewById(R.id.NextPage)
        NextPage_Button.setOnClickListener {
            val intent = Intent(this@AnimationActivity, DialogActivity::class.java)
            startActivity(intent)
        }

        PreviousPage_Button = findViewById(R.id.PreviousPage)
        PreviousPage_Button.setOnClickListener {
            val intent = Intent(this@AnimationActivity, SquaresActivity::class.java)
            startActivity(intent)
        }
    }
}