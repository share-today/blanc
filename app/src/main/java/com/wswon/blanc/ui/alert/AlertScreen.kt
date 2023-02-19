package com.wswon.blanc.ui.alert

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.wswon.blanc.Alert
import com.wswon.blanc.ui.component.SectionHeaderView

@Composable
fun AlertScreen() {
    Column {
        Spacer(modifier = Modifier.height(26.dp))
        SectionHeaderView(
            modifier = Modifier
                .padding(start = 24.dp),
            text = stringResource(id = Alert.displayNameResId)
        )
    }
}