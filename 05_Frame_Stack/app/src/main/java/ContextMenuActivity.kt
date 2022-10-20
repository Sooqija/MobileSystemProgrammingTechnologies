package com.example.a05_frame_stack

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ContextMenuActivity: AppCompatActivity() {
    lateinit var menu: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_context_menu)

        var listItems = arrayOf("Сделать дело" + "\n" + "Гулять смело", "Прочитать книну", "Сходить на учебу")
        var dateItems = arrayOf("17.02.2017", "16.02.2017", "15.02.2017")

        val listView = findViewById<ListView>(R.id.DoList)
        val adapter = MyAdapter(this@ContextMenuActivity, R.layout.list_item, listItems, dateItems)
        listView.adapter = adapter

        menu = findViewById(R.id.Menu)
        registerForContextMenu(menu)

        val previousPageButton = findViewById<Button>(R.id.PreviousPage)
        previousPageButton.setOnClickListener {
            val intent = Intent(this@ContextMenuActivity, TopMenuActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.nav_menu, menu)
    }

   override fun onContextItemSelected(item: MenuItem): Boolean {
       val id = item.itemId
       when (id) {
           R.id.DoAction -> {
               menu.text = "Сделать дело 0"
           }
           R.id.Walk -> {
               menu.text = "Гулять смело 0"
           }
           R.id.Read -> {
               menu.text = "Прочитать книгу 1"
           }
           R.id.GotoSchool -> {
               menu.text = "Сходить на учебу 2"
           }
       }
       return super.onContextItemSelected(item)
    }
}
