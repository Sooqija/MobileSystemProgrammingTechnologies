package com.example.a05_frame_stack

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView


class DragMenuActivity:AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag_menu)

        val drawerLayout = this.findViewById<DrawerLayout>(R.id.drawer_layout)
        val navigator = findViewById<NavigationView>(R.id.nav_view)
        navigator.bringToFront()
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val choice = findViewById<TextView>(R.id.Choice)
        navigator.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.DoAction -> {
                    choice.text = "Your Choice is Сделать дело"
                    return@setNavigationItemSelectedListener true}

                R.id.Walk -> {
                    choice.text = "Your Choice is Гулять Смело"
                    return@setNavigationItemSelectedListener true
                }
                R.id.Read -> {
                    choice.text = "Your Choice is Прочитать книгу"
                    return@setNavigationItemSelectedListener true
                }
                R.id.GotoSchool -> {
                    choice.text = "Your Choice is Сходить на учебу"
                    return@setNavigationItemSelectedListener true
                }
                else -> {
                    true
                }
            }
        }

        val previousPageButton = findViewById<Button>(R.id.PreviousPage)
        previousPageButton.setOnClickListener {
            val intent = Intent(this@DragMenuActivity, SlideShowActivity::class.java)
            startActivity(intent)
        }
        val nextPageButton = findViewById<Button>(R.id.NextPage)
        nextPageButton.setOnClickListener {
            val intent = Intent(this@DragMenuActivity, TopMenuActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            val choice = findViewById<TextView>(R.id.Choice)
            choice.text = "Your Choice is daСделать дело"
            return true
        }
        return false
    }

}
