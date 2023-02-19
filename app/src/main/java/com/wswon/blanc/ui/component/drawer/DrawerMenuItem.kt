package com.wswon.blanc.ui.component.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.wswon.blanc.ui.theme.Identity

@Composable
fun DrawerMenuItem(
    drawerItem: DrawerItem,
    modifier: Modifier,
    isSelected: Boolean,
    onClickItem: (DrawerItem) -> Unit
) {

    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = { onClickItem(drawerItem) },
        elevation = null,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
    ) {

        Box(
            modifier = Modifier,
            contentAlignment = Alignment.Center
        ) {
            val style =
                if (isSelected) MaterialTheme.typography.subtitle1 else MaterialTheme.typography.body1

            if (isSelected) {
                Spacer(
                    modifier = Modifier
                        .width(72.dp)
                        .height(18.dp)
                        .background(color = Identity)
                )
            }

            Text(
                text = stringResource(id = drawerItem.textResId),
                modifier = Modifier.padding(vertical = 26.dp),
                style = style
            )
        }
    }
}
