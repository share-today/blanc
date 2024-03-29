package com.wswon.blanc.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.wswon.blanc.BlancDestination
import com.wswon.blanc.SomeoneYesterday
import com.wswon.blanc.ui.theme.Identity
import com.wswon.blanc.ui.theme.SubIdentity
import java.util.*

@Composable
fun BlancTabRow(
    screens: List<BlancDestination>,
    onTabSelected: (BlancDestination) -> Unit,
    currentScreen: BlancDestination
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
                val selectedColor = if (screen is SomeoneYesterday) SubIdentity else Identity

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

@Composable
fun RallyTab(
    text: String,
    onSelected: () -> Unit,
    selected: Boolean,
    selectedColor: Color,
) {
    val color = MaterialTheme.colors.onSurface
    val durationMillis = if (selected) TabFadeInAnimationDuration else TabFadeOutAnimationDuration
    val animSpec = remember {
        tween<Color>(
            durationMillis = durationMillis,
            easing = LinearEasing,
            delayMillis = TabFadeInAnimationDelay
        )
    }
    val tabTintColor by animateColorAsState(
        targetValue = if (selected) color else color.copy(alpha = InactiveTabOpacity),
        animationSpec = animSpec
    )

    val tabStyle =
        if (selected) MaterialTheme.typography.subtitle2 else MaterialTheme.typography.body2

    ConstraintLayout(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize()
            .selectable(
                selected = selected,
                onClick = onSelected,
                role = Role.Tab,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    bounded = false,
                    radius = Dp.Unspecified,
                    color = Color.Unspecified
                )
            )
            .clearAndSetSemantics { contentDescription = text },
    ) {

        val (indicator, tabText) = createRefs()

        Spacer(
            modifier = Modifier
                .padding(top = 8.dp)
                .height(18.dp)
                .background(color = if (selected) selectedColor else Color.Transparent)
                .constrainAs(indicator) {
                    start.linkTo(tabText.start)
                    end.linkTo(tabText.end)
                    top.linkTo(parent.top)
                    width = Dimension.fillToConstraints
                }
        )

        Text(
            modifier = Modifier.constrainAs(tabText) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            },
            text = text.uppercase(Locale.getDefault()),
            color = tabTintColor,
            style = tabStyle
        )
    }
}

private val TabHeight = 48.dp
private const val InactiveTabOpacity = 0.60f

private const val TabFadeInAnimationDuration = 150
private const val TabFadeInAnimationDelay = 100
private const val TabFadeOutAnimationDuration = 100