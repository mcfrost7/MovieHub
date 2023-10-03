@file:OptIn(ExperimentalMaterial3Api::class)

package com.iliadavidovich.moviehub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.navigation.Navigation
import com.iliadavidovich.moviehub.ui.theme.BackGray
import com.iliadavidovich.moviehub.ui.theme.LightYellow
import com.iliadavidovich.moviehub.ui.theme.MovieHubTheme
import com.iliadavidovich.moviehub.ui.theme.PurpleGrey40
import com.iliadavidovich.moviehub.ui.theme.SoftWhite

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieHubTheme {
                Companion.About()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    companion object {
        @Preview
        @Composable
        fun About() {
        val textsize = 0.4f
        val screenHeight = LocalConfiguration.current.screenHeightDp.dp
        val columnweight = (0.1 * screenHeight)
        val spacesize = 20.dp
           Scaffold(
                topBar = {
                    TopAppBar(
                        colors = topAppBarColors(
                            containerColor = Color.Black
                        ),
                        navigationIcon = {
                            IconButton(onClick = { }) {
                                Icon(
                                    Icons.Filled.Menu,
                                    contentDescription = "Navigation Menu",
                                    tint = LightYellow
                                )
                            }
                        },
                        title = {
                            Navigation
                            Text(
                                stringResource(R.string.about),
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 24.sp
                            )
                        }
                    )
                },
                containerColor = SoftWhite,
               modifier = Modifier.fillMaxHeight(1f)
            )
           {
               CompositionLocalProvider(LocalContentColor provides Color.White) {
                   Column(
                       Modifier
                           .fillMaxWidth(1f)
                           .fillMaxHeight(1f)
                           .fillMaxSize(1f)
                           .background(
                               color = BackGray
                           )

                   )
                   {
                       Column(
                           modifier = Modifier
                               .padding(10.dp, top = 75.dp, bottom = 0.dp, end = 10.dp)
                               .fillMaxWidth()
                               .height(columnweight)


                       ) {
                           Row(
                               modifier = Modifier
                           )

                           {
                               Image(
                                   painter = painterResource(id = R.drawable.version),
                                   contentDescription = null,
                                   modifier = Modifier
                                       .size(60.dp)
                               )
                               Column(modifier = Modifier.fillMaxHeight(1f)) {
                                   Row(
                                       modifier = Modifier
                                           .fillMaxHeight(textsize)
                                   ) {
                                       Text(
                                           text = "Version",
                                           fontSize = 26.sp,
                                           modifier = Modifier
                                               .padding(10.dp, 0.dp)

                                       )

                                   }
                                   Row(
                                       modifier = Modifier
                                           .fillMaxHeight(0.6f)
                                   ) {
                                       Text(
                                           text = "0.0.1",
                                           fontSize = 20.sp,
                                           modifier = Modifier
                                               .padding(10.dp, 0.dp)

                                       )
                                   }

                               }
                           }
                       }
                       Spacer(modifier = Modifier.height(spacesize))
                       Column(
                           modifier = Modifier
                               .padding(10.dp, 0.dp)
                               .fillMaxWidth()
                               .height(columnweight)
                               .fillMaxSize(1f)


                       ) {
                           Row(
                               modifier = Modifier
                           )

                           {
                               Image(
                                   painter = painterResource(id = R.drawable.git),
                                   contentDescription = null,
                                   modifier = Modifier
                                       .size(60.dp)
                               )
                               Column(modifier = Modifier.fillMaxHeight(1f)) {
                                   Row(
                                       modifier = Modifier
                                           .fillMaxHeight(textsize)
                                   ) {
                                       Text(
                                           text = "GitHub",
                                           fontSize = 26.sp,
                                           modifier = Modifier
                                               .padding(10.dp, 0.dp)

                                       )

                                   }
                                   Row(
                                       modifier = Modifier
                                           .fillMaxHeight(0.7f)
                                   ) {
                                       Text(
                                           text = "View this project on GitHub.",
                                           fontSize = 20.sp,
                                           modifier = Modifier
                                               .padding(10.dp, 0.dp)

                                       )
                                   }
                               }

                           }

                       }
                       Spacer(modifier = Modifier.height(spacesize))
                       Column(
                           modifier = Modifier
                               .padding(10.dp, 0.dp)
                               .fillMaxWidth()
                               .height(columnweight)
                               .fillMaxSize(1f)


                       ) {
                           Row(
                               modifier = Modifier
                           )

                           {
                               Image(
                                   painter = painterResource(id = R.drawable.faq),
                                   contentDescription = null,
                                   modifier = Modifier
                                       .size(60.dp)
                               )
                               Column(modifier = Modifier.fillMaxHeight(1f)) {
                                   Row(
                                       modifier = Modifier
                                           .fillMaxHeight(textsize)
                                   ) {
                                       Text(
                                           text = "FAQ",
                                           fontSize = 26.sp,
                                           modifier = Modifier
                                               .padding(10.dp, 0.dp)

                                       )

                                   }
                                   Row(
                                       modifier = Modifier
                                           .fillMaxHeight(0.7f)
                                   ) {
                                       Text(
                                           text = "Need more help?",
                                           fontSize = 20.sp,
                                           modifier = Modifier
                                               .padding(10.dp, 0.dp)

                                       )
                                   }
                               }

                           }

                       }
                       Spacer(modifier = Modifier.height(spacesize))
                       Column(
                           modifier = Modifier
                               .padding(10.dp, 0.dp)
                               .fillMaxWidth()
                               .height(columnweight + 40.dp)
                               .fillMaxSize(1f)


                       ) {
                           Row(
                               modifier = Modifier
                           )

                           {
                               Image(
                                   painter = painterResource(id = R.drawable.bug),
                                   contentDescription = null,
                                   modifier = Modifier
                                       .size(60.dp)
                               )
                               Column(modifier = Modifier.fillMaxHeight(1f)) {
                                   Row(
                                       modifier = Modifier
                                           .fillMaxHeight(textsize - 0.1f)
                                   ) {
                                       Text(
                                           text = "Report bug",
                                           fontSize = 26.sp,
                                           modifier = Modifier
                                               .padding(10.dp, 0.dp)

                                       )

                                   }
                                   Row(
                                       modifier = Modifier
                                           .fillMaxHeight(0.8f)
                                   ) {
                                       Text(
                                           text = "Faced with an error? Let us know and we will help you solve the problem quickly",
                                           fontSize = 20.sp,
                                           modifier = Modifier
                                               .padding(10.dp, 0.dp)

                                       )
                                   }
                               }

                           }

                       }
                       Spacer(modifier = Modifier.height(spacesize))
                       Column(
                           modifier = Modifier
                               .padding(10.dp, 0.dp)
                               .fillMaxWidth()
                               .height(columnweight + 20.dp)
                               .fillMaxSize(1f)


                       ) {
                           Row(
                               modifier = Modifier
                           )

                           {
                               Image(
                                   painter = painterResource(id = R.drawable.contact),
                                   contentDescription = null,
                                   modifier = Modifier
                                       .size(50.dp)
                               )
                               Column(modifier = Modifier.fillMaxHeight(1f)) {
                                   Row(
                                       modifier = Modifier
                                           .fillMaxHeight(textsize)
                                   ) {
                                       Text(
                                           text = "Contact us",
                                           fontSize = 26.sp,
                                           modifier = Modifier
                                               .padding(10.dp, 0.dp)

                                       )

                                   }
                                   Row(
                                       modifier = Modifier
                                           .fillMaxHeight(0.8f)
                                   ) {
                                       Text(
                                           text = "Any ideas for improvement? Contact us for discussion",
                                           fontSize = 20.sp,
                                           modifier = Modifier
                                               .padding(10.dp, 0.dp)

                                       )
                                   }
                               }

                           }

                       }
                       Spacer(modifier = Modifier.height(spacesize))
                       Column(
                           modifier = Modifier
                               .padding(10.dp, 0.dp)
                               .fillMaxWidth()
                               .height(columnweight)
                               .fillMaxSize(1f)


                       ) {
                           Row(
                               modifier = Modifier
                                   .fillMaxHeight(1f)
                           )

                           {
                               Image(
                                   painter = painterResource(id = R.drawable.develop),
                                   contentDescription = null,
                                   modifier = Modifier
                                       .size(50.dp)
                               )
                               Column(modifier = Modifier.fillMaxHeight(1f)) {
                                   Row(
                                       modifier = Modifier
                                           .fillMaxHeight(textsize)
                                   ) {
                                       Text(
                                           text = "Made by",
                                           fontSize = 26.sp,
                                           modifier = Modifier
                                               .padding(10.dp, 0.dp)

                                       )

                                   }
                                   Row(
                                       modifier = Modifier
                                           .fillMaxHeight(0.7f)
                                   ) {
                                       Text(
                                           text = "mcfrost7",
                                           fontSize = 20.sp,
                                           modifier = Modifier
                                               .padding(10.dp, 0.dp)

                                       )
                                   }
                               }

                           }

                       }
                   }

               }
           }
        }

    }
}



