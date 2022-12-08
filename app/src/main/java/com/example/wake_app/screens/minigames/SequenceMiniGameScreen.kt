package com.example.wake_app.screens.minigames

import android.content.Context
import android.content.res.Resources
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
import kotlin.random.Random
import kotlin.random.nextInt

@Composable
fun SequenceMiniGameScreen(navController: NavHostController) {
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
            .background(colorResource(R.color.background_light))
            .padding(15.dp)
    ) {
        Text(text = "Click the buttons in the correct order",
            fontSize = 30.sp,
            modifier = Modifier.padding(16.dp),
            color = colorResource(R.color.text_color_white),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(60.dp))

        // Add the buttons to the screen
       for (num in listNums) {
           val color = if(!checker[num-1].value) colorResource(R.color.main_accent) else Color(152,152,152)
           Spacer(modifier = Modifier.height(Random.nextInt(0, 30).dp))
           Row(modifier = Modifier
               .align(Alignment.CenterHorizontally)
               .fillMaxWidth()
           ) {
               Button(
                   onClick = {
                       checkSequence(num, checker, context, navController)
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

fun checkSequence(num: Int, checker: MutableList<MutableState<Boolean>>, context: Context, navController: NavHostController) {
    if (num == 1) {
        checker[0].value = true
    }
    else if (checker[num-2].value) {
        checker[num-1].value = true
        if (num == 5) {
            Toast.makeText(context, "Correct! Good morning", Toast.LENGTH_SHORT).show()
            navController.navigate(BottomBarScreen.Home.route)
        }
    }
    else {
        Toast.makeText(context, "Incorrect order try again!", Toast.LENGTH_SHORT).show()
    }
}

@Preview
@Composable
fun PreviewSequenceMiniGameScreen() {
    val navController = rememberNavController()
    SequenceMiniGameScreen(navController)
}
