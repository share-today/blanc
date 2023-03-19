package com.wswon.blanc.ui.component.state

import androidx.annotation.DrawableRes
import com.wswon.blanc.R

data class LikeButtonState(
    val isLike: Boolean,
    val isEnabled: Boolean
) {
    @DrawableRes
    val iconHeart: Int = if (isLike) R.drawable.ic_heart_fill else R.drawable.ic_heart_empty
}