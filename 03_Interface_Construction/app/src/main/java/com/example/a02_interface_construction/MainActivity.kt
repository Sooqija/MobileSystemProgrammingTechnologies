package com.example.a02_interface_construction

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button

class MainActivity : Activity() {

    private lateinit var NextPageButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NextPageButton = findViewById(R.id.NextPage)
        NextPageButton.setOnClickListener {
            val intent = Intent(this@MainActivity, SquaresActivity::class.java)
            startActivity(intent)
        }
    }
}

//            Intent(this@MainActivity, OneActivity::class.java).also {
//                startActivity(it)
//            }