package com.mindflow.clarity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mindflow.clarity.data.AppTheme
import com.mindflow.clarity.data.StreakPreferences
import com.mindflow.clarity.ui.screens.DashboardScreen
import com.mindflow.clarity.ui.screens.MilestonesScreen
import com.mindflow.clarity.ui.screens.ShieldScreen
import com.mindflow.clarity.ui.screens.StatsScreen
import com.mindflow.clarity.ui.theme.AccentRose
import com.mindflow.clarity.ui.theme.ClarityTheme

class MainActivity : ComponentActivity() {

    companion object {
        const val EXTRA_NAV_TARGET = "extra_nav_target"
        const val NAV_SHIELD = "shield"
    }

    private lateinit var prefs: StreakPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefs = (application as ClarityApp).preferences

        val initialTab = if (intent?.getStringExtra(EXTRA_NAV_TARGET) == NAV_SHIELD) 2 else 0

        setContent {
            var currentTheme by remember { mutableStateOf(prefs.themeMode) }

            ClarityTheme(themeMode = currentTheme) {
                ClarityMainApp(
                    prefs = prefs,
                    currentTheme = currentTheme,
                    onToggleTheme = {
                        val next = prefs.toggleTheme()
                        currentTheme = next
                    },
                    initialTab = initialTab
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        prefs.updateLongestStreakIfNeeded()
        prefs.updateAllWidgets()
    }
}

sealed class Screen(val index: Int, val title: String, val icon: ImageVector) {
    object Dashboard : Screen(0, "Streak", Icons.Default.Home)
    object Benefits : Screen(1, "Benefits", Icons.Default.AutoAwesome)
    object Shield : Screen(2, "Shield", Icons.Default.Shield)
    object Stats : Screen(3, "History", Icons.Default.BarChart)
}

@Composable
fun ClarityMainApp(
    prefs: StreakPreferences,
    currentTheme: AppTheme,
    onToggleTheme: () -> Unit,
    initialTab: Int = 0
) {
    var selectedTab by remember { mutableIntStateOf(initialTab) }

    val screens = listOf(
        Screen.Dashboard,
        Screen.Benefits,
        Screen.Shield,
        Screen.Stats
    )

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 2.dp,
                modifier = Modifier
                    .border(
                        1.dp,
                        MaterialTheme.colorScheme.outlineVariant,
                        RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp)
                    )
                    .clip(RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp))
            ) {
                screens.forEach { screen ->
                    val isSelected = selectedTab == screen.index
                    val activeColor = when (screen.index) {
                        2 -> AccentRose
                        1 -> MaterialTheme.colorScheme.secondary
                        else -> MaterialTheme.colorScheme.primary
                    }

                    NavigationBarItem(
                        selected = isSelected,
                        onClick = { selectedTab = screen.index },
                        icon = {
                            Icon(
                                imageVector = screen.icon,
                                contentDescription = screen.title,
                                modifier = Modifier.size(22.dp)
                            )
                        },
                        label = {
                            Text(
                                text = screen.title,
                                fontSize = 11.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = activeColor,
                            selectedTextColor = activeColor,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                            indicatorColor = activeColor.copy(alpha = 0.15f)
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (selectedTab) {
                0 -> DashboardScreen(
                    prefs = prefs,
                    currentTheme = currentTheme,
                    onToggleTheme = onToggleTheme,
                    onNavigateToShield = { selectedTab = 2 },
                    onNavigateToMilestones = { selectedTab = 1 }
                )
                1 -> MilestonesScreen(prefs = prefs)
                2 -> ShieldScreen(prefs = prefs)
                3 -> StatsScreen(prefs = prefs)
            }
        }
    }
}
