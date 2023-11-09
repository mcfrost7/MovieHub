package com.iliadavidovich.moviehub.Screens

import android.annotation.SuppressLint
import android.widget.ImageButton
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.iliadavidovich.moviehub.R
import com.iliadavidovich.moviehub.ui.theme.BackGray
import com.iliadavidovich.moviehub.ui.theme.LightYellow
import com.iliadavidovich.moviehub.ui.theme.SoftWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateColumn(
    paddingStart: Float,
    paddingTop: Float,
    paddingBottom: Float,
    paddingEnd: Float,
    imageResource: Int,
    textResourceLong: Int,
    textResourceShort: Int,
    imageSize: Int,
    textPaddingHorizontal: Float,
    textPaddingVertical: Float,
    rowSizeMain: Float,
    rowSizeSecond: Float,
) {
    val textsize = 0.4f

    val screenHeight = LocalConfiguration.current.screenHeightDp * 0.1f
    val screenWidth = LocalConfiguration.current.screenWidthDp * 0.1f
    val curentSzie = if (screenHeight > screenWidth) screenHeight else screenWidth
    var columnweight by rememberSaveable { mutableStateOf(curentSzie) }
    val spacesize = 20.dp
    Column(
        modifier = Modifier
            .padding(paddingStart.dp, paddingTop.dp, paddingEnd.dp, paddingBottom.dp)
            .fillMaxWidth()
            .height(columnweight.dp)


    ) {
        Row(
            modifier = Modifier
        )

        {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = null,
                modifier = Modifier
                    .size(imageSize.dp)
            )
            Column(modifier = Modifier.fillMaxHeight(1f)) {
                Row(
                    modifier = Modifier
                        .fillMaxHeight(rowSizeMain)
                ) {
                    Text(
                        text = stringResource(id = textResourceShort),
                        fontSize = 26.sp,
                        modifier = Modifier
                            .padding(textPaddingHorizontal.dp, textPaddingVertical.dp)

                    )

                }
                Row(
                    modifier = Modifier
                        .fillMaxHeight(rowSizeSecond)
                ) {
                    Text(
                        text = stringResource(id = textResourceLong),
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(textPaddingHorizontal.dp, textPaddingVertical.dp)

                    )
                }

            }
        }
    }
    Spacer(modifier = Modifier.height(spacesize))
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun About(navController: NavController) {

    Scaffold(
        topBar = {
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
                            Icons.Filled.Menu,
                            contentDescription = "Navigation Menu",
                            tint = LightYellow
                        )
                    }
                },
                title = {
                    Navigation
                    Text(
                        stringResource(R.string.about_screen),
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
                    .verticalScroll(rememberScrollState())

            )
            {
                CreateColumn(
                    paddingStart = 10F,
                    paddingTop = 75F,
                    paddingBottom = 0F,
                    paddingEnd = 0F,
                    imageResource = R.drawable.version,
                    textResourceLong = R.string.version,
                    textResourceShort = R.string.versionMain,
                    imageSize = 60,
                    textPaddingHorizontal = 10f,
                    textPaddingVertical = 0F,
                    rowSizeMain = 0.5F,
                    rowSizeSecond = 1F
                )
                CreateColumn(
                    paddingStart = 10F,
                    paddingTop = 0F,
                    paddingBottom = 0F,
                    paddingEnd = 0F,
                    imageResource = R.drawable.git,
                    textResourceLong = R.string.git,
                    textResourceShort = R.string.gitMain,
                    imageSize = 60,
                    textPaddingHorizontal = 10f,
                    textPaddingVertical = 0F,
                    rowSizeMain = 0.5F,
                    rowSizeSecond = 1F
                )
                CreateColumn(
                    paddingStart = 10F,
                    paddingTop = 0F,
                    paddingBottom = 0F,
                    paddingEnd = 0F,
                    imageResource = R.drawable.faq,
                    textResourceLong = R.string.faq,
                    textResourceShort = R.string.faqMain,
                    imageSize = 60,
                    textPaddingHorizontal = 10f,
                    textPaddingVertical = 0F,
                    rowSizeMain = 0.5F,
                    rowSizeSecond = 1F
                )
                CreateColumn(
                    paddingStart = 10F,
                    paddingTop = 0F,
                    paddingBottom = 0F,
                    paddingEnd = 0F,
                    imageResource = R.drawable.bug,
                    textResourceLong = R.string.bug,
                    textResourceShort = R.string.bugMain,
                    imageSize = 60,
                    textPaddingHorizontal = 10f,
                    textPaddingVertical = 0F,
                    rowSizeMain = 0.5F,
                    rowSizeSecond = 1F
                )
                CreateColumn(
                    paddingStart = 10F,
                    paddingTop = 0F,
                    paddingBottom = 0F,
                    paddingEnd = 0F,
                    imageResource = R.drawable.contact,
                    textResourceLong = R.string.contact,
                    textResourceShort = R.string.contactMain,
                    imageSize = 60,
                    textPaddingHorizontal = 10f,
                    textPaddingVertical = 0F,
                    rowSizeMain = 0.5F,
                    rowSizeSecond = 1F
                )
                CreateColumn(
                    paddingStart = 10F,
                    paddingTop = 0F,
                    paddingBottom = 0F,
                    paddingEnd = 0F,
                    imageResource = R.drawable.develop,
                    textResourceLong = R.string.made,
                    textResourceShort = R.string.madeMain,
                    imageSize = 60,
                    textPaddingHorizontal = 10f,
                    textPaddingVertical = 0F,
                    rowSizeMain = 0.5F,
                    rowSizeSecond = 1F
                )

            }

        }
    }
}



