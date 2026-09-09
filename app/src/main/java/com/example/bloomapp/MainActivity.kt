package com.example.bloomapp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.bloomapp.data.ads.AdManager
import com.example.bloomapp.data.datastore.OnboardingDataStore
import com.example.bloomapp.ui.navigation.NavGraph
import com.example.bloomapp.ui.navigation.Screen
import com.example.bloomapp.ui.theme.BloomAppTheme
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var onboardingDataStore: OnboardingDataStore

    @Inject
    lateinit var adManager: AdManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Register App Lifecycle observer for App Open Ads
        adManager.registerLifecycleObserver()

        setContent {
            BloomAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val isOnboardingCompletedState by onboardingDataStore.isOnboardingCompleted
                        .collectAsState(initial = null)

                    when (val isCompleted = isOnboardingCompletedState) {
                        null -> {
                            // Loading State while reading DataStore
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator()
                            }
                        }
                        else -> {
                            // Update AdManager context for returning users
                            adManager.isOnboardingCompleted = isCompleted

                            val navController = rememberNavController()
                            val startDestination = if (isCompleted) {
                                Screen.Home.route
                            } else {
                                Screen.Onboarding.route
                            }

                            NavGraph(
                                navController = navController,
                                startDestination = startDestination
                            )
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Attempt to present App Open Ad when returning to foreground
        adManager.showAppOpenAdIfAvailable(this)
    }
}