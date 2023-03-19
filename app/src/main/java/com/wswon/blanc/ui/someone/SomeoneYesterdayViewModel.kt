package com.wswon.blanc.ui.someone

import androidx.lifecycle.ViewModel
import com.wswon.blanc.ui.component.DiaryState
import com.wswon.blanc.ui.component.state.LikeButtonState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject


data class SomeoneYesterdayUiState(
    val diaryList: List<DiaryState>
) {

    fun updateLike(diaryId: String): SomeoneYesterdayUiState {
        return copy(diaryList = diaryList.map { diaryState ->
            if (diaryState.id == diaryId) {
                diaryState.copy(likeButtonState = diaryState.likeButtonState?.copy(isLike = !diaryState.likeButtonState.isLike))
            } else diaryState
        })
    }

    companion object {
        val Empty = SomeoneYesterdayUiState(emptyList())
    }
}

@HiltViewModel
class SomeoneYesterdayViewModel @Inject constructor() : ViewModel() {

    private val _someone = MutableStateFlow(SomeoneYesterdayUiState.Empty)
    val someone: StateFlow<SomeoneYesterdayUiState> = _someone.asStateFlow()

    init {
        _someone.value = SomeoneYesterdayUiState(
            listOf(
                DiaryState(
                    id = "1",
                    dateLabel = "2023년 3월 11일",
                    content = "1 하고싶은 일이 있는데 뜻대로 되지 않아요. 친구들은 그저 제 배경만 보고 부러워 하지만 그 안에서의 저는 죽을 맛입니다.",
                    likeButtonState = LikeButtonState(isLike = false, isEnabled = true),
                    background = DiaryState.Background.PinkGradient
                ),
                DiaryState(
                    id = "2",
                    dateLabel = "2023년 3월 11일",
                    content = "2 하고싶은 일이 있는데 뜻대로 되지 않아요. 친구들은 그저 제 배경만 보고 부러워 하지만 그 안에서의 저는 죽을 맛입니다.",
                    likeButtonState = LikeButtonState(isLike = false, isEnabled = true),
                    background = DiaryState.Background.PinkGradient
                ),
                DiaryState(
                    id = "3",
                    dateLabel = "2023년 3월 11일",
                    content = "3 하고싶은 일이 있는데 뜻대로 되지 않아요. 친구들은 그저 제 배경만 보고 부러워 하지만 그 안에서의 저는 죽을 맛입니다.",
                    likeButtonState = LikeButtonState(isLike = false, isEnabled = true),
                    background = DiaryState.Background.PinkGradient
                ),
            )
        )
    }

    fun onClickLike(id: String) {
        _someone.update {
            it.updateLike(id)
        }
    }

}