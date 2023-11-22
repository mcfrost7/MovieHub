package com.iliadavidovich.moviehub.Classes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

sealed interface EditState {
    data object Loading : EditState

    data class DisplayBook(val filmComment: FilmComment?): EditState

}

class EditViewModule: ViewModel()
{
    suspend fun onClickSave(filmComment: FilmComment) = CommentRepositryImpl.upsert(filmComment)

    val state = MutableStateFlow<EditState>(EditState.Loading)

    fun setStateFlow(id: UUID?)
    {
        viewModelScope.launch {
            CommentRepositryImpl.getComment(id).collect { book ->
                state.value = EditState.DisplayBook(book)
            }
        }
    }
}