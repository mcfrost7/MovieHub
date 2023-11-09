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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
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
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.iliadavidovich.moviehub.Classes.HomeViewModel
import com.iliadavidovich.moviehub.R
import com.iliadavidovich.moviehub.ui.theme.LightYellow
import com.iliadavidovich.moviehub.ui.theme.SoftWhite
import com.iliadavidovich.moviehub.Classes.Comment
import com.iliadavidovich.moviehub.Classes.Film
import java.time.format.TextStyle

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Home(navController: NavController){
    val viewModel = viewModel<HomeViewModel>()
    val context = LocalContext.current
    Scaffold(
        floatingActionButton = {
            FloatingActionButtonCompose(onAdd = {
                val film = Film(title = "Scream 4" , budget = "20000000" , cast = "Popular actros", collection = "30000000", country = "USA" , description = "Very scary movie!" , duration = "90" , genre = "Horror", rating = "7" , realeseYear = "2003", image = R.drawable.poster_default);
                val comment = Comment(film,"Default note")
                viewModel.comments.add(comment)
                Toast.makeText(context, "Note added!", Toast.LENGTH_SHORT).show()
            }
            )
        }
        ,topBar = { HomeTopBar(navController = navController)},
        contentColor = SoftWhite
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth(1f)
                .verticalScroll(rememberScrollState())
        ){
            Spacer(modifier = Modifier.height(70.dp))
            HomeScreenContent(items = viewModel.comments,
                onRemove = viewModel::onClickRemoveComment,
                onAdd = {
                    val film = Film(title = "Scream 4" , budget = "20000000" , cast = "Popular actros", collection = "30000000", country = "USA" , description = "Very scary movie!" , duration = "90" , genre = "Horror", rating = "7" , realeseYear = "2003", image = R.drawable.poster_default);
                    val comment = Comment(film,"Default note")
                    viewModel.comments.add(comment)
                    Toast.makeText(context, "Note added!", Toast.LENGTH_SHORT).show() },
                navController = navController)
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
fun EmptyListVariant(onAdd: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "There is no notes",
            color = Color.DarkGray,
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
fun HomeScreenContent(
    items: List<Comment>,
    onRemove: (Comment) -> Unit,
    onAdd: () -> Unit,
    navController: NavController
) {
    Column() {
        if (items.isEmpty()) {
            EmptyListVariant(onAdd)
        } else {

            items.forEach {
                CommentItem(
                    comment = it,
                    onRemove = {
                        onRemove(it)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp),
                    navController
                )
            }
        }
    }
}

@Composable
fun CommentItem(
    comment: Comment,
    onRemove: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavController
){
    val context = LocalContext.current;
    Column(verticalArrangement = Arrangement.SpaceBetween, modifier = modifier
        .background(Color.Gray)
        .fillMaxWidth(1f)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)


        ) {
           Image(painter = painterResource(id = comment.film.image ),contentDescription = null, alignment = Alignment.TopCenter,modifier = Modifier
               .size(400.dp)

           )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
        ) {
            TextTheme {
                Text(text =  stringResource (id = R.string.title) + " ${comment.film.title}")
                Text(text =  stringResource (id = R.string.genre) + " ${comment.film.genre}")
                Text(text =  stringResource (id = R.string.duration) + " ${comment.film.duration}")
                Text(text =  stringResource (id = R.string.realese) + " ${comment.film.realeseYear}")
                Text(text =  stringResource (id = R.string.country) + " ${comment.film.country}")
                Text(text =  stringResource (id = R.string.rating) + " ${comment.film.rating}")
                Text(text =  stringResource (id = R.string.cast) + " ${comment.film.cast}")
                Text(text =  stringResource (id = R.string.description) + " ${comment.film.description}")
                Text(text =  stringResource (id = R.string.budget) + " ${comment.film.budget}$")
                Text(text =  stringResource (id = R.string.collection) + " ${comment.film.collection}$")

            }
            }
        Row(modifier = modifier) {
            IconButton(onClick = { navController.navigate("EditScreen")}) {
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