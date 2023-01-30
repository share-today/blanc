package com.wswon.blanc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.wswon.blanc.ui.DrawerMenuShape
import com.wswon.blanc.ui.component.BlancTabRow
import com.wswon.blanc.ui.component.BlancTopNavigation
import com.wswon.blanc.ui.component.InputDiary
import com.wswon.blanc.ui.component.RtlDrawerScaffold
import com.wswon.blanc.ui.login.LoginScreen
import com.wswon.blanc.ui.myyesterday.MyYesterdayScreen
import com.wswon.blanc.ui.someone.SomeoneYesterdayScreen
import com.wswon.blanc.ui.theme.BlancTheme
import com.wswon.blanc.ui.today.TodayScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlancTheme {
                var isLogged by rememberSaveable { mutableStateOf(false) }

                if (isLogged) {
                    MainScreen()
                } else {
                    LoginScreen {
                        isLogged = true
                    }
                }
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

        val scaffoldState = com.wswon.blanc.ui.component.rememberScaffoldState()
        val scope = rememberCoroutineScope()

        RtlDrawerScaffold(
            scaffoldState = scaffoldState,
            topBar = {
                Column(modifier = Modifier) {
                    BlancTopNavigation(
                        onClickAlert = {

                        },
                        onClickMenu = {
                            scope.launch {
                                scaffoldState.snackbarHostState
                                showSnackBar(scaffoldState.snackbarHostState)
                            }

                            scope.launch {
                                scaffoldState.drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }
                    )
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
            drawerContent = {
                Text(text = "Hello World")
            },
            drawerShape = DrawerMenuShape(),
            drawerBackgroundColor = Color.White,
            drawerGesturesEnabled = true,
            modifier = Modifier
                .background(Color.Transparent)
        ) { innerPadding ->
            BlancNavHost(navController, Modifier.padding(innerPadding))
        }
    }


    private suspend fun showSnackBar(snackbarHostState: SnackbarHostState) {
        val result = snackbarHostState
            .showSnackbar(
                message = "Snackbar",
                actionLabel = "광고보기",
                duration = SnackbarDuration.Short
            )
        when (result) {
            SnackbarResult.ActionPerformed -> {
                /* Handle snackbar action performed */
            }
            SnackbarResult.Dismissed -> {
                /* Handle snackbar dismissed */
            }
        }
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
        InputDiary(Modifier)
    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true }