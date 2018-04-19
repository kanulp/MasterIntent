package com.kanu_lp.masterintentlib

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.webview.*

class WebView : AppCompatActivity(){

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.webview)
//add apptheme name

        var b : Bundle = intent.extras
        val url : String = b.getString("url")

        webview.settings.javaScriptEnabled = true
        webview.settings.setSupportZoom(false)
        webview.webViewClient = MyWebViewClient()
        webview.loadUrl(url)
    }

    class MyWebViewClient : WebViewClient() {
         fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
            return true
        }
    }

    override fun onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
