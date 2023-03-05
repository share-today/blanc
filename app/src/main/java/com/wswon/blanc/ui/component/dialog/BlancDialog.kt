package com.wswon.blanc.ui.component.dialog

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.wswon.blanc.R
import com.wswon.blanc.ui.theme.Small

enum class DialogType(
    @DrawableRes
    val iconResId: Int,
    @StringRes
    val titleResId: Int,
    @StringRes
    val submitButtonResId: Int,
    @StringRes
    val cancelButtonResId: Int
) {
    Advertise(
        R.drawable.ic_tv,
        R.string.dialog_ads,
        R.string.dialog_button_watching_ads,
        R.string.cancel
    ),
    Report(
        R.drawable.ic_unpleasant,
        R.string.dialog_report,
        R.string.dialog_button_report,
        R.string.cancel
    ),
    Logout(
        R.drawable.ic_warning,
        R.string.dialog_logout,
        R.string.dialog_button_logout,
        R.string.cancel
    ),
    Leave(
        R.drawable.ic_warning,
        R.string.dialog_leave,
        R.string.dialog_button_leave,
        R.string.cancel
    ),
    SendToday(
        R.drawable.ic_send,
        R.string.dialog_send_today,
        R.string.dialog_button_send,
        R.string.cancel
    ),
    SendEditComments(
        R.drawable.ic_send,
        R.string.dialog_send_edit_comments,
        R.string.dialog_button_send,
        R.string.cancel
    ),
    SendComments(
        R.drawable.ic_send,
        R.string.dialog_send_comments,
        R.string.dialog_button_send,
        R.string.cancel
    ),
    SendOpinion(
        R.drawable.ic_send,
        R.string.dialog_send_opinion,
        R.string.dialog_button_send,
        R.string.cancel
    ),
    EditToday(
        R.drawable.ic_edit,
        R.string.dialog_edit_today,
        R.string.dialog_button_edit,
        R.string.cancel
    ),
    EditYesterday(
        R.drawable.ic_edit,
        R.string.dialog_edit_yesterday,
        R.string.dialog_button_edit,
        R.string.cancel
    ),
    EditComments(
        R.drawable.ic_edit,
        R.string.dialog_edit_comments,
        R.string.dialog_button_edit,
        R.string.cancel
    ),
    DeleteTodayDiary(
        R.drawable.ic_delete,
        R.string.dialog_delete_today_diary,
        R.string.dialog_button_delete,
        R.string.cancel
    ),
    DeleteMyDiary(
        R.drawable.ic_delete,
        R.string.dialog_delete_my_diary,
        R.string.dialog_button_delete,
        R.string.cancel
    ),
    DeleteOthers(
        R.drawable.ic_delete,
        R.string.dialog_delete_others,
        R.string.dialog_button_delete,
        R.string.cancel
    );
}

@Composable
fun BlancDialog(
    type: DialogType,
    isOpen: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    if (isOpen) {
        BlancDialog(
            icon = type.iconResId,
            message = stringResource(id = type.titleResId),
            confirmText = stringResource(id = type.submitButtonResId),
            onConfirm = onConfirm,
            dismissText = stringResource(id = type.cancelButtonResId),
            onDismiss = onDismiss
        )
    }
}

@Composable
fun BlancDialog(
    @DrawableRes icon: Int,
    message: String,
    confirmText: String = "확인",
    onConfirm: () -> Unit,
    dismissText: String = "취소",
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties()
    ) {
        BlancDialogContent(
            buttons = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 41.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = dismissText,
                        style = Small,
                        modifier = Modifier
                            .clickable(true, onClick = onDismiss)
                            .widthIn(min = 55.dp)
                            .padding(horizontal = 14.dp)
                            .padding(top = 11.dp, bottom = 8.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = confirmText,
                        color = Color.White,
                        style = Small,
                        modifier = Modifier
                            .background(Color.Black, shape = RoundedCornerShape(4.dp))
                            .clickable(true, onClick = onConfirm)
                            .widthIn(min = 55.dp)
                            .padding(horizontal = 14.dp)
                            .padding(top = 11.dp, bottom = 8.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            },
            shape = RoundedCornerShape(8.dp),
            icon = painterResource(id = icon),
            text = {
                Text(
                    text = message,
                    style = MaterialTheme.typography.body2
                )
            },
            backgroundColor = Color.White,
            modifier = Modifier
//                .padding(horizontal = 29.dp)
//                .widthIn(max = 316.dp)
                .fillMaxWidth(),
            contentPadding = PaddingValues(24.dp)
        )
    }
}

@Composable
fun BlancDialogContent(
    buttons: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    icon: Painter,
    text: @Composable (() -> Unit)? = null,
    shape: Shape = MaterialTheme.shapes.medium,
    backgroundColor: Color = MaterialTheme.colors.surface,
    contentColor: Color = contentColorFor(backgroundColor),
    contentPadding: PaddingValues,
) {

    Surface(
        modifier = modifier,
        shape = shape,
        color = backgroundColor,
        contentColor = contentColor
    ) {
        Column(modifier = Modifier.padding(contentPadding)) {
            Icon(painter = icon, contentDescription = null)
            Spacer(modifier = Modifier.height(16.dp))
            text?.invoke()
            Spacer(modifier = Modifier.height(32.dp))
            buttons()
        }
    }
}
