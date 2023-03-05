package com.wswon.blanc.ui.component.dialog

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BlancBottomSheetDialog(
    isOpen: Boolean,
    onClose: () -> Unit,
    content: @Composable () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = true,
    )
    var isFirst by rememberSaveable { mutableStateOf(true) }

    if (isOpen) {
        isFirst = true
        Dialog(
            onDismissRequest = onClose,
            properties = DialogProperties()
        ) {
            coroutineScope.launch {
                if (isFirst) {
                    modalSheetState.animateTo(ModalBottomSheetValue.Expanded)
                    isFirst = false
                }
                val alpha = if (modalSheetState.isVisible) 1f else 0f

                if (alpha == 0f) {
                    onClose()
                }
            }

            ModalBottomSheetLayout(
                modifier = Modifier.alpha(if (modalSheetState.isVisible) 1f else 0f),
                sheetState = modalSheetState,
                sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
                sheetContent = {
                    content()
                },
                scrimColor = Color.Transparent
            ) {

            }
        }
    }
}