package com.iliadavidovich.moviehub.Interfaces

import com.iliadavidovich.moviehub.Classes.FilmComment

sealed interface HomeState {
    data object Loading : HomeState
    data object Empty: HomeState
    data class DisplayingFilmComments(val filmComments: List<FilmComment>): HomeState
    data class Error(val e: Exception) : HomeState
}