package com.wswon.blanc.ui.myyesterday

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.wswon.blanc.ui.component.Diary
import com.wswon.blanc.ui.component.DiaryState
import com.wswon.blanc.ui.component.dialog.BlancDialog
import com.wswon.blanc.ui.component.dialog.DialogType
import com.wswon.blanc.ui.component.dialog.ItemBottomSheetDialog
import com.wswon.blanc.ui.component.dialog.SheetItem
import com.wswon.blanc.util.SnackbarProvider
import com.wswon.blanc.util.SnackbarType
import kotlinx.coroutines.launch

@Composable
fun MyYesterdayScreen(viewModel: MyYesterdayViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsState()

    var isShowDialog by rememberSaveable { mutableStateOf(false) }
    var isBottomSheetDialogOpen by rememberSaveable { mutableStateOf(false) }

    BlancDialog(
        type = DialogType.DeleteMyDiary,
        isOpen = isShowDialog,
        onConfirm = {
            isShowDialog = false
        },
        onDismiss = {
            isShowDialog = false
        }
    )

    ItemBottomSheetDialog(
        items = listOf(
            SheetItem.Delete
        ),
        isOpen = isBottomSheetDialogOpen,
        onClickItem = { item ->
            if (item is SheetItem.Delete) {
                isShowDialog = true
            }

            isBottomSheetDialogOpen = false
        },
        onClose = {
            isBottomSheetDialogOpen = false
        }
    )

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .semantics { contentDescription = "MyYesterday Screen" }
    ) {

        state.diary?.let {
            Diary(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(top = 24.dp, bottom = 16.dp),
                diaryState = it,
                onClickMore = {
                    isBottomSheetDialogOpen = true
                }
            )
        }

        Comments(state.comments, viewModel)
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun Comments(
    comments: List<DiaryState>,
    viewModel: MyYesterdayViewModel
) {
    val scope = rememberCoroutineScope()

    var isShowReportDialog by rememberSaveable { mutableStateOf(false) }
    var isShowDeleteDialog by rememberSaveable { mutableStateOf(false) }
    var isBottomSheetDialogOpen by rememberSaveable { mutableStateOf(false) }

    BlancDialog(
        type = DialogType.Report,
        isOpen = isShowReportDialog,
        onConfirm = {
            isShowReportDialog = false
        },
        onDismiss = {
            isShowReportDialog = false
        }
    )

    BlancDialog(
        type = DialogType.DeleteOthers,
        isOpen = isShowDeleteDialog,
        onConfirm = {
            isShowDeleteDialog = false
        },
        onDismiss = {
            isShowDeleteDialog = false
        }
    )

    ItemBottomSheetDialog(
        items = listOf(
            SheetItem.Report,
            SheetItem.Delete
        ),
        isOpen = isBottomSheetDialogOpen,
        onClickItem = { item ->
            if (item is SheetItem.Report) {
                isShowReportDialog = true
            } else if (item is SheetItem.Delete) {
                isShowDeleteDialog = true
            }

            isBottomSheetDialogOpen = false
        },
        onClose = {
            isBottomSheetDialogOpen = false
        }
    )

    HorizontalPager(
        pageCount = comments.size,
        modifier = Modifier,
        pageSpacing = 8.dp,
        verticalAlignment = Alignment.Top,
        contentPadding = PaddingValues(horizontal = 24.dp),
    ) { page ->
        val diaryState = comments[page]
        Diary(
            modifier = Modifier.fillMaxWidth(),
            diaryState = diaryState,
            onClickLike = {
                scope.launch {
                    SnackbarProvider.show(SnackbarType.LikeComplete)
                }
                viewModel.onClickLike(diaryState.id)
            },
            onClickMore = {
                isBottomSheetDialogOpen = true
            },
            content = {

            }
        )
    }
}