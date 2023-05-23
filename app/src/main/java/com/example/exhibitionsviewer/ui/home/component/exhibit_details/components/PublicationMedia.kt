package com.example.exhibitionsviewer.ui.home.component.exhibit_details.components

import android.annotation.SuppressLint
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun PublicationMedia(requestUrl: String) {
    val context = LocalContext.current
    var webView by remember { mutableStateOf<WebView?>(null) }

        AndroidView(
        factory = {
            WebView(context).apply {
                settings.javaScriptEnabled = true
                settings.builtInZoomControls = true;
                settings.supportZoom();
                webChromeClient = WebChromeClient()
                webViewClient = WebViewClient()
                webView = this
                webView?.loadUrl(requestUrl)
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
}