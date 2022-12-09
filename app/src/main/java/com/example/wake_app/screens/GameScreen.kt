package com.example.wake_app.screens

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wake_app.R
import com.example.wake_app.model.Game

@Composable
fun GameScreen(game: Game) {
    var result by remember { mutableStateOf("") }
    val activity = (LocalContext.current as? Activity)
    Column(
        Modifier
            .fillMaxSize()
            .background(colorResource(R.color.background_light)),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {


            Text(text = game.title,
                fontSize = 30.sp,
                modifier = Modifier.padding(16.dp),
                color = colorResource(R.color.text_color_white),
            )


            Card(backgroundColor = colorResource(R.color.background_dark),
                modifier = Modifier
                    .padding(8.dp) // margin
                    .border(2.dp, colorResource(R.color.text_color_white)) // outer border
                    .padding(16.dp), // space between the borders
                elevation = 18.dp
                 ) {
                Text(
                    text = game.equation,
                    fontSize = 35.sp,
                    color = colorResource(R.color.text_color_white),
                    textAlign = TextAlign.Center
                )
            }

            Text(text = " ", modifier = Modifier.padding(100.dp))

            TextField(
                value = result,
                onValueChange = { result = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                label = { Text("Result", color = colorResource(R.color.text_color_white)) },
                modifier = Modifier.background(colorResource(R.color.input_field))
            )

            Button(onClick = {

                checkResult(result, game.result, activity!!)

            }
            ) {
                Text(text = "Submit")
            }


    }
}

fun checkResult(result: String, gameResult: String, activity: Activity){
    if (result.equals(gameResult)) {
        System.out.println("Correct")
        activity.finish()
    } else {
        System.out.println("Incorrect")
    }
}



@Composable
@Preview
fun GameScreenPreview() {
    GameScreen (
        Game("1", "5 + 5 / 5", "6", "Solve the equation")
    )
}