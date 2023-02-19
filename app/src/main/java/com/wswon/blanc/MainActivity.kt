package com.wswon.blanc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
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
import com.wswon.blanc.ui.alert.AlertScreen
import com.wswon.blanc.ui.calendar.CalendarScreen
import com.wswon.blanc.ui.calendar.CalendarViewModel
import com.wswon.blanc.ui.component.BlancTabRow
import com.wswon.blanc.ui.component.BlancTopNavigation
import com.wswon.blanc.ui.component.InputDiary
import com.wswon.blanc.ui.component.RtlDrawerScaffold
import com.wswon.blanc.ui.component.drawer.DrawerItem
import com.wswon.blanc.ui.component.drawer.DrawerMenu
import com.wswon.blanc.ui.component.drawer.DrawerMenuShape
import com.wswon.blanc.ui.login.LoginScreen
import com.wswon.blanc.ui.myyesterday.MyYesterdayScreen
import com.wswon.blanc.ui.setting.SettingScreen
import com.wswon.blanc.ui.setting.screen.OpenSourceLicenseScreen
import com.wswon.blanc.ui.setting.screen.PrivacyPolicyScreen
import com.wswon.blanc.ui.setting.screen.SendCommentsScreen
import com.wswon.blanc.ui.setting.screen.TermsOfServiceScreen
import com.wswon.blanc.ui.someone.SomeoneYesterdayScreen
import com.wswon.blanc.ui.theme.BlancTheme
import com.wswon.blanc.ui.today.TodayScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.YearMonth

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
        viewModel: HomeViewModel = hiltViewModel(),
        calendarViewModel: CalendarViewModel = hiltViewModel(),
    ) {

        val navController = rememberNavController()
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination

        val currentScreen =
            allScreens.find { it.route == currentDestination?.route } ?: Today
        val currentTab =
            tabRowScreens.find { it.route == currentDestination?.route } ?: Today

        val scaffoldState = com.wswon.blanc.ui.component.rememberScaffoldState()
        val scope = rememberCoroutineScope()

        val list = calendarViewModel.yearMonthList

        val callback = remember {
            object : OnBackPressedCallback(scaffoldState.drawerState.isOpen) {
                override fun handleOnBackPressed() {
                    if (scaffoldState.drawerState.isOpen) {
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                    }
                }
            }
        }
        onBackPressedDispatcher.addCallback(
            this@MainActivity,
            callback
        )
        callback.isEnabled = scaffoldState.drawerState.isOpen

        RtlDrawerScaffold(
            scaffoldState = scaffoldState,
            topBar = {
                Column(modifier = Modifier) {
                    val isHomeScreen = currentScreen == currentTab

                    BlancTopNavigation(
                        isShowAlert = isHomeScreen && currentScreen.isRouteScreen,
                        isShowMenu = currentScreen.isRouteScreen,
                        isShowBack = !currentScreen.isRouteScreen,
                        onClickAlert = {
                            navController.navigateSingleTopTo(Alert.route)
                        },
                        onClickMenu = {
                            scope.launch {
                                scaffoldState.drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        },
                        onClickBack = {
                            onBackPressedDispatcher.onBackPressed()
                        }
                    )

                    if (isHomeScreen) {
                        Spacer(modifier = Modifier.height(26.dp))
                        BlancTabRow(
                            screens = tabRowScreens,
                            onTabSelected = { screen ->
                                navController.navigateSingleTopTo(screen.route)
                            },
                            currentScreen = currentTab
                        )
                    }
                }
            },
            drawerContent = {
                DrawerMenu { item ->
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                    when (item) {
                        DrawerItem.Home -> {
                            navController.navigateToRouteDestination(tabRowScreens)
                        }
                        DrawerItem.Calendar -> {
                            navController.navigateToRouteDestination(listOf(Calendar))
                        }
                        DrawerItem.Setting -> {
                            navController.navigateToRouteDestination(listOf(Setting))
                        }
                    }
                }
            },
            drawerShape = DrawerMenuShape(),
            drawerBackgroundColor = Color.White,
            drawerGesturesEnabled = true,
            modifier = Modifier
                .background(Color.Transparent)
        ) { innerPadding ->
            BlancNavHost(navController, Modifier.padding(innerPadding), list)
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
    modifier: Modifier,
    yearMonthList: List<YearMonth>
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
        composable(route = Calendar.route) {
            CalendarScreen(yearMonthList)
        }
        composable(route = Alert.route) {
            AlertScreen()
        }
        composable(route = Setting.route) {
            SettingScreen(navController)
        }
        composable(route = SendComments.route) {
            SendCommentsScreen()
        }
        composable(route = TermsOfService.route) {
            TermsOfServiceScreen()
        }
        composable(route = PrivacyPolicy.route) {
            PrivacyPolicyScreen()
        }
        composable(route = OpenSourceLicense.route) {
            OpenSourceLicenseScreen()
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


fun NavHostController.navigateToRouteDestination(
    destinations: List<BlancDestination>
) {
    require(destinations.isNotEmpty())

    val isAlreadyDestination = backQueue.any { backStack ->
        destinations.any { it.route == backStack.destination.route }
    }
    if (isAlreadyDestination) {
        while (true) {
            if (destinations.any { it.route == currentDestination?.route }) {
                break
            }
            popBackStack()
        }
    } else {
        navigateSingleTopTo(destinations.first().route)
    }
}