package com.example.a05_frame_stack

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class AttachPageActivity : AppCompatActivity() {

    private lateinit var tabs: TabLayout
    private lateinit var viewpager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attach_page)
        tabs = findViewById(R.id.tabLayout)
        tabs.setSelectedTabIndicatorColor(Color.BLUE)
//        tabs.setBackgroundColor(ContextCompat.getColor(this, com.google.android.material.R.color.design_default_color_primary_dark))
        val adapter = TabsPagerAdapter(supportFragmentManager, lifecycle, 2)
        viewpager = findViewById<ViewPager2>(R.id.tabs_viewpager)
        viewpager.adapter = adapter
        viewpager.isUserInputEnabled = true
        TabLayoutMediator(tabs, viewpager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Main"
                }
                1 -> {
                    tab.text = "For Delete"
                }
            }
            tab.icon?.colorFilter =
                BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                    Color.WHITE,
                    BlendModeCompat.SRC_ATOP
                )
        }.attach()
        tabs.getTabAt(1)?.let { tabs.removeTab(it) }
    }

    fun returnBack(view: View) {
        tabs.setScrollPosition(0, 0f, true)
        viewpager.setCurrentItem(0)
    }

    fun detachPage(view: View) {
        tabs.getTabAt(1)?.let { tabs.removeTab(it) }
    }

    fun attachPage(view: View) {
        if (tabs.tabCount == 1) {
            val tab = tabs.newTab()
            tab.setText("New Tab")
            tabs.addTab(tab, false)
            tab.select()
        }
    }

    fun nextPage(view: View) {
        val intent = Intent(this@AttachPageActivity, DialogActivity::class.java)
        startActivity(intent)
    }

    fun previousPage(view: View) {
        val intent = Intent(this@AttachPageActivity, MainActivity::class.java)
        startActivity(intent)
    }

}