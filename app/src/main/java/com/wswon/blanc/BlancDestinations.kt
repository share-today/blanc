package com.wswon.blanc

import androidx.annotation.StringRes

interface BlancDestination {
    val displayNameResId: Int
    val route: String
}

object Today : BlancDestination {
    @StringRes
    override val displayNameResId = R.string.today_tab
    override val route = "today"
}

object MyYesterday : BlancDestination {
    @StringRes
    override val displayNameResId = R.string.my_yesterday_tab
    override val route = "my_yesterday"
}

object SomeoneYesterday : BlancDestination {
    @StringRes
    override val displayNameResId = R.string.someone_yesterday_tab
    override val route = "someone_yesterday"
}

val tabRowScreens = listOf(Today, MyYesterday, SomeoneYesterday)


