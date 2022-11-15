package com.example.wake_app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.wake_app.R
import com.example.wake_app.model.Game

@Composable
fun GameScreen(game: Game) {
    val navController = rememberNavController()
    var result by remember { mutableStateOf("") }
    Column(
        Modifier.fillMaxSize()
            .background(colorResource(R.color.background_dark)),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Text(text = game.title,
            fontSize = 30.sp,
            modifier = Modifier.padding(16.dp),
            color = colorResource(R.color.text_color_white),
        )
        Card(backgroundColor = Color.Gray,
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

        OutlinedTextField(
            value = result,

            onValueChange = { result = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text("Result", color = colorResource(R.color.text_color_white)) }
        )
        Button(onClick = {
            checkResult(result, game.result, navController)
        }
        ) {
            Text(text = "Submit")
        }


    }
}


fun checkResult(result: String, gameResult: String, navController: NavHostController){
    if (result.equals(gameResult)) {
        System.out.println("Correct")
//        navController.navigate(BottomBarScreen.Statistics.route)
        // TODO navigate to Home screen
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