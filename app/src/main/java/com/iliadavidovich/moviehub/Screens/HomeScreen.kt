package com.iliadavidovich.moviehub.Screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.iliadavidovich.moviehub.Classes.HomeViewModel
import com.iliadavidovich.moviehub.R
import com.iliadavidovich.moviehub.ui.theme.LightYellow
import com.iliadavidovich.moviehub.Classes.FilmComment
import com.iliadavidovich.moviehub.Interfaces.HomeState
import androidx.compose.runtime.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
fun Home(navController: NavController){
    val viewModel = viewModel<HomeViewModel>()
    val myState: State<HomeState> = viewModel.state.collectAsStateWithLifecycle()
    HomeScreenContent(
        onRemove = { filmComment->
            val job = Job()
            val scope = CoroutineScope(job)
            scope.launch {
                viewModel.onClickRemoveComment(filmComment = filmComment)
            }
    },
        onAdd = { navController.navigate("EditScreen?filmComment=${null}") },
        navController = navController,
        value = myState.value)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreenContent(
    onRemove: (FilmComment) -> Unit,
    onAdd: () -> Unit,
    navController: NavController,
    value: HomeState
) {
    Scaffold(
        floatingActionButton = { FloatingActionButtonCompose(onAdd = onAdd ) },
        topBar = { HomeTopBar(navController = navController)},
        contentColor = Color(0xff1b1b23),
    ) {
        values ->
        when (value){
            is HomeState.Empty -> EmptyListVariant(
                onAdd,
                Modifier
                    .fillMaxSize()
                    .padding(values)
            )
            is HomeState.DisplayingFilmComments -> Column(modifier = Modifier
                .padding(values)
                .background(Color(0xff1b1b23))
                .fillMaxHeight()
                .verticalScroll(
                    rememberScrollState()
                ))
            {
                for (item in value.filmComments) {
                    Spacer(modifier = Modifier.height(10.dp))
                    CommentItem(
                        filmComment = item,
                        onRemove = { onRemove(item) },
                        navController = navController )
                }
                }
            is HomeState.Error -> EmptyListVariant(
                onAdd,
                Modifier
                    .fillMaxSize()
                    .padding(values)
            )
            HomeState.Loading ->{
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xff1b1b23)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Loading()
                }
            }
        }

    }
}

@Composable
fun CommentItem(
    filmComment: FilmComment,
    onRemove: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavController
){
    val context = LocalContext.current;
    Column(verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally ,
        modifier = modifier
            .background(Color.Gray)
            .fillMaxWidth()
            .padding(10.dp)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)

        ) {
           Image(painter = painterResource(id = filmComment.film.image ),contentDescription = null, alignment = Alignment.TopCenter,modifier = Modifier
               .size(400.dp)

           )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
        ) {
            TextTheme {
                Text(text =  stringResource (id = R.string.title) + " ${filmComment.film.title}")
                Text(text =  stringResource (id = R.string.genre) + " ${filmComment.film.genre}")
                Text(text =  stringResource (id = R.string.duration) + " ${filmComment.film.duration}")
                Text(text =  stringResource (id = R.string.realese) + " ${filmComment.film.realeseYear}")
                Text(text =  stringResource (id = R.string.country) + " ${filmComment.film.country}")
                Text(text =  stringResource (id = R.string.rating) + " ${filmComment.film.rating}")
                Text(text =  stringResource (id = R.string.cast) + " ${filmComment.film.cast}")
                Text(text =  stringResource (id = R.string.description) + " ${filmComment.film.description}")
                Text(text =  stringResource (id = R.string.budget) + " ${filmComment.film.budget}$")
                Text(text =  stringResource (id = R.string.collection) + " ${filmComment.film.collection}$")

            }
            }
        Row(modifier = modifier) {
            IconButton(onClick = { navController.navigate("EditScreen?filmCommentId=${filmComment.id}")}) {
                Icon(Icons.Default.Settings, contentDescription = "Edit", tint = Color.Blue)
            }
            IconButton(onClick = { onRemove()
                Toast.makeText(context, "Comment deleted!", Toast.LENGTH_SHORT).show()
            }) {
                Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red)
            }
        }
    }
}

@Composable
fun FloatingActionButtonCompose(onAdd:() -> Unit){
    FloatingActionButton(onClick = {onAdd() }) {
        Icon(Icons.Default.Add, contentDescription = stringResource(R.string.add))
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(navController: NavController)
{
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Black
        ),
        navigationIcon = {
            IconButton(onClick = {
                navController.navigate("AboutScreen")
                {
                    popUpTo("AboutScreen")
                }
            }) {
                Icon(
                    Icons.Filled.Info,
                    contentDescription = "Navigation Menu",
                    tint = LightYellow
                )
            }
        },
        title = {
            Navigation
            Text(
                stringResource(R.string.home_screen),
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 24.sp
            )
        }
    )
}

@Composable
fun EmptyListVariant(onAdd: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff1b1b23)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(70.dp))
        Text(
            text = "There is no notes",
            color = Color.White,
            fontSize = 36.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = { onAdd() },
            modifier = Modifier.width(100.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Yellow,
                contentColor = Color.DarkGray
            )
        ) {
            Text(text = "Add")
        }
    }
}

@Composable
fun TextTheme(content: @Composable () -> Unit)
{
    MaterialTheme(
        colorScheme = lightColorScheme() ,
        typography = Typography(
           bodyLarge = androidx.compose.ui.text.TextStyle(
               fontFamily = FontFamily.Default,
               fontWeight = FontWeight.Normal,
               fontSize = 20.sp
           )
        ),
        shapes = Shapes(),
        content = content
    )
}