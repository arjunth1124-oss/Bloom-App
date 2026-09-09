package com.example.bloomapp.ui.onboarding

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

private val BloomRoseColor = Color(0xFFC06260)
private val LightRoseBg = Color(0xFFFAF0EE)

@Composable
fun OnboardingScreen(
    onNavigateToHome: () -> Unit,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    var step by remember { mutableStateOf(1) }
    var userName by remember { mutableStateOf("") }

    Scaffold(
        containerColor = Color.White
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Progress Bar Indicators
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(4) { index ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(4.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(if (index + 1 <= step) BloomRoseColor else Color.LightGray.copy(alpha = 0.4f))
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            AnimatedContent(
                targetState = step,
                modifier = Modifier.weight(1f),
                label = "OnboardingSteps"
            ) { currentStep ->
                when (currentStep) {
                    1 -> OnboardingStep1(onNext = { step = 2 })
                    2 -> OnboardingStep2(name = userName, onNameChange = { userName = it }, onNext = { step = 3 })
                    3 -> OnboardingStep3(onNext = { step = 4 })
                    4 -> OnboardingStep4(onComplete = { viewModel.completeOnboarding(onNavigateToHome) })
                }
            }
        }
    }
}

@Composable
private fun OnboardingStep1(onNext: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 40.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .background(LightRoseBg),
                contentAlignment = Alignment.Center
            ) {
                Text("🌸", fontSize = 54.sp)
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Welcome to Bloom",
                fontFamily = FontFamily.Serif,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = BloomRoseColor
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "A daily companion for managing PCOS — the 5 core habits, fitness, cycle, nutrition & daily check-ins.",
                textAlign = TextAlign.Center,
                color = Color.Gray,
                fontSize = 15.sp
            )
        }
        Button(
            onClick = onNext,
            colors = ButtonDefaults.buttonColors(containerColor = BloomRoseColor),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(26.dp)
        ) {
            Text("Get Started", fontSize = 16.sp, color = Color.White)
        }
    }
}

@Composable
private fun OnboardingStep2(name: String, onNameChange: (String) -> Unit, onNext: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "A little about you",
                fontFamily = FontFamily.Serif,
                fontSize = 24.sp,
                color = BloomRoseColor
            )
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(
                value = name,
                onValueChange = onNameChange,
                label = { Text("Your first name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Button(
            onClick = onNext,
            colors = ButtonDefaults.buttonColors(containerColor = BloomRoseColor),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(26.dp)
        ) {
            Text("Continue", fontSize = 16.sp, color = Color.White)
        }
    }
}

@Composable
private fun OnboardingStep3(onNext: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "What would you like to track daily?",
            fontFamily = FontFamily.Serif,
            fontSize = 22.sp,
            color = BloomRoseColor,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Pick the habits that matter to you. You can adjust these anytime in Settings.",
            textAlign = TextAlign.Center,
            color = Color.Gray,
            fontSize = 13.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            val habits = listOf(
                "Meals" to "3 balanced meals",
                "Movement" to "30 minutes",
                "Water" to "8 glasses",
                "Relaxation" to "15 minutes",
                "Sleep" to "8 hours",
                "Cycle" to "Period dates",
                "Symptom check-in" to "Bloating, skin, mood"
            )
            habits.forEach { (title, subtitle) ->
                var checked by remember { mutableStateOf(true) }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(title, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                        Text(subtitle, color = Color.Gray, fontSize = 12.sp)
                    }
                    Switch(
                        checked = checked,
                        onCheckedChange = { checked = it },
                        colors = SwitchDefaults.colors(checkedThumbColor = BloomRoseColor)
                    )
                }
                HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f))
            }
        }
        Button(
            onClick = onNext,
            colors = ButtonDefaults.buttonColors(containerColor = BloomRoseColor),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(26.dp)
        ) {
            Text("Continue", fontSize = 16.sp, color = Color.White)
        }
    }
}

@Composable
private fun OnboardingStep4(onComplete: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "When should we remind you?",
                fontFamily = FontFamily.Serif,
                fontSize = 22.sp,
                color = BloomRoseColor
            )
            Spacer(modifier = Modifier.height(24.dp))
            val options = listOf(
                "Morning" to "7:30 AM — start the day on track",
                "Afternoon" to "1:00 PM — midday check-in",
                "Evening" to "8:00 PM — wind down and reflect"
            )
            var selectedOption by remember { mutableStateOf(0) }
            options.forEachIndexed { index, (time, desc) ->
                Card(
                    onClick = { selectedOption = index },
                    colors = CardDefaults.cardColors(
                        containerColor = if (selectedOption == index) LightRoseBg else Color(0xFFF7F7F7)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(time, fontWeight = FontWeight.Bold, color = BloomRoseColor)
                        Text(desc, fontSize = 12.sp, color = Color.Gray)
                    }
                }
            }
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(
                onClick = onComplete,
                colors = ButtonDefaults.buttonColors(containerColor = BloomRoseColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(26.dp)
            ) {
                Text("Know Focus", fontSize = 16.sp, color = Color.White)
            }
            TextButton(onClick = onComplete) {
                Text("I'll set this up later", color = Color.Gray, fontSize = 13.sp)
            }
        }
    }
}