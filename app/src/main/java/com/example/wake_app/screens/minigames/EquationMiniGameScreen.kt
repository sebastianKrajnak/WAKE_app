package com.example.wake_app.screens.minigames

import android.app.Activity
import android.view.KeyEvent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.wake_app.model.minigames.Expression
import com.example.wake_app.ui.theme.md_theme_dark_background
import com.example.wake_app.ui.theme.md_theme_dark_onBackground
import com.example.wake_app.ui.theme.md_theme_light_background
import com.example.wake_app.ui.theme.md_theme_light_onBackground

@Composable
fun EquationMiniGameScreen(expr: Expression) {
    val navController = rememberNavController()
    var result by remember { mutableStateOf("") }
    var strExpr by remember { mutableStateOf(expr.toString()) }
    val focusManager = LocalFocusManager.current
    val activity = (LocalContext.current as? Activity)

    Column(
        Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) md_theme_dark_background else md_theme_light_background),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Text(text = expr.title,
            fontSize = 30.sp,
            modifier = Modifier.padding(16.dp),
            color = if (isSystemInDarkTheme()) md_theme_dark_onBackground else md_theme_light_onBackground

        )
        Card(
            modifier = Modifier
                .padding(8.dp), // margin
            elevation = 18.dp,
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = strExpr,
                fontSize = 35.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 7.dp, end = 7.dp)
            )
        }

        Button(
            onClick = {
                expr.newExpression()
                strExpr = expr.toString()
            },
            shape = RoundedCornerShape(19.dp)
        ) {
            Text(text = "Re-generate")
        }

        Spacer(Modifier.height(100.dp))

        OutlinedTextField(
            value = result,
            onValueChange = { result = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text("Result") },
            shape = CircleShape,
            modifier = Modifier.onKeyEvent {
                    if (it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER){
                        //result.dropLast(1); //remove whitespace
                        println("result $result")
//                        if (checkResult(result, expr.getResult(), navController)) activity!!.finish()
                        focusManager.clearFocus()
                        result=result.trim()
                        true
                    }
                    false
            }
        )

        Button(
            onClick = {
                if (checkResult(result, expr.getResult(), navController)) activity!!.finish()
            },
            shape = RoundedCornerShape(19.dp)
        ) {
            Text(text = "Submit")
        }


    }
}


fun checkResult(result: String, gameResult: Int, navController: NavHostController): Boolean {
    val result = result.trim()
    if (result == "") return false
    return result.toInt().equals(gameResult)
}



@Composable
@Preview
fun GameScreenPreview() {
    EquationMiniGameScreen (
        Expression(1,10)
    )
}