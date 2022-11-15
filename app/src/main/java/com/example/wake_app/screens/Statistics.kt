package com.example.wake_app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wake_app.R

@Composable
fun StatisticsScreen() {
    val col = Color(50, 50, 50)
    Column(
        Modifier.fillMaxSize()
            .background(Color.Black),
//            .background(colorResource(R.color.background)),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {

        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = "Statistics",
            fontSize = 34.sp,
            color = colorResource(R.color.text_color_white)
        )

        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = "Early bird streak: 4 days",
            fontSize = 28.sp,
            color = colorResource(R.color.text_color_white)
        )

        val dpCon = 15.dp

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dpCon),
            backgroundColor = col
        ) {
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = "Solve an equation",
                fontSize = 24.sp,
                color = colorResource(R.color.text_color_white),

                )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dpCon)
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                backgroundColor = col
            ) {
                Column() {
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = "High to Low",
                        fontSize = 24.sp,
                        color = colorResource(R.color.text_color_white)
                    )
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = "Successful awakening: 19%",
                        color = colorResource(R.color.text_color_white)
                    )
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = "Woke up on first try: 13.6%",
                        color = colorResource(R.color.text_color_white)
                    )
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = "Average solving time: 24 seconds",
                        color = colorResource(R.color.text_color_white)
                    )
                    Image(
                painter = painterResource(id = R.drawable.success_rate_per_game),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                        modifier = Modifier.padding(10.dp)
            )
                }

            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dpCon),
            backgroundColor = col
        ) {
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = "Memorize",
                fontSize = 24.sp,
                color = colorResource(R.color.text_color_white)
            )
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dpCon),
            backgroundColor = col
        ) {
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = "Repeat the pattern",
                fontSize = 24.sp,
                color = colorResource(R.color.text_color_white)
            )
        }

//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(10.dp),
//            backgroundColor = col
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.number_games_played),
//                contentDescription = null,
//                contentScale = ContentScale.Fit
//            )
//        }
//
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(10.dp)
//        ) {
//
//        }


    }

}

@Composable
@Preview
fun StatisticsScreenPreview() {
    StatisticsScreen()
}