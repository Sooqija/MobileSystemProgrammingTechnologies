package com.example.a05_frame_stack

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ListActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        var listItems = arrayOf("Сделать дело" + "\n" + "Гулять смело", "Прочитать книну", "Сходить на учебу")
        var dateItems = arrayOf("17.02.2017", "16.02.2017", "15.02.2017")

        val listView = findViewById<ListView>(R.id.DoList)
        val adapter = MyAdapter(this@ListActivity, R.layout.list_item, listItems, dateItems)
        listView.adapter = adapter

        val previousPageButton = findViewById<Button>(R.id.PreviousPage)
        previousPageButton.setOnClickListener {
            val intent = Intent(this@ListActivity, DateActivity::class.java)
            startActivity(intent)
        }
        val nextPageButton = findViewById<Button>(R.id.NextPage)
        nextPageButton.setOnClickListener {
            val intent = Intent(this@ListActivity, WebActivity::class.java)
            startActivity(intent)
        }
    }
}