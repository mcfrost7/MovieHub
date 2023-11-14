package com.iliadavidovich.moviehub.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.compose.rememberNavController
import com.iliadavidovich.moviehub.Classes.Film
import com.iliadavidovich.moviehub.R
import com.iliadavidovich.moviehub.ui.theme.LightYellow
import org.w3c.dom.Comment
import com.iliadavidovich.moviehub.Screens.*
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditScreen( navController: NavController = rememberNavController(),
    comment: com.iliadavidovich.moviehub.Classes.Comment = com.iliadavidovich.moviehub.Classes.Comment(Film("0","","","", "","","","","","",0),
                   stringResource(R.string.made)
    )
   )
{
    Scaffold(
        topBar = { EditTopAppBar(navController) },
        containerColor = Color(0xff1b1b23),
    ){

        EditField(comment)
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
fun EditField(comment: com.iliadavidovich.moviehub.Classes.Comment)
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(70.dp))
        Field(name = "Title", icon = Icons.Filled.CheckCircle)
        Field(name = "Duration", icon = Icons.Filled.CheckCircle)
        Field(name = "Genre", icon = Icons.Filled.CheckCircle)
        Field(name = "Realese", icon = Icons.Filled.CheckCircle)
        Field(name = "Country", icon = Icons.Filled.CheckCircle)
        Field(name = "Rating", icon = Icons.Filled.CheckCircle)
        Field(name = "Cast", icon = Icons.Filled.CheckCircle)
        Field(name = "Description", icon = Icons.Filled.CheckCircle)
        Field(name = "Budget", icon = Icons.Filled.CheckCircle)
        Field(name = "Collection", icon = Icons.Filled.CheckCircle)
    }
}

@Composable
fun Field(name: String,icon : ImageVector){
    var text by remember {
        mutableStateOf("")
    }
    Row (verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ){
        OutlinedTextField(value = text, onValueChange = {text = it},
            placeholder = {
                Text (
                    text = name,
                    color = Color.White
                )
            },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon( icon, contentDescription = null, tint = Color.Yellow)
            },
            textStyle = TextStyle(color = Color.White )

        )
    }
}