package com.wswon.blanc

import androidx.annotation.StringRes

interface BlancDestination {
    val displayNameResId: Int
    val route: String
    val isRouteScreen: Boolean
}

val allScreens = listOf(
    Today,
    MyYesterday,
    SomeoneYesterday,
    Calendar,
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
    override val isRouteScreen: Boolean = true
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
