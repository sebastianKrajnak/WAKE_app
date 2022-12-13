package com.example.wake_app.screens.minigames

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.wake_app.data.ConstructWordMiniGameLetter
import com.example.wake_app.model.minigames.ConstructWord
import com.example.wake_app.ui.theme.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ConstructWordMiniGameScreen(cw: ConstructWord) {
    val navController = rememberNavController()

    Column(
        Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) md_theme_dark_background else md_theme_light_background),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            Text(text = cw.title,
                fontSize = 30.sp,
                modifier = Modifier
                    .padding(16.dp)
                    .align(alignment = Alignment.CenterHorizontally)
                ,
                textAlign = TextAlign.Center,
                color = if (isSystemInDarkTheme()) md_theme_dark_onBackground else md_theme_light_onBackground
            )
            Card(
                modifier = Modifier
                    .padding(8.dp) // margin
                    .padding(16.dp), // space between the borders
                elevation = 18.dp,
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = cw.result.value,
                    fontSize = 35.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width((cw.word.value!!.length*35).dp)
                )
            }

            Button(
                onClick = { cw.newWord() },
                shape = RoundedCornerShape(19.dp)
            ) {
                Text(text = "Re-generate")
            }
            Spacer(Modifier.height(30.dp))

            LazyVerticalGrid(
                cells = GridCells.Adaptive(minSize = 75.dp),
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalArrangement = Arrangement.Center
            ) {
                itemsIndexed(cw.letters) { index, item ->
                    GameLetterBtn(cw,item,index)
                }
            }
        }

    }

@Composable
fun GameLetterBtn(cw: ConstructWord, cwLetter : ConstructWordMiniGameLetter, index : Int) {
    val context = LocalContext.current
    val color = if (cwLetter.selected) {
        if (isSystemInDarkTheme())
            md_theme_dark_primary
        else
            md_theme_light_primary
    } else Color.Gray
    val activity = (LocalContext.current as? Activity)

    Button(
        onClick = {
            cw.handleUpdate(cwLetter)

            println(cw.result)
            println(cw.word)
            if (cw.isCorrect()) {
                Toast.makeText(context, "Correct! Good morning", Toast.LENGTH_SHORT).show()
                activity!!.finish()
            }
        },
        shape = RoundedCornerShape(19.dp),
        modifier = Modifier.padding(5.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = color),
    ) {
        Text(
            text = cwLetter.letter,
            fontSize = 50.sp,
            modifier = Modifier.align(Alignment.CenterVertically),
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
@Preview
fun ConnectWordMiniGameScreenPreview() {
    ConstructWordMiniGameScreen (
        ConstructWord()
    )
}