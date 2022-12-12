package com.example.wake_app.screens.minigames

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wake_app.R
import com.example.wake_app.model.minigames.MemorizeSequence
import com.example.wake_app.ui.theme.*
import kotlinx.coroutines.launch

@Composable
fun MemorizeSequenceScreen(ms: MemorizeSequence) {
    val context = LocalContext.current
    val btnSize = 90.dp

    Column(
        Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) md_theme_dark_background else md_theme_light_background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = ms.title,
            fontSize = 30.sp,
            modifier = Modifier
                .padding(16.dp)
                .align(alignment = Alignment.CenterHorizontally)
            ,
            textAlign = TextAlign.Center,
            color = if (isSystemInDarkTheme()) md_theme_dark_onBackground else md_theme_light_onBackground

        )

        Spacer(Modifier.height(60.dp))

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
            enabled = ms.showAgain.value,
            shape = RoundedCornerShape(19.dp),
        ) {
            Text(text =
            if (ms.firstRepeat.value)
            "Show sequence"
            else "Show again " + ms.showAgainTimerStr.value
            )
        }

        Spacer(Modifier.height(20.dp))

        Row(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
        ) {
            IconButton(
                index = 0,
                ms = ms,
                context = context,
                btnSize = btnSize,
                icon = painterResource(R.drawable.triangle)
            )

            Spacer(Modifier.weight(1f))

            IconButton(
                index = 1,
                ms = ms,
                context = context,
                btnSize = btnSize,
                icon = painterResource(R.drawable.circle)
            )
        }

        Row(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(
                index = 2,
                ms = ms,
                context = context,
                btnSize = btnSize,
                icon = painterResource(R.drawable.pentagon)
            )
        }


        Row(modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp)
        ) {
            IconButton(
                index = 3,
                ms = ms,
                context = context,
                btnSize = btnSize,
                icon = painterResource(R.drawable.cross)
            )

            Spacer(Modifier.weight(1f))

            IconButton(
                index = 4,
                ms = ms,
                context = context,
                btnSize = btnSize,
                icon = painterResource(R.drawable.square)
            )
        }

    }

}

@Composable
fun IconButton(
    index: Int,
    ms: MemorizeSequence,
    context: Context,
    btnSize: Dp,
    icon: Painter
) {
    val colorEnabled = if (isSystemInDarkTheme()) md_theme_dark_primary else md_theme_light_primary
    val colorDisabled = if (isSystemInDarkTheme()) md_theme_dark_surface else md_theme_light_surface
    val colorIcon = ColorFilter.tint(
        if (isSystemInDarkTheme()) md_theme_dark_onSurface
        else md_theme_light_onSurface
    )

    Button(
        onClick = {
            ms.addToSolution(index, context)
        },
        enabled = ms.symbolsDisabled.value,
        modifier = Modifier
            .size(btnSize),
        shape = RoundedCornerShape(25.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor =
                if (ms.symbols[index].selected) colorEnabled else colorDisabled,
            disabledBackgroundColor =
                if (ms.symbols[index].selected) colorEnabled else colorDisabled
        )
    ) {
        Image(
            painter = icon,
            contentDescription = null,
            colorFilter = colorIcon
        )
    }
}

@Preview
@Composable
fun MemorizeSequenceScreenPreview() {
    MemorizeSequenceScreen(
        MemorizeSequence(5,200)
    )
}