package com.wswon.blanc.ui.calendar

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.wswon.blanc.navigateToDiaryDetail
import com.wswon.blanc.ui.component.BlancCalendarView

@Composable
fun CalendarScreen(
    viewModel: CalendarViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val state by viewModel.state.collectAsState()
    val monthList by derivedStateOf { state.monthList }

    LazyColumn(
        modifier = Modifier
    ) {
        items(monthList) { yearMonth ->
            BlancCalendarView(
                modifier = Modifier.padding(top = 26.dp, bottom = 14.dp),
                currentMonth = yearMonth,
                dataList = state.hasDataList,
                onClickDay = { day ->
                    navController.navigateToDiaryDetail(day)
                }
            )
        }
    }
}
