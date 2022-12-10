package com.example.wake_app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wake_app.ExpandableCard
import com.example.wake_app.R
import com.example.wake_app.ui.theme.md_theme_dark_background
import com.example.wake_app.ui.theme.md_theme_dark_secondary
import com.example.wake_app.ui.theme.md_theme_light_background
import com.example.wake_app.ui.theme.md_theme_light_secondary

@Composable
fun StatisticsScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Statistics") },
                backgroundColor =
                    if (isSystemInDarkTheme()) md_theme_dark_background
                    else md_theme_light_background
            )
        },
        content = { PageContent() }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun PageContent(modifier: Modifier = Modifier) {
    Column(
        Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        ) {
        Text(
            modifier = Modifier
                .padding(start = 8.dp, top = 25.dp)
                .weight(1f),
            text = "Early bird streak",
            fontSize = 28.sp,
            textAlign = TextAlign.Center,
            maxLines = 2
        )

        Text(
            text = "4 days",
            color =
                if (isSystemInDarkTheme()) md_theme_dark_secondary
                else md_theme_light_secondary,
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )

        LazyColumn (modifier = Modifier.weight(5f)) {
            item {
                ExpandableCard(
                    title = "Click the sequence",
                    successPercentage = 19.0,
                    firstTryPercentage = 13.6,
                    avgTime = 24.4,
                    graph = painterResource(id = R.drawable.time_1)
                )
            }

            item {
                ExpandableCard(
                    title = "Solve an equation",
                    successPercentage = 19.0,
                    firstTryPercentage = 13.6,
                    avgTime = 24.4,
                    graph = painterResource(id = R.drawable.time_2)
                )
            }

            item {
                ExpandableCard(
                    title = "Memorize",
                    successPercentage = 4.20,
                    firstTryPercentage = 69.69,
                    avgTime = 25.1,
                    graph = painterResource(id = R.drawable.time_3)
                )
            }

            item {
                ExpandableCard(
                    title = "High to Low",
                    successPercentage = 19.0,
                    firstTryPercentage = 13.6,
                    avgTime = 24.4,
                    graph = painterResource(id = R.drawable.time_1)
                )
            }

            item {
                ExpandableCard(
                    title = "Construct a word",
                    successPercentage = 19.0,
                    firstTryPercentage = 13.6,
                    avgTime = 24.4,
                    graph = painterResource(id = R.drawable.time_2)
                )
            }
        }
    }

}

@Composable
@Preview
fun StatisticsScreenPreview() {
    StatisticsScreen()
}