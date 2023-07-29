package com.blanc.data.repository

import com.blanc.data.remote.DiaryRemoteDataSource
import com.blanc.domain.diary.entity.Diary
import com.blanc.domain.diary.entity.DiaryId
import com.blanc.domain.user.entity.UserId
import com.blanc.domain.user.repository.DiaryRepository
import java.text.DateFormat
import javax.inject.Inject

class DiaryRepositoryImpl @Inject constructor(
    private val diaryRemoteDataSource: DiaryRemoteDataSource
) : DiaryRepository {

    override suspend fun createDiary(content: String): DiaryId {
        return diaryRemoteDataSource.createDiary(content)
    }

    override suspend fun getDiary(id: DiaryId): Diary {
        val diary = diaryRemoteDataSource.getDiary(id)
        val dateFormat = DateFormat.getDateInstance()

        return Diary(
            DiaryId(diary.id),
            UserId(diary.userId),
            diary.content.orEmpty(),
            diary.likeCount,
            diary.createdAt?.let { dateFormat.parse(it) },
            diary.modifiedAt?.let { dateFormat.parse(it) },
            diary.removedAt?.let { dateFormat.parse(it) }
        )
    }

}