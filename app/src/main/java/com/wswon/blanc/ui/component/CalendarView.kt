package com.wswon.blanc.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.wswon.blanc.R
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

@Composable
fun BlancCalendarView(
    modifier: Modifier,
    currentMonth: YearMonth,
    dataList: List<LocalDateTime>
) {
    val locale = Locale.KOREA

    // 현재 월의 첫 번째 날짜를 계산합니다.
    val firstMonthDay = currentMonth.atDay(1)

    // 현재 월의 마지막 날짜를 계산합니다.
    val lastMonthDay = currentMonth.atEndOfMonth()

    // 이전 달의 마지막 날짜를 계산합니다.
    val previousMonth = currentMonth.minusMonths(1)
    val daysInPreviousMonth = previousMonth.lengthOfMonth()
    val startDayFromPreviousMonth = firstMonthDay.dayOfWeek.getValue(DayOfWeek.SUNDAY)

    // 다음 달의 첫 번째 날짜를 계산합니다.
    val nextMonth = currentMonth.plusMonths(1)
    val startDayFromNextMonth = lastMonthDay.dayOfWeek.getValue(DayOfWeek.SUNDAY)

    val hasDiaryData: (LocalDateTime) -> Boolean = { date ->
        dataList.any {
            it.year == date.year
                && it.month == date.month
                && it.dayOfMonth == date.dayOfMonth
        }
    }

    Column(
        modifier = modifier.padding(horizontal = 24.dp)
    ) {
        // 월의 이름을 표시합니다.
        Text(
            text = currentMonth.format(
                DateTimeFormatter.ofPattern(
                    stringResource(R.string.year_month_format),
                    locale
                )
            ),
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // 요일 이름을 표시합니다.
        val dayOfWeeks = listOf(DayOfWeek.values().last()) + DayOfWeek.values().dropLast(1)
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            for (dayOfWeek in dayOfWeeks) {
                Text(
                    text = dayOfWeek.getDisplayName(TextStyle.SHORT, locale),
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(1f)
                )
            }
        }

        // 이전 달의 마지막 주를 표시합니다.
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ((daysInPreviousMonth - startDayFromPreviousMonth + 2)..daysInPreviousMonth).forEach { day ->
                DayBox(
                    modifier = Modifier.weight(1f),
                    day = Day(
                        yearMonth = previousMonth,
                        day = day
                    ),
                    hasItem = false,
                    enabled = false,
                )
            }

            // 이번 달의 첫 번째 주를 표시합니다.
            for (day in 1..7 - startDayFromPreviousMonth + 1) {
                DayBox(
                    modifier = Modifier.weight(1f),
                    day = Day(
                        yearMonth = currentMonth,
                        day = day
                    ),
                    hasItem = hasDiaryData(getLocalDateTime(currentMonth, day)),
                )
            }
        }

        // 이번 달의 주를 표시합니다.
        for (weekOfMonth in 2..5) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                for (dayOfWeek in dayOfWeeks) {
                    val day =
                        (weekOfMonth - 1) * 7 + dayOfWeek.getValue(DayOfWeek.SUNDAY) - startDayFromPreviousMonth + 1
                    if (day <= lastMonthDay.dayOfMonth) {
                        DayBox(
                            modifier = Modifier.weight(1f),
                            day = Day(
                                yearMonth = currentMonth,
                                day = day
                            ),
                            hasItem = hasDiaryData(getLocalDateTime(currentMonth, day)),
                        )
                    } else {
                        DayBox(
                            modifier = Modifier.weight(1f),
                            day = Day(
                                yearMonth = nextMonth,
                                day = (day - lastMonthDay.dayOfMonth)
                            ),
                            hasItem = false,
                            enabled = false
                        )
                    }
                }
            }
        }

//        // 다음 달의 첫 번째 주를 표시합니다.
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 16.dp)
//        ) {
//            for (day in 1..7 - startDayFromNextMonth) {
//                DayBox(modifier = Modifier.weight(1f), text = day.toString(), enabled = false)
//            }
//        }
    }
}

@Composable
private fun getLocalDateTime(
    previousMonth: YearMonth,
    day: Int
) = LocalDateTime.of(
    previousMonth.year,
    previousMonth.month,
    day, 0, 0
)

private fun DayOfWeek.getValue(startDayOfWeek: DayOfWeek): Int {
    val value = value + (8 - startDayOfWeek.value)
    return if (value > 7) value - 7 else value
}


data class Day(
    val yearMonth: YearMonth,
    val day: Int,
) {

    fun isToday(): Boolean {
        return YearMonth.now() == yearMonth
            && LocalDate.now().dayOfMonth == yearMonth.atDay(day).dayOfMonth
    }
}


@Composable
fun DayBox(
    modifier: Modifier = Modifier,
    day: Day,
    hasItem: Boolean,
    enabled: Boolean = true,
) {
    val backgroundColor =
        if (enabled) MaterialTheme.colors.secondary else Color.Transparent
    val textColor =
        if (enabled) MaterialTheme.colors.primary else Color.Transparent

    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clickable(enabled) { /* 클릭 이벤트 처리 */ },
        contentAlignment = Alignment.Center
    ) {
        val style =
            if (day.isToday()) MaterialTheme.typography.subtitle2 else MaterialTheme.typography.body2
        if (hasItem) {
            Box(
                modifier = Modifier
                    .width(28.dp)
                    .height(28.dp)
                    .background(color = backgroundColor, shape = CircleShape)
                    .align(Alignment.Center)
            )
        }
        Text(
            text = "${day.day}",
            color = textColor,
            style = style,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

