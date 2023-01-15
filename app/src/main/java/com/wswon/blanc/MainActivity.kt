package com.wswon.blanc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.wswon.blanc.ui.component.BlancTabRow
import com.wswon.blanc.ui.component.BlancTopNavigation
import com.wswon.blanc.ui.component.ShareTodayPost
import com.wswon.blanc.ui.myyesterday.MyYesterdayScreen
import com.wswon.blanc.ui.someone.SomeoneYesterdayScreen
import com.wswon.blanc.ui.theme.BlancTheme
import com.wswon.blanc.ui.today.TodayScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlancTheme {
                // A surface container using the 'background' color from the theme
                MainScreen()
            }
        }
    }

    @Composable
    private fun MainScreen(
        viewModel: HomeViewModel = hiltViewModel()
    ) {

        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val currentScreen =
            tabRowScreens.find { it.route == currentDestination?.route } ?: Today

        Scaffold(
            topBar = {
                Column(modifier = Modifier) {
                    BlancTopNavigation()
                    Spacer(modifier = Modifier.height(26.dp))
                    BlancTabRow(
                        screens = tabRowScreens,
                        onTabSelected = { screen ->
                            navController.navigateSingleTopTo(screen.route)
                        },
                        currentScreen = currentScreen
                    )
                }
            },
            modifier = Modifier
                .background(Color.Transparent)
        ) { innerPadding ->
            BlancNavHost(navController, Modifier.padding(innerPadding))
        }


//        Column(
//            modifier = Modifier.fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally
//            //                    color = MaterialTheme.colorScheme.background
//        ) {
//            ShareTodayPost(
//                Modifier,
//                onClickSend = {
//
//                }
//            )
//        }
    }
}

@Composable
fun BlancNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Today.route,
        modifier = modifier
    ) {
        composable(route = Today.route) {
            TodayScreen()
        }
        composable(route = MyYesterday.route) {
            MyYesterdayScreen()
        }
        composable(route = SomeoneYesterday.route) {
            SomeoneYesterdayScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BlancTheme {
        ShareTodayPost(Modifier)
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true }