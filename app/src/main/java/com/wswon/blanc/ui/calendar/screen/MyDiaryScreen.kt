package com.wswon.blanc.ui.calendar.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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

@Composable
fun MyDiaryScreen() {
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
            .padding(start = 24.dp, top = 16.dp, end = 24.dp)
            .verticalScroll(rememberScrollState())
            .semantics { contentDescription = "MyDiary Screen" }
    ) {
        Diary(
            modifier = Modifier.fillMaxWidth(),
            diaryState = DiaryState(
                id = "1",
                dateLabel = "2023년 3월 11일",
                content = "오늘은 상사에게 후배에게 하루종일 시달려서 지쳤어요. 중간에 껴서 새우등 터지고 있는데 어디가서 말해봤자 제 이미지만 안좋아지겠죠?",
                likeButtonState = null,
                background = DiaryState.Background.BlueGradient
            ),
            onClickLike = {

            },
            onClickMore = {
                isBottomSheetDialogOpen = true
            }
        )
    }
}