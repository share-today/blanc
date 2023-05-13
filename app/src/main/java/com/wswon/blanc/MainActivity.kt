package com.wswon.blanc

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.blanc.common.WLog
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.wswon.blanc.fcm.FcmUtil
import com.wswon.blanc.login.GoogleLoginManager
import com.wswon.blanc.login.KakaoLoginManager
import com.wswon.blanc.ui.alert.AlertScreen
import com.wswon.blanc.ui.calendar.CalendarScreen
import com.wswon.blanc.ui.calendar.screen.DiaryDetailScreen
import com.wswon.blanc.ui.component.BlancTabRow
import com.wswon.blanc.ui.component.BlancTopNavigation
import com.wswon.blanc.ui.component.Day
import com.wswon.blanc.ui.component.InputDiary
import com.wswon.blanc.ui.component.RtlDrawerScaffold
import com.wswon.blanc.ui.component.drawer.DrawerItem
import com.wswon.blanc.ui.component.drawer.DrawerMenu
import com.wswon.blanc.ui.component.drawer.DrawerMenuShape
import com.wswon.blanc.ui.component.rememberScaffoldState
import com.wswon.blanc.ui.login.LoginButtonType
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
import com.wswon.blanc.util.CustomSnackBar
import com.wswon.blanc.util.SnackbarProvider
import com.wswon.blanc.util.SnackbarType
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
//                        isLogged = true

                        login(it)
                    }
                }
            }
        }

        lifecycleScope.launch {
            WLog.d("getToken ${FcmUtil.getToken()}")
        }
    }

    private fun login(type: LoginButtonType) {
        when (type) {
            LoginButtonType.Kakao -> {
                lifecycleScope.launch {
                    KakaoLoginManager.login(this@MainActivity)
                }
            }
            LoginButtonType.Google -> {
                lifecycleScope.launch {
                    GoogleLoginManager.login(this@MainActivity)
                }
            }
            LoginButtonType.Apple -> {

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            GoogleLoginManager.REQ_ONE_TAP -> {
                try {
                    val credential =
                        GoogleLoginManager.oneTapClient.getSignInCredentialFromIntent(data)
                    val idToken = credential.googleIdToken
                    val username = credential.id
                    val password = credential.password

                    WLog.d("idToken $idToken\n username $username\n password $password")
                    when {
                        idToken != null -> {
                            // Got an ID token from Google. Use it to authenticate
                            // with your backend.
                            WLog.d("Got ID token.")
                        }
                        password != null -> {
                            // Got a saved username and password. Use them to authenticate
                            // with your backend.
                            WLog.d("Got password.")
                        }
                        else -> {
                            // Shouldn't happen.
                            WLog.d("No ID token or password!")
                        }
                    }
                } catch (e: ApiException) {
                    e.printStackTrace()
                    when (e.statusCode) {
                        CommonStatusCodes.CANCELED -> {
                            WLog.d("One-tap dialog was closed.")
                            // Don't re-prompt the user.
                        }
                        CommonStatusCodes.NETWORK_ERROR -> {
                            WLog.d("One-tap encountered a network error.")
                            // Try again or just ignore.
                        }
                        else -> {
                            WLog.d("Couldn't get credential from result." +
                                " (${e.localizedMessage})")
                        }
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

        val snackbarData by viewModel.snackbarData.collectAsState(initial = null)
        val snackState = remember { SnackbarHostState() }

        LaunchedEffect(snackbarData) {
            if (snackbarData != null) {
                snackState.showSnackbar((0..Int.MAX_VALUE).random().toString())
            }
        }

        val currentScreen =
            BlancDestination.find(currentDestination?.route) ?: Today
        val currentTab =
            tabRowScreens.find { it.route == currentDestination?.route } ?: Today

        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()

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
                val currentItem = when {
                    tabRowScreens.contains(currentScreen) -> DrawerItem.Home
                    currentScreen is Calendar -> DrawerItem.Calendar
                    currentScreen is Setting -> DrawerItem.Setting
                    else -> DrawerItem.Home
                }
                DrawerMenu(
                    currentSelectedItem = currentItem
                ) { item ->
                    scope.launch {
                        scaffoldState.drawerState.close()
                        SnackbarProvider.show(SnackbarType.values().random())
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
            BlancNavHost(navController, Modifier.padding(innerPadding))
        }

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            SnackbarHost(
                modifier = Modifier.align(Alignment.BottomStart),
                hostState = snackState
            ) {
                snackbarData?.let { data ->
                    CustomSnackBar(
                        snackbarData = data,
                        containerColor = Color.White,
                        onClickAction = {
                            // TODO: 광고보기 등..
                        }
                    )
                }
            }
        }
    }

//    private suspend fun showSnackBar(snackbarHostState: SnackbarHostState) {
//        val result = snackbarHostState
//            .showSnackbar(
//                message = "Snackbar",
//                actionLabel = "광고보기",
//                duration = SnackbarDuration.Short
//            )
//        when (result) {
//            SnackbarResult.ActionPerformed -> {
//                /* Handle snackbar action performed */
//            }
//            SnackbarResult.Dismissed -> {
//                /* Handle snackbar dismissed */
//            }
//        }
//    }
}

@Composable
fun BlancNavHost(
    navController: NavHostController,
    modifier: Modifier,
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
            CalendarScreen(navController = navController)
        }
        composable(
            route = DiaryDetail.routeWithArguments,
            arguments = DiaryDetail.arguments,
            deepLinks = DiaryDetail.deepLinks
        ) { navBackStackEntry ->
            val arguments = requireNotNull(navBackStackEntry.arguments)
            val year: Int =
                arguments.getInt(DiaryDetail.yearArg)
            val month: Int =
                arguments.getInt(DiaryDetail.monthArg)
            val day: Int =
                arguments.getInt(DiaryDetail.dayArg)

            DiaryDetailScreen(Day(YearMonth.of(year, month), day))
        }
        composable(route = Alert.route) {
            AlertScreen()
        }
        composable(route = Setting.route) {
            SettingScreen(navController = navController)
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

fun NavHostController.navigateSingleTopTo(route: String) {
    this.navigate(route) { launchSingleTop = true }
}

fun NavHostController.navigateToDiaryDetail(day: Day) {
    this.navigateSingleTopTo("${DiaryDetail.route}/${day.yearMonth.year}/${day.yearMonth.month.value}/${day.day}")
}

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