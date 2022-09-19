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

       webView.settings.javaScriptEnabled = true  // 자바스크립트 허용
//        웹뷰에서 새 창이 뜨지 않도록 방지하는 구분 //
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()

        webView.loadUrl(getUrl.toString())






    }


}