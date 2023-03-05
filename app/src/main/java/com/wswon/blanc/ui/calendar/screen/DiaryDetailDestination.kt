package com.wswon.blanc.ui.calendar.screen

import androidx.annotation.StringRes
import com.wswon.blanc.R

interface DiaryDetailDestination {
    val displayNameResId: Int
    val route: String
    val isRouteScreen: Boolean
}

object MyDiary : DiaryDetailDestination {
    @StringRes
    override val displayNameResId = R.string.my_diary
    override val route = "my_diary"
    override val isRouteScreen: Boolean = true
}

object SomeoneDiary : DiaryDetailDestination {
    @StringRes
    override val displayNameResId = R.string.someone_diary
    override val route = "someone_diary"
    override val isRouteScreen: Boolean = true
}

val tabRowScreens = listOf(MyDiary, SomeoneDiary)