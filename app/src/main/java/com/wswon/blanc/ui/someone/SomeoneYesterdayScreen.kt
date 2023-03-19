package com.wswon.blanc.ui.someone

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.wswon.blanc.ui.component.Diary
import com.wswon.blanc.ui.component.InputDiary
import com.wswon.blanc.util.SnackbarProvider
import com.wswon.blanc.util.SnackbarType
import kotlinx.coroutines.launch

// dialog 붙이기
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SomeoneYesterdayScreen(viewModel: SomeoneYesterdayViewModel = hiltViewModel()) {
    val state by viewModel.someone.collectAsState()

    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .padding(top = 24.dp)
            .verticalScroll(rememberScrollState())
            .semantics { contentDescription = "SomeoneYesterday Screen" }
    ) {
        HorizontalPager(
            pageCount = state.diaryList.size,
            modifier = Modifier,
            pageSpacing = 8.dp,
            verticalAlignment = Alignment.Top,
            contentPadding = PaddingValues(horizontal = 24.dp),
        ) { page ->
            val diaryState = state.diaryList[page]
            Diary(
                modifier = Modifier.fillMaxWidth(),
                diaryState = diaryState,
                onClickLike = {
                    scope.launch {
                        SnackbarProvider.show(SnackbarType.WatchingAds)
                    }
                    viewModel.onClickLike(diaryState.id)
                },
                onClickMore = {

                },
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
                        inputTextMinHeight = 72.dp,
                        maxCount = 50,
                    )
                }
            )
        }
    }
}