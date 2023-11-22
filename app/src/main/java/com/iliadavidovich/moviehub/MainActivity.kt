@file:OptIn(ExperimentalMaterial3Api::class)

package com.iliadavidovich.moviehub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost;
import androidx.navigation.compose.composable;
import com.google.gson.GsonBuilder
import com.iliadavidovich.moviehub.Screens.*;
import java.util.UUID

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "HomeScreen"
            ) {
                composable("HomeScreen") {
                    Home(navController)
                }

                composable("AboutScreen") {
                    About(navController);
                }
                composable("EditScreen?filmCommentId={id}") {
                    navBackStackEntry ->
                    val idString = navBackStackEntry.arguments?.getString("id")
                    val converter = GsonBuilder().create()
                    val id = converter.fromJson(idString, UUID::class.java)
                    EditScreen(navController, id = id);
                }
            }
        }
    }
}




