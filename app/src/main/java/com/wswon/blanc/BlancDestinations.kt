package com.wswon.blanc

import androidx.annotation.StringRes
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

interface BlancDestination {
    val displayNameResId: Int
    val route: String
    val isRouteScreen: Boolean

    companion object {
        fun find(route: String?): BlancDestination? {
            if (route.isNullOrEmpty()) return null

            return allScreens.find {
                it.route == route.split("/").firstOrNull()
            }
        }
    }
}

val allScreens = listOf(
    Today,
    MyYesterday,
    SomeoneYesterday,
    Calendar,
    DiaryDetail,
    Setting,
    Alert,
    Setting,
    SendComments,
    TermsOfService,
    PrivacyPolicy,
    OpenSourceLicense
)

object Today : BlancDestination {
    @StringRes
    override val displayNameResId = R.string.today_tab
    override val route = "today"
    override val isRouteScreen: Boolean = true
}

object MyYesterday : BlancDestination {
    @StringRes
    override val displayNameResId = R.string.my_yesterday_tab
    override val route = "my_yesterday"
    override val isRouteScreen: Boolean = true
}

object SomeoneYesterday : BlancDestination {
    @StringRes
    override val displayNameResId = R.string.someone_yesterday_tab
    override val route = "someone_yesterday"
    override val isRouteScreen: Boolean = true
}

val tabRowScreens = listOf(Today, MyYesterday, SomeoneYesterday)

object Calendar : BlancDestination {
    @StringRes
    override val displayNameResId = R.string.calendar
    override val route = "calendar"
    override val isRouteScreen: Boolean = true
}

object DiaryDetail : BlancDestination {
    @StringRes
    override val displayNameResId = R.string.diary_detail
    override val route = "diary_detail"
    override val isRouteScreen: Boolean = false
    const val yearArg = "year_arg"
    const val monthArg = "month_arg"
    const val dayArg = "day_arg"
    val routeWithArguments = "$route/{$yearArg}/{$monthArg}/{$dayArg}"

    val arguments = listOf(
        navArgument(yearArg) { type = NavType.IntType },
        navArgument(monthArg) { type = NavType.IntType },
        navArgument(dayArg) { type = NavType.IntType }
    )

    val deepLinks = listOf(
        navDeepLink { uriPattern = "blanc://$route/{$yearArg}/{$monthArg}/{$dayArg}" }
    )
}

object Setting : BlancDestination {
    @StringRes
    override val displayNameResId = R.string.setting
    override val route = "setting"
    override val isRouteScreen: Boolean = true
}

object Alert : BlancDestination {
    @StringRes
    override val displayNameResId = R.string.alert
    override val route = "alert"
    override val isRouteScreen: Boolean = false
}

object SendComments : BlancDestination {
    @StringRes
    override val displayNameResId = R.string.setting_send_comments
    override val route = "send_comments"
    override val isRouteScreen: Boolean = false
}

object TermsOfService : BlancDestination {
    @StringRes
    override val displayNameResId = R.string.setting_terms_of_service
    override val route = "terms_of_service"
    override val isRouteScreen: Boolean = false
}

object PrivacyPolicy : BlancDestination {
    @StringRes
    override val displayNameResId = R.string.setting_privacy_policy
    override val route = "privacy_policy"
    override val isRouteScreen: Boolean = false
}

object OpenSourceLicense : BlancDestination {
    @StringRes
    override val displayNameResId = R.string.setting_open_source_license
    override val route = "open_source_license"
    override val isRouteScreen: Boolean = false
}

object Logout : BlancDestination {
    @StringRes
    override val displayNameResId = R.string.setting_logout
    override val route = "logout"
    override val isRouteScreen: Boolean = false
}
