package com.example.a02_interface_construction

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView

class AnimationActivity: Activity() {

    private lateinit var NextPage_Button: Button
    private lateinit var PreviousPage_Button: Button
    private lateinit var Animated_Shape: TextView
    private lateinit var Animated_Rectangle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)

        Animated_Shape = findViewById(R.id.BlackShape)
        Animated_Shape.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this@AnimationActivity, R.anim.my_animation)
            animation.setAnimationListener(object: Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {}
                    override fun onAnimationRepeat(animation: Animation?) {}
                    override fun onAnimationEnd(animation: Animation?) {
                        Animated_Shape.startAnimation(AnimationUtils.loadAnimation(this@AnimationActivity, R.anim.my_animation_undo))
                }
            })
            Animated_Shape.startAnimation(animation)
        }

        Animated_Rectangle = findViewById(R.id.BlackRectangle)
        Animated_Rectangle.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this@AnimationActivity, R.anim.rectangle_animation)
            val animation_undo = AnimationUtils.loadAnimation(this@AnimationActivity, R.anim.rectangle_animation_undo)
            animation.setAnimationListener(object: Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                    Animated_Rectangle.text = "Превращаюсь в квадрат"
                }
                override fun onAnimationRepeat(animation: Animation?) {}
                override fun onAnimationEnd(animation: Animation?) {
//                    Animated_Rectangle.text = "Теперь я квадрат :("
                    Thread.sleep(1000)
                    Animated_Rectangle.startAnimation(animation_undo)
                }
            })
            animation_undo.setAnimationListener(object: Animation.AnimationListener {
                override fun onAnimationStart(animation: Animation?) {
                    Animated_Rectangle.text = "Превращаюсь в прямоульник"
                }
                override fun onAnimationRepeat(animation: Animation?) {}
                override fun onAnimationEnd(animation: Animation?) {
                    Animated_Rectangle.text = "Теперь я снова прямоугольник"
                }
            })
            Animated_Rectangle.startAnimation(animation)
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