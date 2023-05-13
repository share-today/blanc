package com.wswon.blanc.ui.login

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wswon.blanc.R
import com.wswon.blanc.ui.theme.SubBackground

@Composable
fun LoginScreen(onClickLoginButton: (LoginButtonType) -> Unit) {

    Box(
        modifier = Modifier
            .padding(bottom = 56.dp)
            .fillMaxWidth()
            .fillMaxHeight()
    ) {

        Box(
            modifier = Modifier
                .padding(top = 164.dp)
                .widthIn(min = 224.dp)
                .heightIn(min = 86.dp)
                .border(1.dp, Color.Black)
                .align(Alignment.TopCenter),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.h1,
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_logo_sample), contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(start = 204.dp, top = 220.dp)
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement
                .spacedBy(16.dp)
        ) {
            LoginButton(
                Modifier.fillMaxWidth(),
                LoginButtonType.Kakao,
                onClick = {
                    onClickLoginButton(it)
                }
            )
            LoginButton(
                Modifier.fillMaxWidth(),
                LoginButtonType.Google,
                onClick = {
                    onClickLoginButton(it)
                }
            )
            LoginButton(
                Modifier.fillMaxWidth(),
                LoginButtonType.Apple,
                onClick = {
                    onClickLoginButton(it)
                }
            )
        }
    }
}

enum class LoginType {
    Kakao, Google, Apple
}

enum class LoginButtonType(
    val loginType: LoginType,
    @StringRes val textResId: Int,
    @DrawableRes val iconResId: Int,
    val backgroundColor: Color,
    val textColor: Color,
    val stroke: Stroke?
) {
    Kakao(
        LoginType.Kakao,
        R.string.login_kakao,
        R.drawable.ic_kakao,
        Color(0xFFF8EB4F),
        Color.Black,
        null
    ),
    Google(
        LoginType.Google,
        R.string.login_google,
        R.drawable.ic_google,
        Color.White,
        Color.Black,
        Stroke(SubBackground, 2.dp)
    ),
    Apple(
        LoginType.Apple,
        R.string.login_apple,
        R.drawable.ic_apple,
        Color.Black,
        Color.White,
        null
    );

    data class Stroke(
        val color: Color,
        val width: Dp
    )
}

@Preview
@Composable
fun LoginButton(
    modifier: Modifier = Modifier,
    type: LoginButtonType = LoginButtonType.Kakao,
    onClick: (LoginButtonType) -> Unit = {}
) {
    Button(
        onClick = {
            onClick(type)
        },
        modifier = modifier
            .heightIn(min = 56.dp),
        border = type.stroke?.let {
            BorderStroke(it.width, it.color)
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = type.backgroundColor,
            contentColor = Color.Unspecified
        ),
        shape = RoundedCornerShape(4.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        elevation = null
    ) {
        Icon(painter = painterResource(id = type.iconResId), contentDescription = null)
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = stringResource(id = type.textResId),
            color = type.textColor,
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier
        )
    }
}