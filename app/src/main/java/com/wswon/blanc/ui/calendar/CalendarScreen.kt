package com.wswon.blanc.ui.calendar

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wswon.blanc.ui.component.BlancCalendarView
import java.time.YearMonth

@Composable
fun CalendarScreen(yearMonthList: List<YearMonth>) {
    LazyColumn(
        modifier = Modifier
    ) {
        items(yearMonthList) { yearMonth ->
            BlancCalendarView(
                modifier = Modifier.padding(top = 26.dp, bottom = 14.dp),
                yearMonth = yearMonth
            )
        }
    }
}
