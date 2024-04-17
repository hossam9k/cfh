package com.example.cfh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.toArgb
import com.example.cfh.home.utils.DrawerCompose
import com.example.cfh.ui.theme.CFHTheme
import com.example.cfh.ui.theme.gray
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = gray.toArgb()
        window.navigationBarColor = gray.toArgb()
        setContent {
            CFHTheme {
                DrawerCompose()
            }
        }
    }
}
