package com.iliadavidovich.moviehub.Classes

import com.iliadavidovich.moviehub.Interfaces.CommentDataSource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import java.util.UUID

object MemoryCommentsDataSource: CommentDataSource {

    private val comments = HomeViewModel.DefaultFilmComments.associateBy { it.id }.toMutableMap()

    private val _Film_commentFlow = MutableSharedFlow<Map<UUID, FilmComment>>(1)
    override fun getComments(): Flow<List<FilmComment>> {
        return _Film_commentFlow
            .asSharedFlow()
            .map { it.values.toList() }
            .onStart {
                delay(1000L)
                emit(comments.values.toList())
            }
    }

    override fun getComment(id: UUID): Flow<FilmComment?> {
        return _Film_commentFlow
            .asSharedFlow()
            .map { it[id] }
            .onStart {
                delay(1000L)
                emit(comments[id])
            }
    }

    override suspend fun upsert(filmComment: FilmComment) {
        comments[filmComment.id] = filmComment
        _Film_commentFlow.emit(comments)
    }

    override suspend fun delete(id: UUID) {
        comments.remove(id)
        _Film_commentFlow.emit(comments)
    }
}