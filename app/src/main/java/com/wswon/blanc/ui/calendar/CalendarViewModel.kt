package com.wswon.blanc.ui.calendar

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blanc.common.WLog
import com.blanc.data.service.ShareTodayApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.YearMonth
import javax.inject.Inject


data class CalendarUiState(
    val monthList: List<YearMonth>,
    val hasDataList: List<LocalDateTime>
) {
    fun updateDiaryData(list: List<LocalDateTime>): CalendarUiState {
        val minDate = list.min()

        val yearMonth = YearMonth.now()
        val minYearMonth = YearMonth.of(minDate.year, minDate.month)
        var iteratorYearMonth = yearMonth
        val yearMonthList = mutableListOf<YearMonth>()

        while (true) {
            yearMonthList.add(iteratorYearMonth)
            if (iteratorYearMonth == minYearMonth) break
            iteratorYearMonth = iteratorYearMonth.minusMonths(1)
        }
        return copy(
            monthList = yearMonthList,
            hasDataList = list
        )
    }

    companion object {
        val Default = CalendarUiState(
            listOf(YearMonth.now()),
            emptyList()
        )
    }
}

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val shareTodayApi: ShareTodayApi
) : ViewModel() {

    private val _state = MutableStateFlow(CalendarUiState.Default)
    val state: StateFlow<CalendarUiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            kotlin.runCatching {
                shareTodayApi.getDiaryList().dataList
                    ?.map {
                        val result = it.split(".")
                        LocalDateTime.of(
                            result[0].toInt(),
                            result[1].toInt(),
                            result[2].toInt(),
                            0,
                            0
                        )
                    }
                    .orEmpty()
            }
                .onSuccess { dateTimeList ->
                    _state.update {
                        it.updateDiaryData(dateTimeList)
                    }
                }
                .onFailure(WLog::e)
        }
    }
}