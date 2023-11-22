package com.iliadavidovich.moviehub.Classes

import com.iliadavidovich.moviehub.Interfaces.CommentDataSource
import com.iliadavidovich.moviehub.Interfaces.CommentRepositry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.util.UUID

object CommentRepositryImpl : CommentRepositry {
    private val dataSource: CommentDataSource = MemoryCommentsDataSource
    override fun getComments(): Flow<List<FilmComment>> {
       return dataSource.getComments()
    }

    override fun getComment(id: UUID?): Flow<FilmComment?> {
        if (id == null)
        {
            return flowOf(null)
        }
        return dataSource.getComment(id)
    }

    override suspend fun upsert(filmComment: FilmComment) {
        dataSource.upsert(filmComment)
    }

    override suspend fun delete(id: UUID) {
        dataSource.delete(id = id)
    }
}