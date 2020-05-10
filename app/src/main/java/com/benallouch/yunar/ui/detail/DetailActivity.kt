package com.benallouch.yunar.ui.detail

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.benallouch.yunar.R
import com.benallouch.yunar.ui.EXTRA_ARTICLE_URL
import kotlinx.android.synthetic.main.details_activity.*

/**
 * WebView to display the details of the article (same pattern used by Google News App)
 */
class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupWebView()

        val url = intent.getStringExtra(EXTRA_ARTICLE_URL)
        url?.let { webView.loadUrl(it) }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        progressBar.max = 100
        webView.webViewClient = WebViewClientDemo(progressBar)
        webView.webChromeClient = WebChromeClientDemo(progressBar)
        webView.settings.javaScriptEnabled = true
    }

    private class WebViewClientDemo(progressBar: ProgressBar) : WebViewClient() {
        val progressBar = progressBar
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)
            progressBar.visibility = View.GONE
            progressBar.progress = 100
        }

        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            progressBar.visibility = View.VISIBLE
            progressBar.progress = 0
        }
    }

    private class WebChromeClientDemo(progressBar: ProgressBar) : WebChromeClient() {
        val progressBar = progressBar
        override fun onProgressChanged(view: WebView, progress: Int) {
            progressBar.progress = progress
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}