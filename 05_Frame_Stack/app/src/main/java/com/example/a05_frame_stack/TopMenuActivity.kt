package com.example.a05_frame_stack

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

class TopMenuActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_menu)

        val tinkerChoice = findViewById<Button>(R.id.Tinker)
        val oracleChoice = findViewById<Button>(R.id.Oracle)
        val arcWardenChoice = findViewById<Button>(R.id.ArcWarden)
        val dazzleChoice = findViewById<Button>(R.id.Dazzle)

        val yourChoice = findViewById<TextView>(R.id.Choice)
        tinkerChoice.setOnClickListener {
            val str = "Your Choice is Tinker"
            yourChoice.text = str
        }
        oracleChoice.setOnClickListener {
            val str = "Your Choice is Oracle"
            yourChoice.text = str
        }
        arcWardenChoice.setOnClickListener {
            val str = "Your Choice is Arc Warden"
            yourChoice.text = str
        }
        dazzleChoice.setOnClickListener {
            val str = "Your Choice is Dazzle"
            yourChoice.text = str
        }
        val previousPageButton = findViewById<Button>(R.id.PreviousPage)
        previousPageButton.setOnClickListener {
            val intent = Intent(this@TopMenuActivity, DragMenuActivity::class.java)
            startActivity(intent)
        }
        val nextPageButton = findViewById<Button>(R.id.NextPage)
        nextPageButton.setOnClickListener {
            val intent = Intent(this@TopMenuActivity, ContextMenuActivity::class.java)
            startActivity(intent)
        }
    }
}