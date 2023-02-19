package com.wswon.blanc.ui.setting.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.wswon.blanc.SendComments
import com.wswon.blanc.ui.component.SectionHeaderView

@Composable
fun SendCommentsScreen() {
    Column {
        Spacer(modifier = Modifier.height(26.dp))
        SectionHeaderView(
            modifier = Modifier
                .padding(start = 24.dp),
            text = stringResource(id = SendComments.displayNameResId)
        )
    }
}