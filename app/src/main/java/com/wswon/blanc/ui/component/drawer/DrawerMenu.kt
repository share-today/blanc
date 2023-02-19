package com.wswon.blanc.ui.component.drawer

import android.os.Parcelable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wswon.blanc.R
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Composable
fun DrawerMenu(onSelectedMenu: (DrawerItem) -> Unit) {
    Box(
        modifier = Modifier
            .width(200.dp)
            .fillMaxHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            var isSelectedItem by rememberSaveable { mutableStateOf<DrawerItem>(DrawerItem.Home) }
            val onClickItem: (DrawerItem) -> Unit = { item ->
                isSelectedItem = item
                onSelectedMenu(item)
            }

            DrawerMenuItem(
                drawerItem = DrawerItem.Home,
                modifier = Modifier,
                isSelected = isSelectedItem == DrawerItem.Home,
                onClickItem = onClickItem
            )
            DrawerMenuItem(
                drawerItem = DrawerItem.Calendar,
                modifier = Modifier,
                isSelected = isSelectedItem == DrawerItem.Calendar,
                onClickItem = onClickItem
            )
            DrawerMenuItem(
                drawerItem = DrawerItem.Setting,
                modifier = Modifier,
                isSelected = isSelectedItem == DrawerItem.Setting,
                onClickItem = onClickItem
            )
        }
    }
}


sealed interface DrawerItem : Parcelable {

    val textResId: Int

    @Parcelize
    object Home : DrawerItem {
        @IgnoredOnParcel
        override val textResId: Int = R.string.home
    }

    @Parcelize
    object Calendar : DrawerItem {
        @IgnoredOnParcel
        override val textResId: Int = R.string.calendar
    }

    @Parcelize
    object Setting : DrawerItem {
        @IgnoredOnParcel
        override val textResId: Int = R.string.setting
    }
}