package com.example.wake_app.screens.minigames

import android.app.Activity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.wake_app.R
import com.example.wake_app.data.SequenceNumber
import com.example.wake_app.model.minigames.SortSequence
import com.example.wake_app.ui.theme.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SortSequenceMiGameScreen(ss: SortSequence) {
    val navController = rememberNavController()

    Column(
        Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) md_theme_dark_background else md_theme_light_background),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Text(text = ss.title,
            fontSize = 30.sp,
            modifier = Modifier
                .padding(16.dp)
                .align(alignment = Alignment.CenterHorizontally),
            color = if (isSystemInDarkTheme()) md_theme_dark_onBackground else md_theme_light_onBackground

        )
        Card(
            modifier = Modifier
                .padding(8.dp) // margin
                .padding(16.dp), // space between the borders
            elevation = 18.dp
        ) {
            Text(
                text = ss.result.value,
                fontSize = 35.sp,
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
        Spacer(Modifier.height(30.dp))

        LazyVerticalGrid(
            cells = GridCells.Adaptive(minSize = 100.dp),
            modifier = Modifier.padding(10.dp),
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
    val color = if (sn.selected) {
        if (isSystemInDarkTheme())
            md_theme_dark_primary
        else
            md_theme_light_primary
    } else Color.Gray

    val activity = (LocalContext.current as? Activity)

    Button(
        onClick = {
            ss.handleUpdate(sn)

            System.out.println(ss.result)
            System.out.println(ss.seq)
            if (ss.isCorrect()) activity!!.finish()
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = color),
        shape = RoundedCornerShape(19.dp),
        modifier = Modifier.padding(5.dp),
    ) {
        Text(
            text = sn.number.toString(),
            fontSize = 50.sp,
            modifier = Modifier.fillMaxSize(),
            textAlign = TextAlign.Center,
        )
    }

}

@Composable
@Preview
fun SortSequenceMiGameScreenPreview() {
    SortSequenceMiGameScreen (
        SortSequence(1,99,10)
    )
}