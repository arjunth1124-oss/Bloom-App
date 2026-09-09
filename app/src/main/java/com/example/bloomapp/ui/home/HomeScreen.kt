package com.example.bloomapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Insights
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bloomapp.ui.home.components.BannerAdView

private val BloomRoseColor = Color(0xFFC06260)
private val LightRoseBg = Color(0xFFFAF0EE)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    var selectedTab by remember { mutableStateOf(0) }
    var activeBottomSheet by remember { mutableStateOf<String?>(null) }
    val sheetState = rememberModalBottomSheetState()

    Scaffold(
        bottomBar = {
            NavigationBar(containerColor = Color.White) {
                val items = listOf(
                    Triple(0, "Dashboard", Icons.Default.Home),
                    Triple(1, "Insights", Icons.Default.Insights),
                    Triple(2, "Learn", Icons.Default.Book),
                    Triple(3, "Settings", Icons.Default.Settings)
                )
                items.forEach { (index, title, icon) ->
                    NavigationBarItem(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        icon = { Icon(icon, contentDescription = title) },
                        label = { Text(title, fontSize = 11.sp) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = BloomRoseColor,
                            indicatorColor = LightRoseBg
                        )
                    )
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when (selectedTab) {
                0 -> DashboardTabContent(onLogClick = { activeBottomSheet = it })
                1 -> PlaceholderTab("Insights Screen")
                2 -> PlaceholderTab("Learn PCOS Articles")
                3 -> PlaceholderTab("Settings & Reminders")
            }

            // Bottom sheet logging trigger according to Figma screens
            activeBottomSheet?.let { logType ->
                ModalBottomSheet(
                    onDismissRequest = { activeBottomSheet = null },
                    sheetState = sheetState,
                    containerColor = Color.White
                ) {
                    LogSheetContent(logType = logType, onClose = { activeBottomSheet = null })
                }
            }
        }
    }
}
@Composable
private fun DashboardTabContent(onLogClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("GOOD MORNING", fontSize = 11.sp, color = Color.Gray, letterSpacing = 1.sp)
                Text("Sofia", fontFamily = FontFamily.Serif, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(LightRoseBg),
                contentAlignment = Alignment.Center
            ) {
                Text("🌸")
            }
        }

        // 1. Today's Goals Card
        Card(
            colors = CardDefaults.cardColors(containerColor = LightRoseBg),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Today's Goals", fontFamily = FontFamily.Serif, fontSize = 20.sp, color = BloomRoseColor)
                Text("Saturday, June 27", fontSize = 12.sp, color = Color.Gray)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Let's get blooming", fontWeight = FontWeight.SemiBold)
                Text("0 of 5 complete", fontSize = 12.sp, color = Color.Gray)
            }
        }

        // 2. Banner Ad (Placed directly between Goals and FAQ)
        BannerAdView()

        // 3. PCOS FAQ Header section
        Card(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text("PCOS FAQ", fontSize = 12.sp, color = Color.Gray)
                    Text("What exactly is PCOS?", fontWeight = FontWeight.Bold)
                }
                Text(">", color = Color.Gray)
            }
        }

        // 4. Goal Progress Action Items
        Text("Goal Progress", fontWeight = FontWeight.Bold, fontSize = 16.sp)
        val goals = listOf(
            Triple("Meals", "0/3 meals", "Meals"),
            Triple("Movement", "0/30 min", "Movement"),
            Triple("Water", "0/8 glasses", "Water"),
            Triple("Relaxation", "0/15 min", "Relaxation"),
            Triple("Sleep", "0/8 hrs", "Sleep")
        )
        goals.forEach { (title, sub, type) ->
            Card(
                onClick = { onLogClick(type) },
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFAFAFA)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(title, fontWeight = FontWeight.SemiBold)
                        Text(sub, fontSize = 12.sp, color = Color.Gray)
                    }
                    Text("+ Log", color = BloomRoseColor, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                }
            }
        }

        // 5. Today's Tip Card
        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF9E6)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Today's tip", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFF8A6D3B))
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "LOG LAST NIGHT'S SLEEP\nYou haven't logged sleep yet. Consistent 7–8 hr nights help keep hormones balanced.",
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
private fun LogSheetContent(logType: String, onClose: () -> Unit) {
    var count by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Log $logType", fontFamily = FontFamily.Serif, fontSize = 22.sp, color = BloomRoseColor)
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            IconButton(onClick = { if (count > 0) count-- }) {
                Text("-", fontSize = 28.sp)
            }
            Text("$count", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            IconButton(onClick = { count++ }) {
                Text("+", fontSize = 28.sp)
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = onClose,
            colors = ButtonDefaults.buttonColors(containerColor = BloomRoseColor),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("Save Entry", color = Color.White)
        }
    }
}

@Composable
private fun PlaceholderTab(title: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(title, fontFamily = FontFamily.Serif, fontSize = 20.sp, color = Color.Gray)
    }
}