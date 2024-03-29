package com.wswon.blanc.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.wswon.blanc.R
import com.wswon.blanc.ui.theme.DefaultState

@Composable
fun InputDiary(
    modifier: Modifier = Modifier,
    backgroundBrush: Brush = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFE7EDFF),
            Color(0xFFD8E3FE)
        )
    ),
    inputTextMinHeight: Dp = 72.dp,
    maxCount: Int = 100,
    dateLabel: String = "",
    onClickSend: () -> Unit = {}
) {
    ConstraintLayout(
        modifier
            .widthIn(max = 327.dp)
            .background(
                brush = backgroundBrush,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(24.dp)
    ) {
        var textCount by rememberSaveable { mutableStateOf(0) }

        val (date, inputText, textCounter, sendButton) = createRefs()

        if (dateLabel.isNotEmpty()) {
            Text(
                text = dateLabel,
                modifier = Modifier.constrainAs(date) {
                },
                style = MaterialTheme.typography.caption
            )
        }

        InputText(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = inputTextMinHeight)
                .constrainAs(inputText) {
                    top.linkTo(date.bottom, 30.dp)
                },
            onTextChanged = { text ->
                textCount = text.length
            }
        )

        TextCounter(
            modifier = Modifier.constrainAs(textCounter) {
                top.linkTo(sendButton.top)
                bottom.linkTo(sendButton.bottom)
                start.linkTo(parent.start)
            },
            textCount = textCount,
            maxCount = maxCount,
        )

        val sendEnabled = textCount >= 10
        SendButton(
            modifier = Modifier.constrainAs(sendButton) {
                top.linkTo(inputText.bottom, 32.dp)
                end.linkTo(parent.end)
            },
            enabled = sendEnabled,
            onClickSend = onClickSend
        )
    }
}

@Composable
private fun TextCounter(
    modifier: Modifier,
    textCount: Int,
    maxCount: Int = 100
) {
    Text(
        text = AnnotatedString(
            text = "${textCount}/${maxCount}",
            spanStyle = SpanStyle(
                textDecoration = TextDecoration.Underline
            )
        ),
        modifier = modifier,
        color = MaterialTheme.colors.primary,
        style = MaterialTheme.typography.caption
    )
}

@Composable
private fun SendButton(
    modifier: Modifier,
    enabled: Boolean,
    onClickSend: () -> Unit
) {
    Row(
        modifier = modifier
            .clickable(
                enabled,
                onClick = {
                    onClickSend()
                }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val color = if (enabled) MaterialTheme.colors.primary else DefaultState
        Text(
            text = stringResource(com.wswon.blanc.R.string.send),
            color = color,
            style = MaterialTheme.typography.button
        )
        val icon =
            if (enabled) R.drawable.ic_arrow_right_circle_enabled else R.drawable.ic_arrow_right_circle_disabled
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.padding(start = 8.dp),
            tint = Color.Unspecified
        )
    }
}


@Composable
fun InputText(modifier: Modifier, onTextChanged: (String) -> Unit) {
    var value by rememberSaveable { mutableStateOf("") }

    BasicTextField(
        value = value,
        onValueChange = {
            value = it
            onTextChanged(it)
        },
        textStyle = MaterialTheme.typography.body2,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        decorationBox = { innerTextField ->
            if (value.isEmpty()) {
                Text(
                    stringResource(R.string.today_input_hint),
                    style = MaterialTheme.typography.body2,
                    color = DefaultState,
                )
            }
            innerTextField()
        },
        modifier = modifier
    )
}

