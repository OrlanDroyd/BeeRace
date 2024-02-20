package com.gmail.orlandroyd.beerace.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.gmail.orlandroyd.beerace.core.navigation.Screen
import com.gmail.orlandroyd.beerace.core.navigation.SetupNavGraph
import com.gmail.orlandroyd.beerace.core.ui.theme.BeeRaceTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            BeeRaceTheme {
                val navController = rememberNavController()
                SetupNavGraph(
                    startDestination = Screen.HOME,
                    navController = navController
                )
            }
        }
    }
}