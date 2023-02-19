package com.wswon.blanc.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun SectionHeaderView(modifier: Modifier = Modifier, text: String = "시스템 설정") {
    Column(modifier = modifier) {
        Text(
            text = text,
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.padding(start = 12.dp, top = 12.dp, end = 12.dp, bottom = 11.dp)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Black)
        )
    }
}