package com.example.a05_frame_stack

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.helper.widget.Carousel

class SlideShowActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slideshow)

        val previousPageButton = findViewById<Button>(R.id.PreviousPage)
        previousPageButton.setOnClickListener {
            val intent = Intent(this@SlideShowActivity, WebActivity::class.java)
            startActivity(intent)
        }
        val nextPageButton = findViewById<Button>(R.id.NextPage)
        nextPageButton.setOnClickListener {
            val intent = Intent(this@SlideShowActivity, DragMenuActivity::class.java)
            startActivity(intent)
        }
    }
}