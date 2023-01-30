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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.wswon.blanc.R

@Preview
@Composable
fun Diary(
    modifier: Modifier = Modifier,
    backgroundBrush: Brush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFE7EDFF),
            Color(0xFFD8E3FE)
        )
    ),
    onClickMore: () -> Unit = {},
    content: @Composable () -> Unit = {},
) {

    Column(
        modifier = modifier
            .widthIn(max = 327.dp)
            .background(
                brush = backgroundBrush,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {

            val (date, contentText, moreButton) = createRefs()

            Text(
                text = "2023년 1월 22일",
                modifier = Modifier.constrainAs(date) {
                },
                style = MaterialTheme.typography.caption
            )

            Text(
                text = "오늘은 상사에게 후배에게 하루종일 시달려서 지쳤어요. 중간에 껴서 새우등 터지고 있는데 어디가서 말해봤자 제 이미지만 안좋아지겠죠?",
                style = MaterialTheme.typography.body2,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(contentText) {
                        top.linkTo(date.bottom, 30.dp)
                        bottom.linkTo(moreButton.top, 30.dp)
                    }
            )

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