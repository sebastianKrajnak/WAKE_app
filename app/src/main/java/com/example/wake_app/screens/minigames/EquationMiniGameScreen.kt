package com.example.wake_app.screens.minigames

import android.view.KeyEvent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
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
import com.example.wake_app.model.minigames.Expression

@Composable
fun EquationMiniGameScreen(expr: Expression) {
    val navController = rememberNavController()
    var result by remember { mutableStateOf("") }
    var strExpr by remember { mutableStateOf(expr.toString()) }
    val focusManager = LocalFocusManager.current
    Column(
        Modifier
            .fillMaxSize()
            .background(colorResource(R.color.background_light)),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Text(text = "Solve the expression",
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
                text = strExpr,
                fontSize = 35.sp,
                color = colorResource(R.color.text_color_white),
                textAlign = TextAlign.Center
            )
        }

        Button(onClick = {
            expr.newExpression()
            strExpr = expr.toString();
        }
        ) {
            Text(text = "Re-generate")
        }

        Text(text = " ", modifier = Modifier.padding(100.dp))

        TextField(
            value = result,
            onValueChange = { result = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text("Result", color = colorResource(R.color.text_color_white)) },
            modifier = Modifier.background(colorResource(R.color.input_field))
                .onKeyEvent {
                    if (it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER){
                        //result.dropLast(1); //remove whitespace
                        System.out.println("result " + result);
                        checkResult(result, expr.getResult(), navController)
                        focusManager.clearFocus()
                        result=result.trim()
                        true
                    }
                    false
            }
        )

        Button(onClick = {
            checkResult(result, expr.getResult(), navController)

        }
        ) {
            Text(text = "Submit")
        }


    }
}


fun checkResult(result: String, gameResult: Int, navController: NavHostController){
    val result = result.trim();
    if (result == "") return;

    if (result.toInt().equals(gameResult)) {
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
    EquationMiniGameScreen (
        Expression(1,10)
    )
}