package com.iliadavidovich.moviehub.Classes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iliadavidovich.moviehub.Interfaces.HomeState
import com.iliadavidovich.moviehub.R
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel:ViewModel() {

   suspend fun onClickRemoveComment(filmComment: FilmComment) = CommentRepositryImpl.delete(filmComment.id)

   val state: StateFlow<HomeState> = CommentRepositryImpl.getComments()
       .map { data ->
           when{
               data.isEmpty() -> HomeState.Empty
               else -> HomeState.DisplayingFilmComments(data)
           }
       }.stateIn(viewModelScope, SharingStarted.Lazily, HomeState.Loading)

    companion object{
        val film = Film(title = "Scream 4" ,
            budget = "20000000" ,
            cast = "Popular actros",
            collection = "30000000",
            country = "USA" ,
            description = "Very scary movie!" ,
            duration = "90" ,
            genre = "Horror",
            rating = "7" ,
            realeseYear = "2003",
            image = R.drawable.poster_default);

        val DefaultFilmComments = listOf(
            FilmComment(film,"So so "),
            FilmComment(film, "Very good!"),
            FilmComment(film, "Sh*t")
        )
    }
}