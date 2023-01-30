package com.wswon.blanc

import android.content.res.Resources
import androidx.compose.ui.unit.Dp

val Int.dp: Float
    get() = this * (densityDpi / 160f)

val Int.sp: Float
    get() = this * scaledDensity

val Float.dp: Float
    get() = this * (densityDpi / 160f)

val Float.sp: Float
    get() = this * scaledDensity

val Int.px: Int
    get() = (this / (densityDpi / 160f)).toInt()

val Float.px: Int
    get() = (this / (densityDpi / 160f)).toInt()

private val densityDpi: Int
    get() = Resources.getSystem().displayMetrics.densityDpi

private val scaledDensity: Float
    get() = Resources.getSystem().displayMetrics.scaledDensity

fun Dp.toPx(): Float = value.dp