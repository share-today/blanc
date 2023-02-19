package com.wswon.blanc.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wswon.blanc.R

@Preview(showBackground = true)
@Composable
fun BlancTopNavigation(
    isShowAlert: Boolean = true,
    isShowMenu: Boolean = true,
    isShowBack: Boolean = true,
    onClickAlert: () -> Unit = {},
    onClickMenu: () -> Unit = {},
    onClickBack: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .padding(24.dp)
    ) {
        if (isShowAlert) {
            IconButton(
                onClick = onClickAlert,
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bell),
                    contentDescription = "alert"
                )
            }
        }

        if (isShowBack) {
            IconButton(
                onClick = onClickBack,
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = "back"
                )
            }
        }

        if (isShowMenu) {
            IconButton(
                onClick = onClickMenu,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterEnd)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_menu),
                    contentDescription = "menu"
                )
            }
        }

    }
}