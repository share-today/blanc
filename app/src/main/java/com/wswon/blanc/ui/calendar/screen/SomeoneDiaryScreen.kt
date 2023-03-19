package com.wswon.blanc.ui.calendar.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.wswon.blanc.ui.component.Diary
import com.wswon.blanc.ui.component.DiaryState
import com.wswon.blanc.ui.component.dialog.BlancDialog
import com.wswon.blanc.ui.component.dialog.DialogType
import com.wswon.blanc.ui.component.dialog.ItemBottomSheetDialog
import com.wswon.blanc.ui.component.dialog.SheetItem
import com.wswon.blanc.ui.component.state.LikeButtonState

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
                diaryState = DiaryState(
                    id = "1",
                    dateLabel = "2023년 3월 11일",
                    content = "하고싶은 일이 있는데 뜻대로 되지 않아요. 친구들은 그저 제 배경만 보고 부러워 하지만 그 안에서의 저는 죽을 맛입니다.",
                    likeButtonState = LikeButtonState(isLike = false, isEnabled = true),
                    background = DiaryState.Background.PinkGradient
                ),
                onClickLike = {

                },
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
        diaryState = DiaryState(
            id = "1",
            dateLabel = "2023년 3월 11일",
            content = "저도 비슷한 상황을 겪어봐서 알아요. 그 누구도 나의 힘듬을 공감해주지 않는.. 화이팅",
            likeButtonState = LikeButtonState(isLike = true, isEnabled = false),
            background = DiaryState.Background.LightGrayGradient
        ),
        onClickLike = {

        },
        onClickMore = {
            isBottomSheetDialogOpen = true
        },
    )
}