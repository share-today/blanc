package com.wswon.blanc.ui.calendar.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.wswon.blanc.ui.component.Diary
import com.wswon.blanc.ui.component.dialog.BlancDialog
import com.wswon.blanc.ui.component.dialog.DialogType
import com.wswon.blanc.ui.component.dialog.ItemBottomSheetDialog
import com.wswon.blanc.ui.component.dialog.SheetItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SomeoneDiaryScreen() {
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
        type = DialogType.DeleteMyDiary,
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

    Column(
        modifier = Modifier
            .padding(top = 16.dp)
            .verticalScroll(rememberScrollState())
            .semantics { contentDescription = "SomeoneYesterday Screen" }
    ) {
        val pages = listOf("1", "2", "3")
        HorizontalPager(
            pageCount = pages.size,
            modifier = Modifier,
            pageSpacing = 8.dp,
            contentPadding = PaddingValues(horizontal = 24.dp),
        ) { page ->
            Diary(
                modifier = Modifier.fillMaxWidth(),
                backgroundBrush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFE7E7),
                        Color(0xFFFED8D8)
                    )
                ),
                onClickMore = {
                    isBottomSheetDialogOpen = true
                },
                content = {
                    MyComments()
                }
            )
        }
    }
}

@Composable
private fun MyComments() {
    var isShowDeleteDialog by rememberSaveable { mutableStateOf(false) }
    var isBottomSheetDialogOpen by rememberSaveable { mutableStateOf(false) }

    BlancDialog(
        type = DialogType.DeleteMyDiary,
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
            SheetItem.Delete
        ),
        isOpen = isBottomSheetDialogOpen,
        onClickItem = { item ->
            if (item is SheetItem.Delete) {
                isShowDeleteDialog = true
            }

            isBottomSheetDialogOpen = false
        },
        onClose = {
            isBottomSheetDialogOpen = false
        }
    )

    Diary(
        modifier = Modifier
            .padding(top = 32.dp)
            .fillMaxWidth(),
        backgroundBrush = Brush.verticalGradient(
            colors = listOf(
                Color.White.copy(alpha = 0.8f),
                Color.White.copy(alpha = 0.8f)
            )
        ),
        onClickMore = {
            isBottomSheetDialogOpen = true
        },
    )
}