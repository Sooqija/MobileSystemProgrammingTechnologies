package com.example.a05_frame_stack

import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class WebActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        val webView = findViewById<WebView>(R.id.WebView)
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("https://www.webtoons.com/en/romance/maybe-meant-to-be/list?title_no=4208")
        webView.webViewClient = WebViewClient()

        val previousPageButton = findViewById<Button>(R.id.PreviousPage)
        previousPageButton.setOnClickListener {
            val intent = Intent(this@WebActivity, ListActivity::class.java)
            startActivity(intent)
        }
        val nextPageButton = findViewById<Button>(R.id.NextPage)
        nextPageButton.setOnClickListener {
            val intent = Intent(this@WebActivity, SlideShowActivity::class.java)
            startActivity(intent)
        }
    }
}
