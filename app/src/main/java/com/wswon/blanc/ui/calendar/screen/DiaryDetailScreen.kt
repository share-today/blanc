package com.wswon.blanc.ui.calendar.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.wswon.blanc.navigateSingleTopTo
import com.wswon.blanc.ui.component.Day

@Composable
fun DiaryDetailScreen(day: Day) {
    val navController = rememberNavController()
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStack?.destination

    val currentTab: DiaryDetailDestination =
        tabRowScreens.find { it.route == currentDestination?.route } ?: MyDiary

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Column {
        DiaryDetailTabRow(
            screens = tabRowScreens,
            onTabSelected = { screen ->
                navController.navigateSingleTopTo(screen.route)
            },
            currentScreen = currentTab
        )
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            Column(modifier = Modifier) {
                Spacer(modifier = Modifier.height(26.dp))
                DiaryDetailTabRow(
                    screens = tabRowScreens,
                    onTabSelected = { screen ->
                        navController.navigateSingleTopTo(screen.route)
                    },
                    currentScreen = currentTab
                )
            }
        },
        modifier = Modifier
            .background(Color.Transparent)
    ) { innerPadding ->
        DiaryDetailNavHost(navController, Modifier.padding(innerPadding))
    }
}

@Composable
fun DiaryDetailNavHost(
    navController: NavHostController,
    modifier: Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = MyDiary.route,
        modifier = modifier
    ) {
        composable(route = MyDiary.route) {
            MyDiaryScreen()
        }
        composable(route = SomeoneDiary.route) {
            SomeoneDiaryScreen()
        }
    }
}
