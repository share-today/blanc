package com.wswon.blanc.util

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.wswon.blanc.R
import com.wswon.blanc.ui.theme.Small
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import java.util.*

@Composable
fun CustomSnackBar(
    snackbarData: SnackbarData,
    onClickAction: () -> Unit,
    isRtl: Boolean = false,
    containerColor: Color = Color.Black
) {
    Snackbar(
        modifier = Modifier.padding(horizontal = 24.dp, vertical = 14.dp),
        backgroundColor = containerColor
    ) {
        CompositionLocalProvider(
            LocalLayoutDirection provides
                if (isRtl) LayoutDirection.Rtl else LayoutDirection.Ltr
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (snackbarData.icon != null) {
                        Icon(
                            painterResource(id = snackbarData.icon),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                    }
                    Text(
                        text = snackbarData.getMessage(LocalContext.current),
                        style = Small,
                        color = Color.Black,
                    )
                }

                val action = snackbarData.getAction(LocalContext.current)
                if (action.isNotEmpty()) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .clickable(true, onClick = onClickAction),
                        text = action,
                        style = MaterialTheme.typography.button,
                        color = Color.Black,
                    )
                }
            }
        }
    }
}


data class SnackbarData(
    private val id: String = UUID.randomUUID().toString(),
    @DrawableRes val icon: Int? = null,
    private val message: String? = null,
    @StringRes private val messageId: Int? = null,
    private val action: String? = null,
    @StringRes private val actionId: Int? = null,
) {

    fun getMessage(context: Context): String {
        return message ?: context.getString(messageId ?: return "")
    }

    fun getAction(context: Context): String {
        return action ?: context.getString(actionId ?: return "")
    }
}

object SnackbarProvider {

    private val _data = MutableSharedFlow<SnackbarData>()
    val data: SharedFlow<SnackbarData> = _data.asSharedFlow()

    suspend fun show(type: SnackbarType) {
        _data.emit(
            SnackbarData(
                icon = type.icon,
                messageId = type.message,
                actionId = type.actionLabel
            )
        )
    }

    fun show(message: String) {
        _data.tryEmit(
            SnackbarData(
                message = message
            )
        )
    }

    fun show(
        @DrawableRes icon: Int,
        message: String,
        actionLabel: String?
    ) {
        _data.tryEmit(
            SnackbarData(
                icon = icon,
                message = message,
                action = actionLabel
            )
        )
    }

}


enum class SnackbarType(
    @DrawableRes val icon: Int,
    @StringRes val message: Int,
    @StringRes val actionLabel: Int?
) {

    SendComplete(
        R.drawable.ic_send,
        R.string.message_send_complete,
        null
    ),
    EditComplete(
        R.drawable.ic_edit,
        R.string.message_edit_complete,
        null
    ),
    ReportComplete(
        R.drawable.ic_report,
        R.string.message_report_complete,
        null
    ),
    DeleteComplete(
        R.drawable.ic_delete,
        R.string.message_delete_complete,
        null
    ),
    OpinionComplete(
        R.drawable.ic_report,
        R.string.message_opinion_complete,
        null
    ),
    WatchingAds(
        R.drawable.ic_tv,
        R.string.message_watching_ads,
        R.string.action_watching_ads
    ),
    LikeComplete(
        R.drawable.ic_heart_fill,
        R.string.message_like_complete,
        null
    )

}