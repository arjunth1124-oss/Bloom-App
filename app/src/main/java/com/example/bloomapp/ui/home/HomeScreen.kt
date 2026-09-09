package com.example.bloomapp.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bloomapp.ui.home.components.BannerAdView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Bloom Wellness") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Cycle Info Card
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Cycle Day 14", style = MaterialTheme.typography.headlineSmall)
                    Text("Fertile Phase • Low Ovulation Risk", style = MaterialTheme.typography.bodyMedium)
                }
            }

            // Daily Wellness Check-In Card
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Daily Log", style = MaterialTheme.typography.titleMedium)
                    Text("How are your symptoms today?", style = MaterialTheme.typography.bodySmall)
                }
            }

            // Adaptive Banner Ad Placement (Separated from core UI actions)
            BannerAdView()

            // Recommended PCOS Content
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("PCOS Article", style = MaterialTheme.typography.titleMedium)
                    Text("Top 5 Anti-Inflammatory Foods for PCOS", style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}