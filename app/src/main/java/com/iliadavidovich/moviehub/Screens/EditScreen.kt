package com.iliadavidovich.moviehub.Screens

import android.annotation.SuppressLint

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.compose.rememberNavController
import com.iliadavidovich.moviehub.Classes.EditState
import com.iliadavidovich.moviehub.Classes.EditViewModule
import com.iliadavidovich.moviehub.Classes.Film
import com.iliadavidovich.moviehub.R
import com.iliadavidovich.moviehub.ui.theme.LightYellow
import com.iliadavidovich.moviehub.Screens.*
import java.util.UUID
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.iliadavidovich.moviehub.Classes.*;
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditScreen( navController: NavController = rememberNavController(),
    id: UUID? = null
) {
    val viewModel = viewModel<EditViewModule>()
    
    viewModel.setStateFlow(id)
    val myState: State<EditState> = viewModel.state.collectAsStateWithLifecycle()

    EditScreenContent(
        navController,
        myState.value,
        ){
        filmComment ->
            viewModel.viewModelScope.launch {
                viewModel.onClickSave(filmComment = filmComment)
                navController.popBackStack()
            }
        }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditScreenContent(
    navController: NavController,
    stateValue: EditState,
    onSave: (FilmComment) -> Unit
){
    Scaffold(
        topBar = { EditTopAppBar(navController) },
        containerColor = Color(0xff1b1b23),
    ){
        values ->
        Column(Modifier.padding(values)) {
            when(stateValue){
                is EditState.Loading ->{
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Loading()
                    }
                }
                is EditState.DisplayBook->{
                    var newFilmComment = FilmComment(Film("0","0","0","0","0","0","0","0","0","0",0),"")
                    if (stateValue.filmComment != null){
                        newFilmComment = stateValue.filmComment
                    }
                    var title = newFilmComment.film.title
                    var duration= newFilmComment.film.duration
                    var genre = newFilmComment.film.genre
                    var releaseYear= newFilmComment.film.realeseYear
                    var country= newFilmComment.film.country
                    var rating= newFilmComment.film.rating
                    var cast= newFilmComment.film.cast
                    var description = newFilmComment.film.description
                    var budget= newFilmComment.film.budget
                    var collection= newFilmComment.film.collection
                    var image = newFilmComment.film.image
                    var notes = newFilmComment.notes

                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(horizontal = 10.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        title = Field(
                            mainText = title,
                            name = "Title",
                            icon = Icons.Filled.CheckCircle,
                            KeyboardOptions(keyboardType = KeyboardType.Text)
                        )
                            duration = Field(mainText = duration,name = "Duration", icon = Icons.Filled.CheckCircle, KeyboardOptions(keyboardType = KeyboardType.Text))
                            genre = Field(mainText = genre,name = "Genre", icon = Icons.Filled.CheckCircle, KeyboardOptions(keyboardType = KeyboardType.Text))
                            releaseYear = Field(mainText = releaseYear,name = "Realese", icon = Icons.Filled.CheckCircle, KeyboardOptions(keyboardType = KeyboardType.Text))
                            country = Field(mainText = country,name = "Country", icon = Icons.Filled.CheckCircle, KeyboardOptions(keyboardType = KeyboardType.Text))
                            rating = Field(mainText = rating,name = "Rating", icon = Icons.Filled.CheckCircle, KeyboardOptions(keyboardType = KeyboardType.Text))
                            cast = Field(mainText = cast,name = "Cast", icon = Icons.Filled.CheckCircle, KeyboardOptions(keyboardType = KeyboardType.Text))
                            description = Field(mainText = description,name = "Description", icon = Icons.Filled.CheckCircle, KeyboardOptions(keyboardType = KeyboardType.Text))
                            budget = Field(mainText = budget,name = "Budget", icon = Icons.Filled.CheckCircle, KeyboardOptions(keyboardType = KeyboardType.Text))
                            collection =  Field(mainText = collection,name = "Collection", icon = Icons.Filled.CheckCircle, KeyboardOptions(keyboardType = KeyboardType.Text))
                            image = R.drawable.poster_default
                            notes = Field(mainText = notes, name = "Notes",icon = Icons.Filled.CheckCircle, KeyboardOptions(keyboardType = KeyboardType.Text))
                        newFilmComment.notes = notes
                        Button(
                            onClick = {
                                onSave(
                                    FilmComment(
                                        Film(
                                            title,
                                            duration,
                                            genre,
                                            releaseYear,
                                            country,
                                            rating,
                                            cast,
                                            description,
                                            budget,
                                            collection,
                                            image
                                        ),
                                        description,
                                        id = stateValue.filmComment?.id ?: UUID.randomUUID()
                                    )
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 100.dp)
                        ) {
                            Text(stringResource(R.string.save))
                        }
                    }
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTopAppBar(navController: NavController) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Black
        ),
        navigationIcon = {
            IconButton(onClick = {
                navController.navigate("HomeScreen")
                {
                    popUpTo("HomeScreen")
                    {
                        inclusive = true
                    }
                }
            }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "Navigation Menu",
                    tint = LightYellow
                )
            }
        },
        title = {
            Navigation
            Text(
                stringResource(R.string.edit_screen),
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 24.sp
            )
        }
    )
}
@Composable
fun Field(mainText: String ="",
          name: String = "",
          icon : ImageVector,
          keyboardOptions: KeyboardOptions = KeyboardOptions.Default): String{
    var text by remember {
        mutableStateOf(mainText)
    }
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ){
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it}
            ,
            placeholder = {
                Text (
                    text = name,
                    color = Color.White
                )
            },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    icon,
                    contentDescription = null,
                    tint = Color.Yellow
                )
            },
            textStyle = TextStyle(color = Color.White ),
            keyboardOptions = keyboardOptions

        )
    }
    return text
}