package com.wswon.blanc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blanc.data.service.ShareTodayApi
import com.wswon.blanc.util.SnackbarData
import com.wswon.blanc.util.SnackbarProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val shareTodayApi: ShareTodayApi
) : ViewModel() {

    val snackbarData: SharedFlow<SnackbarData> = SnackbarProvider.data

    init {
        viewModelScope.launch {
//            shareTodayApi.getDiaryList()
//                .also {
//                    WLog.d("$it")
//                }
//
//            shareTodayApi.getYesterdayDiary()
//                .also {
//                    WLog.d("$it")
//                }
//
//            shareTodayApi.getSomeoneDiary()
//                .also {
//                    WLog.d("$it")
//                }
//
//            shareTodayApi.postDiary(DiaryRequest("보냅니다 보내요"))
//                .also {
//                    WLog.d("$it")
//                }
//
//            shareTodayApi.getTodayDiary()
//                .also {
//                    WLog.d("$it")
//                }

        }
    }
}