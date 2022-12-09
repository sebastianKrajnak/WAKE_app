package com.example.wake_app.screens.minigames

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.wake_app.R
import com.example.wake_app.data.ConstructWordMiniGameLetter
import com.example.wake_app.data.SequenceNumber
import com.example.wake_app.model.minigames.ConstructWord
import com.example.wake_app.model.minigames.SortSequence
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SortSequenceMiGameScreen(ss: SortSequence) {
    val navController = rememberNavController()

    Column(
        Modifier
            .fillMaxSize()
            .background(colorResource(R.color.background_light)),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Text(text = ss.title,
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
                text = ss.result.value,
                fontSize = 35.sp,
                color = colorResource(R.color.text_color_white),
                textAlign = TextAlign.Center,
                modifier = Modifier.width((ss.numbers!!.size*35).dp)
            )
        }

        Button(onClick = {
            ss.newSequence()
        }
        ) {
            Text(text = "Re-generate")
        }

        LazyVerticalGrid(
            cells = GridCells.Adaptive(minSize = 100.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            itemsIndexed(ss.numbers) { index, item ->
                GameNumberBtn(ss,item,index)
            }
        }

    }

}

@Composable
fun GameNumberBtn(ss: SortSequence, sn : SequenceNumber, index : Int) {
    val color = if (sn.selected) colorResource(R.color.main_accent) else Color(152,152,152)

    Column (modifier = Modifier
        .padding(5.dp)
        .clip(RoundedCornerShape(10.dp))
        .background(color)
    ) {
        Button(
            onClick = {
                ss.handleUpdate(sn)

                System.out.println(ss.result)
                System.out.println(ss.seq)
                if (ss.isCorrect()) System.out.println("CORRECT")
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = color),
        ) {
            Text(
                text = sn.number.toString(),
                fontSize = 50.sp,
                color = colorResource(R.color.text_color_white),
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center,
            )
        }
    }

}

@Composable
@Preview
fun SortSequenceMiGameScreenPreview() {
    SortSequenceMiGameScreen (
        SortSequence(1,100,10)
    )
}