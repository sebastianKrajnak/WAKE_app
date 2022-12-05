package com.example.wake_app.screens.minigames

import android.view.KeyEvent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.wake_app.R
import com.example.wake_app.model.minigames.ConstructWord

@Composable
fun ConnectWordMiniGameScreen(cw: ConstructWord) {
    val navController = rememberNavController()
    var result by remember { mutableStateOf("") }
    //var word by remember { mutableStateOf(cw.word) }
    var word by remember { mutableStateOf("administration") } //longest word
    val selects: List<Boolean> = cw.selects
    val focusManager = LocalFocusManager.current



    Column(
        Modifier
            .fillMaxSize()
            .background(colorResource(R.color.background_light)),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Text(text = cw.title,
            fontSize = 30.sp,
            modifier = Modifier
                .padding(16.dp)
                .align(alignment = Alignment.CenterHorizontally)
            ,
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
                text = result,
                fontSize = 35.sp,
                color = colorResource(R.color.text_color_white),
                textAlign = TextAlign.Center,
                modifier = Modifier.width((word!!.length*35).dp)
            )
        }

        Button(onClick = {
            cw.newWord()
            word = cw.toString()
        }
        ) {
            Text(text = "Re-generate")
        }
        //generateButtonGrid(selects,cw,word!!)

        LazyColumn() {
            itemsIndexed(cw.letters) { index, item ->

                val color = if (item.selected) colorResource(R.color.main_accent) else Color(152,152,152)
                Column (modifier = Modifier
                    .padding(5.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(color)
                ) {
                    Button(
                        onClick = {
                            cw.updateLetter(index)
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = color),
                    ) {
                        Text(
                            text = "a",
                            fontSize = 50.sp,
                            color = colorResource(R.color.text_color_white),
                            modifier = Modifier.fillMaxSize(),
                            textAlign = TextAlign.Center,
                        )
                    }
                }

            }
        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun generateButtonGrid(selects: List<Boolean>,cw: ConstructWord,word: String) {
    LazyVerticalGrid(
        cells = GridCells.Adaptive(minSize = 75.dp)
    ) {
            items(word.length) { index ->
                GameLetterBtn(selects,cw,word,index)
//                Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                    Text(
//                        text = word[index].toString(),
//                        fontSize = 50.sp,
//                        color = colorResource(R.color.text_color_white)
//                    )
//                }
            }
    }
}


@Composable
fun GameLetterBtn(selects: List<Boolean>,cw: ConstructWord, word: String, index: Int) {
    //var selected by remember { mutableStateOf(selects[index]) }
    val color = if (selects[index]) colorResource(R.color.main_accent) else Color(152,152,152)

    Column (modifier = Modifier
        .padding(5.dp)
        .clip(RoundedCornerShape(10.dp))
        .background(color)
    ) {
        Button(
            onClick = {
                System.out.println("Clicked " + word[index].toString())
                System.out.println("Select " + selects[index])

                cw.setSelect(index,!selects[index])
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = color),
        ) {
            Text(
                text = word[index].toString(),
                fontSize = 50.sp,
                color = colorResource(R.color.text_color_white),
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center,
                )
        }
    }

}

//fun checkResult(result: String, gameResult: Int, navController: NavHostController){
//    val result = result.trim();
//    if (result == "") return;
//
//    if (result.toInt().equals(gameResult)) {
//        System.out.println("Correct")
////        navController.navigate(BottomBarScreen.Statistics.route)
//        // TODO navigate to Home screen
//    } else {
//        System.out.println("Incorrect")
//    }
//}



@Composable
@Preview
fun ConnectWordMiniGameScreenPreview() {
    ConnectWordMiniGameScreen (
        ConstructWord()
    )
}