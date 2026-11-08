package com.mindflow.clarity.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

enum class BreathPhase(val instruction: String, val durationSec: Int) {
    INHALE("Inhale Deeply", 4),
    HOLD_IN("Hold Your Breath", 4),
    EXHALE("Exhale Slowly", 4),
    HOLD_OUT("Rest & Center", 4)
}

@Composable
fun BreathingVisualizer(
    modifier: Modifier = Modifier
) {
    var isRunning by remember { mutableStateOf(false) }
    var currentPhase by remember { mutableStateOf(BreathPhase.INHALE) }
    var secondsLeft by remember { mutableIntStateOf(4) }
    var cycleCount by remember { mutableIntStateOf(0) }

    val scaleAnim = remember { Animatable(0.6f) }

    val primaryColor = MaterialTheme.colorScheme.primary
    val secondaryColor = MaterialTheme.colorScheme.secondary
    val trackColor = MaterialTheme.colorScheme.surfaceVariant

    LaunchedEffect(isRunning) {
        if (!isRunning) {
            scaleAnim.snapTo(0.6f)
            return@LaunchedEffect
        }

        while (isRunning) {
            // INHALE (4s)
            currentPhase = BreathPhase.INHALE
            for (s in 4 downTo 1) {
                secondsLeft = s
                scaleAnim.animateTo(
                    targetValue = 1.0f,
                    animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
                )
            }

            // HOLD IN (4s)
            currentPhase = BreathPhase.HOLD_IN
            for (s in 4 downTo 1) {
                secondsLeft = s
                delay(1000)
            }

            // EXHALE (4s)
            currentPhase = BreathPhase.EXHALE
            for (s in 4 downTo 1) {
                secondsLeft = s
                scaleAnim.animateTo(
                    targetValue = 0.6f,
                    animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
                )
            }

            // HOLD OUT (4s)
            currentPhase = BreathPhase.HOLD_OUT
            for (s in 4 downTo 1) {
                secondsLeft = s
                delay(1000)
            }

            cycleCount++
        }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(230.dp)
        ) {
            // Outer guideline ring
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawCircle(
                    color = trackColor,
                    radius = size.minDimension / 2.1f,
                    style = Stroke(width = 3.dp.toPx())
                )
            }

            // Pulsing inner aura circle
            Box(
                modifier = Modifier
                    .size((230 * scaleAnim.value).dp)
                    .clip(CircleShape)
                    .background(
                        Brush.radialGradient(
                            colors = listOf(
                                secondaryColor.copy(alpha = 0.35f),
                                primaryColor.copy(alpha = 0.15f),
                                Color.Transparent
                            )
                        )
                    )
            )

            // Dynamic instruction text inside circle
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (isRunning) {
                    Text(
                        text = currentPhase.instruction,
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "${secondsLeft}s",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Black
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Cycle $cycleCount",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 11.sp
                    )
                } else {
                    Text(
                        text = "4-4-4-4",
                        color = MaterialTheme.colorScheme.secondary,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Box Breathing",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Lowers Cortisol & Urges",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 11.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        Button(
            onClick = {
                isRunning = !isRunning
                if (!isRunning) {
                    cycleCount = 0
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isRunning) MaterialTheme.colorScheme.surfaceVariant else MaterialTheme.colorScheme.primary,
                contentColor = if (isRunning) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onPrimary
            ),
            shape = MaterialTheme.shapes.medium
        ) {
            Text(
                text = if (isRunning) "Stop Breathwork" else "Start 2-Min Reset",
                fontWeight = FontWeight.SemiBold,
                fontSize = 13.sp
            )
        }
    }
}
