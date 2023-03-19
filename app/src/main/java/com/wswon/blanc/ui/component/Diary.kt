package com.wswon.blanc.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.wswon.blanc.R
import com.wswon.blanc.ui.component.state.LikeButtonState

data class DiaryState(
    val id: String,
    val dateLabel: String,
    val content: String,
    val likeButtonState: LikeButtonState?,
    private val background: Background,
) {

    val backgroundBrush: Brush = background.backgroundBrush

    enum class Background(val backgroundBrush: Brush) {
        BlueGradient(
            Brush.verticalGradient(
                colors = listOf(
                    Color(0xFFE7EDFF),
                    Color(0xFFD8E3FE)
                )
            )
        ),
        PinkGradient(
            Brush.verticalGradient(
                colors = listOf(
                    Color(0xFFFFE7E7),
                    Color(0xFFFED8D8)
                )
            )
        ),
        LightGrayGradient(
            Brush.verticalGradient(
                colors = listOf(
                    Color.White.copy(alpha = 0.8f),
                    Color.White.copy(alpha = 0.8f)
                )
            )
        )
    }

}


@Composable
fun Diary(
    modifier: Modifier = Modifier,
    diaryState: DiaryState,
    onClickLike: () -> Unit = {},
    onClickMore: () -> Unit = {},
    content: @Composable () -> Unit = {},
) {

    Column(
        modifier = modifier
            .widthIn(max = 327.dp)
            .background(
                brush = diaryState.backgroundBrush,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {

            val (date, contentText, heartButton, moreButton) = createRefs()

            Text(
                text = diaryState.dateLabel,
                modifier = Modifier.constrainAs(date) {
                },
                style = MaterialTheme.typography.caption
            )

            Text(
                text = diaryState.content,
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(contentText) {
                        top.linkTo(date.bottom, 30.dp)
                        bottom.linkTo(moreButton.top, 30.dp)
                    }
            )

            diaryState.likeButtonState?.let { likeButtonState ->
                LikeButton(
                    modifier = Modifier.constrainAs(heartButton) {
                        end.linkTo(moreButton.start, 16.dp)
                        bottom.linkTo(parent.bottom)
                    },
                    state = likeButtonState,
                    onClickLike = {
                        onClickLike()
                    }
                )
            }

            IconButton(
                onClick = onClickMore,
                modifier = Modifier
                    .size(24.dp)
                    .constrainAs(moreButton) {
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_more_horizontal),
                    contentDescription = "more"
                )
            }
        }
        content()
    }
}

@Composable
private fun LikeButton(
    modifier: Modifier,
    state: LikeButtonState,
    onClickLike: () -> Unit,
) {
    IconButton(
        onClick = onClickLike,
        enabled = state.isEnabled,
        modifier = modifier
            .size(24.dp),
    ) {
        Icon(
            painter = painterResource(id = state.iconHeart),
            contentDescription = "like",
            tint = Color.Unspecified
        )
    }
}