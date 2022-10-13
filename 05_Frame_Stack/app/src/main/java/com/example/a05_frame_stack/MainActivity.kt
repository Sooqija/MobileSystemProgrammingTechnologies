package com.example.a05_frame_stack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.view.get
import androidx.core.view.iterator
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabs = findViewById<TabLayout>(R.id.tabLayout)

        val forwardButton = findViewById<Button>(R.id.Forward)
        val backwardButton = findViewById<Button>(R.id.Backward)
        val stackDepthInfo = findViewById<TextView>(R.id.StackDepth)
        val returnButton = findViewById<Button>(R.id.Return)
        val attachButton = findViewById<Button>(R.id.Attach)
        val detachButton = findViewById<Button>(R.id.Detach)
        val nextPageButton = findViewById<Button>(R.id.NextPage)

        val mainTab = tabs.newTab()
        mainTab.setText("Main")
        tabs.addTab(mainTab, true)

        returnButton.visibility = View.INVISIBLE
        val newTab = tabs.newTab()
        newTab.setText("Attached Page")
        newTab.setTabLabelVisibility(TabLayout.TAB_LABEL_VISIBILITY_UNLABELED)
        tabs.addTab(newTab, false)


        // Вкладки
        attachButton.setOnClickListener {
            newTab.setTabLabelVisibility(TabLayout.TAB_LABEL_VISIBILITY_LABELED)
            newTab.select()
            forwardButton.visibility = View.INVISIBLE
            backwardButton.visibility = View.INVISIBLE
            stackDepthInfo.visibility = View.INVISIBLE
            detachButton.visibility = View.INVISIBLE
            attachButton.visibility = View.INVISIBLE
            nextPageButton.visibility = View.INVISIBLE
            returnButton.visibility = View.VISIBLE
        }
        detachButton.setOnClickListener {
            newTab.setTabLabelVisibility(TabLayout.TAB_LABEL_VISIBILITY_UNLABELED)
        }
        returnButton.setOnClickListener {
            mainTab.select()
            forwardButton.visibility = View.VISIBLE
            backwardButton.visibility = View.VISIBLE
            stackDepthInfo.visibility = View.VISIBLE
            detachButton.visibility = View.VISIBLE
            attachButton.visibility = View.VISIBLE
            nextPageButton.visibility = View.VISIBLE
            returnButton.visibility = View.INVISIBLE
        }

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
            val intent = Intent(this@MainActivity, DialogActivity::class.java)
            startActivity(intent)
        }
    }
}