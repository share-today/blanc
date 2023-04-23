package com.wswon.blanc.ui.myyesterday

import androidx.lifecycle.ViewModel
import com.wswon.blanc.ui.component.DiaryState
import com.wswon.blanc.ui.component.state.LikeButtonState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class MyYesterdayUiState(
    val diary: DiaryState?,
    val comments: List<DiaryState>
) {

    fun updateLike(diaryId: String): MyYesterdayUiState {
        return copy(comments = comments.map { diaryState ->
            if (diaryState.id == diaryId) {
                diaryState.copy(likeButtonState = diaryState.likeButtonState?.copy(isLike = !diaryState.likeButtonState.isLike))
            } else diaryState
        })
    }

    companion object {
        val Empty = MyYesterdayUiState(null, emptyList())
    }
}


@HiltViewModel
class MyYesterdayViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(MyYesterdayUiState.Empty)
    val uiState: StateFlow<MyYesterdayUiState> = _uiState.asStateFlow()

    init {
        _uiState.value = MyYesterdayUiState(
            DiaryState(
                id = "1",
                dateLabel = "2023년 3월 11일",
                content = "오늘은 상사에게 후배에게 하루종일 시달려서 지쳤어요. 중간에 껴서 새우등 터지고 있는데 어디가서 말해봤자 제 이미지만 안좋아지겠죠?",
                likeButtonState = null,
                background = DiaryState.Background.BlueGradient
            ),
            listOf(
                DiaryState(
                    id = "1",
                    dateLabel = "",
                    content = "1 하고싶은 일이 있는데 뜻대로 되지 않아요. 친구들은 그저 제 배경만 보고 부러워 하지만 그 안에서의 저는 죽을 맛입니다.",
                    likeButtonState = LikeButtonState(isLike = false, isEnabled = true),
                    background = DiaryState.Background.LightGrayGradientWithStroke
                ),
                DiaryState(
                    id = "2",
                    dateLabel = "",
                    content = "2 하고싶은 일이 있는데 뜻대로 되지 않아요. 친구들은 그저 제 배경만 보고 부러워 하지만 그 안에서의 저는 죽을 맛입니다.",
                    likeButtonState = LikeButtonState(isLike = false, isEnabled = true),
                    background = DiaryState.Background.LightGrayGradientWithStroke
                ),
                DiaryState(
                    id = "3",
                    dateLabel = "",
                    content = "3 하고싶은 일이 있는데 뜻대로 되지 않아요. 친구들은 그저 제 배경만 보고 부러워 하지만 그 안에서의 저는 죽을 맛입니다.",
                    likeButtonState = LikeButtonState(isLike = false, isEnabled = true),
                    background = DiaryState.Background.LightGrayGradientWithStroke
                ),
            )
        )
    }

    fun onClickLike(id: String) {
        _uiState.update {
            it.updateLike(id)
        }
    }


}