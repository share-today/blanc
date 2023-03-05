package com.wswon.blanc.ui.myyesterday

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

@Composable
fun MyYesterdayScreen() {
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
            .padding(24.dp)
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
            ),
            onClickMore = {
                isBottomSheetDialogOpen = true
            }
        )
    }
}