package com.sangmin.firstaid

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CategoryShowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category_show)


        val getUrl = intent.getStringExtra("url")

        val webView : WebView = findViewById(R.id.webView)

        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            setSupportMultipleWindows(true)
        }

        webView.apply {
            webView.webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
            webView.loadUrl(getUrl.toString())


        }



    }
}