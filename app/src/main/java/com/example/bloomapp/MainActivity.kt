package com.example.bloomapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.bloomapp.ui.navigation.NavGraph
import com.example.bloomapp.ui.navigation.Screen
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Safe AdMob initialization
        try {
            MobileAds.initialize(this) {}
        } catch (e: Exception) {
            e.printStackTrace()
        }

        setContent {
            val navController = rememberNavController()

            NavGraph(
                navController = navController,
                startDestination = Screen.Onboarding.route
            )
        }
    }
}