package com.example.wake_app.screens.minigames

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.wake_app.BottomBarScreen
import com.example.wake_app.R
import com.example.wake_app.ui.theme.*
import kotlin.random.Random

@Composable
fun ClickSequenceMiniGameScreen() {
    val context = LocalContext.current
    val listNums = mutableListOf(1,2,3,4,5)
    val checker = mutableListOf(
        remember { mutableStateOf(false) },
        remember { mutableStateOf(false) },
        remember { mutableStateOf(false) },
        remember { mutableStateOf(false) },
        remember { mutableStateOf(false) }
    )
    listNums.shuffle()

    Column(
        Modifier
            .fillMaxSize()
            .padding(15.dp)
            .background(if (isSystemInDarkTheme()) md_theme_dark_background else md_theme_light_background)
    ) {
        Text(text = "Click the buttons in the correct order",
            fontSize = 30.sp,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center,
            color = if (isSystemInDarkTheme()) md_theme_dark_onBackground else md_theme_light_onBackground
        )
        Spacer(modifier = Modifier.height(60.dp))

        // Add the buttons to the screen
       for (num in listNums) {
           val color = if(!checker[num-1].value) {
               if (isSystemInDarkTheme())
                   md_theme_dark_primary
               else
                   md_theme_light_primary
           } else Color.Gray
           Spacer(modifier = Modifier.height(Random.nextInt(0, 30).dp))
           Row(modifier = Modifier
               .align(Alignment.CenterHorizontally)
               .fillMaxWidth()
           ) {
               Button(
                   onClick = {
                       checkSequence(num, checker, context)
                   },
                   modifier = Modifier
                       .size(85.dp)
                       .offset(x = Random.nextInt(30, 200).dp),
                   shape = RoundedCornerShape(25.dp),
                   colors = ButtonDefaults.buttonColors(backgroundColor = color),
                   content = {
                       Text(text="$num", fontSize = 25.sp)
                   }
               )
           }
       }
    }
}

fun checkSequence(num: Int, checker: MutableList<MutableState<Boolean>>, context: Context) {
    if (num == 1) {
        checker[0].value = true
    }
    else if (checker[num-2].value) {
        checker[num-1].value = true
        if (num == 5) {
            Toast.makeText(context, "Correct! Good morning", Toast.LENGTH_SHORT).show()
            //navController.navigate(BottomBarScreen.Home.route)
        }
    }
    else {
        Toast.makeText(context, "Incorrect order try again!", Toast.LENGTH_SHORT).show()
    }
}

@Preview
@Composable
fun PreviewSequenceMiniGameScreen() {
    ClickSequenceMiniGameScreen()
}
