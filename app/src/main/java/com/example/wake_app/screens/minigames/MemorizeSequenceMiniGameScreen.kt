package com.example.wake_app.screens.minigames

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wake_app.R
import com.example.wake_app.model.minigames.MemorizeSequence
import kotlinx.coroutines.launch

@Composable
fun MemorizeSequenceScreen(ms: MemorizeSequence) {
    val btnSize = 90.dp
    Column(
        Modifier
            .fillMaxSize()
            .background(colorResource(R.color.background_light)),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Text(text = ms.title,
            fontSize = 30.sp,
            modifier = Modifier
                .padding(16.dp)
                .align(alignment = Alignment.CenterHorizontally)
            ,
            color = colorResource(R.color.text_color_white),
        )

        val scope = rememberCoroutineScope()
        Button(onClick = {
            ms.firstRepeat.value = false
            scope.launch {
                ms.showAgain.value = false
                ms.showSequence()
                ms.showBtnTimer()
                ms.showAgain.value = true
            }
        },
            enabled = ms.showAgain.value
        ) {
            Text(text =
            if (ms.firstRepeat.value)
            "Show sequence"
            else "Show again " + ms.showAgainTimerStr.value
            )
        }

        Row(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .fillMaxWidth()
        ) {
            Button(
                onClick = {
                    ms.addToSolution(0)
                },
                enabled = ms.symbolsDisabled.value,
                modifier = Modifier
                    .size(btnSize),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor =
                    if (ms.symbols[0].selected)
                        colorResource(R.color.main_accent)
                    else
                        Color(152,152,152),
                    disabledBackgroundColor =
                    if (ms.symbols[0].selected)
                        colorResource(R.color.main_accent)
                    else
                        Color(152,152,152)
            )) {
                Image(
                    painter = painterResource(R.drawable.triangle),
                    contentDescription = null
                )
            }

            Spacer(Modifier.weight(1f))

            Button(
                onClick = {
                    ms.addToSolution(1)
                },
                enabled = ms.symbolsDisabled.value,
                modifier = Modifier
                    .size(btnSize),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor =
                    if (ms.symbols[1].selected)
                        colorResource(R.color.main_accent)
                    else
                        Color(152,152,152),
                    disabledBackgroundColor =
                    if (ms.symbols[1].selected)
                        colorResource(R.color.main_accent)
                    else
                        Color(152,152,152)
                ),
                content = {
                    Image(
                        painter = painterResource(R.drawable.circle),
                        contentDescription = null
                    )
                }
            )
        }

        Row(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    ms.addToSolution(2)
                },
                enabled = ms.symbolsDisabled.value,
                modifier = Modifier
                    .size(btnSize),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor =
                    if (ms.symbols[2].selected)
                        colorResource(R.color.main_accent)
                    else
                        Color(152,152,152),
                    disabledBackgroundColor =
                    if (ms.symbols[2].selected)
                        colorResource(R.color.main_accent)
                    else
                        Color(152,152,152)
                )) {
                Image(
                    painter = painterResource(R.drawable.star),
                    contentDescription = null
                )
            }
        }


        Row(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .fillMaxWidth()
        ) {
            Button(
                onClick = {
                    ms.addToSolution(3)
                },
                enabled = ms.symbolsDisabled.value,
                modifier = Modifier
                    .size(btnSize),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor =
                    if (ms.symbols[3].selected)
                        colorResource(R.color.main_accent)
                    else
                        Color(152,152,152),
                    disabledBackgroundColor =
                    if (ms.symbols[3].selected)
                        colorResource(R.color.main_accent)
                    else
                        Color(152,152,152)
                )) {
                Image(
                    painter = painterResource(R.drawable.cross),
                    contentDescription = null
                )
            }

            Spacer(Modifier.weight(1f))

            Button(
                onClick = {
                    ms.addToSolution(4)
                },
                enabled = ms.symbolsDisabled.value,
                modifier = Modifier
                    .size(btnSize),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor =
                    if (ms.symbols[4].selected)
                        colorResource(R.color.main_accent)
                    else
                        Color(152,152,152),
                    disabledBackgroundColor =
                        if (ms.symbols[4].selected)
                            colorResource(R.color.main_accent)
                        else
                            Color(152,152,152)
                )) {
                Image(
                    painter = painterResource(R.drawable.square),
                    contentDescription = null
                )
            }
        }

    }
}


@Preview
@Composable
fun MemorizeSequenceScreenPreview() {
    MemorizeSequenceScreen(
        MemorizeSequence(5,200)
    )
}