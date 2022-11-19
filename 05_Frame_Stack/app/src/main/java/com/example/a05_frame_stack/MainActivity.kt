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

var count = 0

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val forwardButton = findViewById<Button>(R.id.Forward)
        val backwardButton = findViewById<Button>(R.id.Backward)
        val nextPageButton = findViewById<Button>(R.id.NextPage)
        val stackDepthInfo = findViewById<TextView>(R.id.StackDepth)

        var stackDepth = intent.getIntExtra("stackDepth", 0)
        stackDepthInfo.text = "Stack Depth: " + stackDepth.toString()
        backwardButton.setOnClickListener {
            if (stackDepth != 0) {
                val intent = Intent(this@MainActivity, MainActivity::class.java)
                stackDepth--
                intent.putExtra("stackDepth", stackDepth)
                startActivity(intent)
            }
        }
        forwardButton.setOnClickListener {
            val intent = Intent(this@MainActivity, MainActivity::class.java)
            stackDepth++
            intent.putExtra("stackDepth", stackDepth)
            startActivity(intent)
        }
        nextPageButton.setOnClickListener {
            val intent = Intent(this@MainActivity, AttachPageActivity::class.java)
            startActivity(intent)
        }

//        val mainTab = tabs.newTab()
//        mainTab.setText("Main")
//        tabs.addTab(mainTab, true)
//
//        returnButton.visibility = View.INVISIBLE
//        val newTab = tabs.newTab()
//        newTab.setText("Attached Page")
//        newTab.setTabLabelVisibility(TabLayout.TAB_LABEL_VISIBILITY_UNLABELED)
//        tabs.addTab(newTab, false)
//
//
//        // Вкладки
//        attachButton.setOnClickListener {
//            newTab.setTabLabelVisibility(TabLayout.TAB_LABEL_VISIBILITY_LABELED)
//            newTab.select()
//            forwardButton.visibility = View.INVISIBLE
//            backwardButton.visibility = View.INVISIBLE
//            stackDepthInfo.visibility = View.INVISIBLE
//            detachButton.visibility = View.INVISIBLE
//            attachButton.visibility = View.INVISIBLE
//            nextPageButton.visibility = View.INVISIBLE
//            returnButton.visibility = View.VISIBLE
//        }
//        detachButton.setOnClickListener {
//            newTab.setTabLabelVisibility(TabLayout.TAB_LABEL_VISIBILITY_UNLABELED)
//            newTab
//        }
//        returnButton.setOnClickListener {
//            mainTab.select()
//            forwardButton.visibility = View.VISIBLE
//            backwardButton.visibility = View.VISIBLE
//            stackDepthInfo.visibility = View.VISIBLE
//            detachButton.visibility = View.VISIBLE
//            attachButton.visibility = View.VISIBLE
//            nextPageButton.visibility = View.VISIBLE
//            returnButton.visibility = View.INVISIBLE
//        }
//
//        var stackDepth = intent.getIntExtra("stackDepth", 0)
//        stackDepthInfo.text = "Stack Depth: " + stackDepth.toString()
//        backwardButton.setOnClickListener {
//            if (stackDepth != 0) {
//                val intent = Intent(this@MainActivity, MainActivity::class.java)
//                stackDepth--
//                intent.putExtra("stackDepth", stackDepth)
//                startActivity(intent)
//            }
//        }
//        forwardButton.setOnClickListener {
//            val intent = Intent(this@MainActivity, MainActivity::class.java)
//            stackDepth++
//            intent.putExtra("stackDepth", stackDepth)
//            startActivity(intent)
//        }
//        nextPageButton.setOnClickListener {
//            val intent = Intent(this@MainActivity, DialogActivity::class.java)
//            startActivity(intent)
//        }
    }
}