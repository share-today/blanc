package com.wswon.blanc.ui.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.wswon.blanc.BlancApp
import com.wswon.blanc.R

@Composable
fun ItemBottomSheetDialog(
    items: List<SheetItem>,
    isOpen: Boolean,
    onClickItem: (SheetItem) -> Unit,
    onClose: () -> Unit
) {
    BlancBottomSheetDialog(
        isOpen = isOpen,
        onClose = onClose
    ) {
        SheetContent(
            contents = items,
            onClick = onClickItem
        )
    }
}

sealed class SheetItem(val title: String) {

    object Report : SheetItem(BlancApp.getInstance().getString(R.string.dialog_button_report))

    object Edit : SheetItem(BlancApp.getInstance().getString(R.string.dialog_button_edit))

    object Delete : SheetItem(BlancApp.getInstance().getString(R.string.dialog_button_delete))

}

@Composable
private fun SheetContent(
    contents: List<SheetItem>,
    modifier: Modifier = Modifier,
    onClick: (SheetItem) -> Unit
) {
    val itemSpace = 16.dp
    LazyColumn(
        modifier = modifier
            .background(Color.White)
            .padding(top = itemSpace, bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(itemSpace)
    ) {
        items(contents) { item ->
            Button(
                onClick = {
                    onClick(item)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(48.dp),
                elevation = ButtonDefaults.elevation(0.dp, 0.dp, 0.dp, 0.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(Color.Transparent),
            ) {
                Box {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }
}