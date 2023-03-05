package com.wswon.blanc.ui.calendar.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.wswon.blanc.ui.component.RallyTab
import com.wswon.blanc.ui.theme.Identity
import com.wswon.blanc.ui.theme.SubIdentity

@Composable
fun DiaryDetailTabRow(
    screens: List<DiaryDetailDestination>,
    onTabSelected: (DiaryDetailDestination) -> Unit,
    currentScreen: DiaryDetailDestination
) {
    ConstraintLayout(
        modifier = Modifier
            .height(TabHeight)
            .fillMaxWidth()
    ) {

        val (tabRow, divider) = createRefs()

        Row(
            modifier = Modifier
                .selectableGroup()
                .padding(start = 24.dp)
                .constrainAs(tabRow) {
                    bottom.linkTo(parent.bottom)
                }
        ) {
            screens.forEach { screen ->
                val selectedColor = if (screen is SomeoneDiary) SubIdentity else Identity

                RallyTab(
                    text = stringResource(id = screen.displayNameResId),
                    onSelected = { onTabSelected(screen) },
                    selected = currentScreen == screen,
                    selectedColor = selectedColor
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .padding(start = 24.dp)
                .background(Color.Black)
                .constrainAs(divider) {
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}

private val TabHeight = 48.dp