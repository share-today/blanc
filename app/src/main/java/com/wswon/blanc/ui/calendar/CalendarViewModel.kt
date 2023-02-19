package com.wswon.blanc.ui.calendar

import androidx.lifecycle.ViewModel
import com.blanc.common.WLog
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.YearMonth
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(): ViewModel() {

    val yearMonthList = mutableListOf<YearMonth>()
    init {
        val yearMonth = YearMonth.now()
        val pastOneYearMonth = yearMonth.minusYears(1)
        var iteratorYearMonth = YearMonth.now()

        while (true) {
            WLog.d("iteratorYearMonth $iteratorYearMonth")
            yearMonthList.add(iteratorYearMonth)
            if (iteratorYearMonth == pastOneYearMonth) break
            iteratorYearMonth = iteratorYearMonth.minusMonths(1)
        }
    }
}