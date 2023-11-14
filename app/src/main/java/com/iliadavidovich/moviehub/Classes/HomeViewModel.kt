package com.iliadavidovich.moviehub.Classes

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.iliadavidovich.moviehub.R
class HomeViewModel:ViewModel() {
   val  comments: SnapshotStateList<Comment> = DefaultComments.toMutableStateList()

   fun onClickRemoveComment(comment: Comment) = comments.remove(comment)

    private companion object{
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

        private val DefaultComments = listOf(
            Comment(film,"So so "),
            Comment(film, "Very good!"),
            Comment(film, "Sh*t")
        )
    }
}