package com.wswon.blanc.ui.someone

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
import com.wswon.blanc.ui.component.InputDiary

@Composable
fun SomeoneYesterdayScreen() {
    Column(
        modifier =
        Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .semantics { contentDescription = "SomeoneYesterday Screen" }
    ) {
        Diary(
            modifier = Modifier.fillMaxWidth(),
            backgroundBrush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFFFFE7E7),
                    Color(0xFFFED8D8)
                )
            ),
            content = {
                InputDiary(
                    modifier = Modifier
                        .padding(top = 32.dp)
                        .fillMaxWidth(),
                    backgroundBrush = Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.8f),
                            Color.White.copy(alpha = 0.8f)
                        )
                    ),
                    inputTextMinHeight = 100.dp
                )
            }
        )
    }
}