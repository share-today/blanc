package com.wswon.blanc.ui.myyesterday

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.wswon.blanc.ui.component.Diary

@Composable
fun MyYesterdayScreen() {
    Column(
        modifier =
        Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .semantics { contentDescription = "MyYesterday Screen" }
    ) {
        Diary(
            modifier = Modifier.fillMaxWidth(),
            backgroundBrush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFFE7EDFF),
                    Color(0xFFD8E3FE)
                )
            )
        )
    }
}