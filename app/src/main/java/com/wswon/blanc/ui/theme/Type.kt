package com.wswon.blanc.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.wswon.blanc.R

// Set of Material typography styles to start with
//val Typography = Typography(
//    bodyLarge = TextStyle(
//        fontFamily = FontFamily.Default,
//        fontWeight = FontWeight.Normal,
//        fontSize = 16.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp
//    )
/* Other default text styles to override
titleLarge = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 22.sp,
    lineHeight = 28.sp,
    letterSpacing = 0.sp
),
labelSmall = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Medium,
    fontSize = 11.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.5.sp
)
*/
//)

private val SsurroundFontFamily = FontFamily(
    Font(R.font.cafe24_ssurround, FontWeight.Bold),
    Font(R.font.cafe24_ssurround_air, FontWeight.W300)
)

val Typography = Typography(
    defaultFontFamily = SsurroundFontFamily,
    h1 = TextStyle(
        fontFamily = SsurroundFontFamily,
        fontSize = 40.sp,
        fontWeight = FontWeight.Bold,
    ),
    h2 = TextStyle(
        fontFamily = SsurroundFontFamily,
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
    ),
    h3 = TextStyle(
        fontFamily = SsurroundFontFamily,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
    ),
    h4 = TextStyle(
        fontFamily = SsurroundFontFamily,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
    ),
    subtitle1 = TextStyle(
        fontFamily = SsurroundFontFamily,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
    ),
    subtitle2 = TextStyle(
        fontFamily = SsurroundFontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
    ),
    body1 = TextStyle(
        fontFamily = SsurroundFontFamily,
        fontSize = 20.sp,
        fontWeight = FontWeight.W300
    ),
    body2 = TextStyle(
        fontFamily = SsurroundFontFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.W300
    ),
    button = TextStyle(
        fontFamily = SsurroundFontFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
    ),
    caption = TextStyle(
        fontFamily = SsurroundFontFamily,
        fontSize = 12.sp,
        fontWeight = FontWeight.W300
    ),
)