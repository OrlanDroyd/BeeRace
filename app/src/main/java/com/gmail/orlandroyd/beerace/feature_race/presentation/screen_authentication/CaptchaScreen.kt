package com.gmail.orlandroyd.beerace.feature_race.presentation.screen_authentication

import android.annotation.SuppressLint
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SetJavaScriptEnabled")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaptchaScreen(
    onClose: () -> Unit,
    isSuccess: (isSuccess: Boolean) -> Unit,
    captchaUrl: String = "https://www.google.com/recaptcha/api2/demo",
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "reCaptcha")
                }, navigationIcon = {
                    IconButton(onClick = { onClose() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                })
            Spacer(modifier = Modifier.height(8.dp))
        },
        content = {

            var code = 199 // TODO: Only for test, need to get code status after submit captcha

            AndroidView(factory = { context ->
                WebView(context).apply {
                    webChromeClient = object : WebChromeClient() {}
                    webViewClient = object : WebViewClient() {
                        override fun shouldOverrideUrlLoading(
                            view: WebView?,
                            request: WebResourceRequest?,
                        ): Boolean {
                            return false
                        }

                        override fun doUpdateVisitedHistory(
                            view: WebView?,
                            url: String?,
                            isReload: Boolean,
                        ) {

                            if (code == 200) {
                                isSuccess(true)
                            }

                            super.doUpdateVisitedHistory(view, url, isReload)
                        }
                    }

                    settings.javaScriptEnabled = true
                    loadUrl(captchaUrl)
                }
            })
        }
    )
}