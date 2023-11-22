package com.iliadavidovich.moviehub.Interfaces

import com.iliadavidovich.moviehub.Classes.FilmComment
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface CommentRepositry {
    fun getComments(): Flow<List<FilmComment>>
    fun getComment(id: UUID?): Flow<FilmComment?>

    suspend fun upsert(filmComment: FilmComment)
    suspend fun delete(id: UUID)
}