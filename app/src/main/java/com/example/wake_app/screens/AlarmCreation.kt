package com.example.wake_app.screens

import android.graphics.Color.rgb
import android.media.RingtoneManager
import android.widget.ExpandableListView
import android.widget.NumberPicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.chargemap.compose.numberpicker.NumberPicker
import com.example.wake_app.R
import com.example.wake_app.data.DataSource
import com.example.wake_app.data.DataSource.gameButtons
import com.example.wake_app.model.GameButton
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

import java.util.*

@Composable
fun AlarmCreationScreen(navController: NavHostController) {
    var textLabel by remember { mutableStateOf("") }
    var ringTone by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Create alarm") },
                navigationIcon = if (navController.previousBackStackEntry != null) {
                {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            } else {
                null
            })
        },
        content = {
            Box (
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(R.color.background_light))
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TimePick()

                    Text(
                        text = "Games",
                        fontSize = 25.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier
                            .padding(
                                start = 25.dp,
                                top = 0.dp,
                                end = 0.dp,
                                bottom = 0.dp
                            )
                            .align(Alignment.Start),
                        color = colorResource(R.color.text_color_white)
                    )

                    GameList(gameList = gameButtons)

                    Text(
                        text = "Name",
                        color = colorResource(R.color.text_color_white),
                        fontSize = 25.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier
                            .padding(
                                start = 25.dp,
                                top = 0.dp,
                                end = 0.dp,
                                bottom = 0.dp
                            )
                            .align(Alignment.Start)
                    )
                    TextField(
                        value = textLabel,
                        onValueChange = { textLabel = it },
                        textStyle = TextStyle(
                            color = colorResource(R.color.text_color_white)
                        ),
                        modifier = Modifier
                            .padding(
                                start = 25.dp,
                                top = 0.dp,
                                end = 0.dp,
                                bottom = 0.dp
                            )
                            .align(Alignment.Start)
                            .clip(RoundedCornerShape(10.dp))
                            .background(colorResource(R.color.input_field))
                    )

                    Text(
                        text = "Alarm ringtone",
                        fontSize = 25.sp,
                        color = colorResource(R.color.text_color_white),
                        textAlign = TextAlign.Left,
                        modifier = Modifier
                            .padding(
                                start = 25.dp,
                                top = 0.dp,
                                end = 0.dp,
                                bottom = 0.dp
                            )
                            .align(Alignment.Start)
                    )
                    TextField(
                        value = ringTone,
                        onValueChange = { ringTone = it },
                        textStyle = TextStyle(
                            color = colorResource(R.color.text_color_white)
                        ),
                        modifier = Modifier
                            .padding(
                                start = 25.dp,
                                top = 0.dp,
                                end = 0.dp,
                                bottom = 0.dp
                            )
                            .align(Alignment.Start)
                            .clip(RoundedCornerShape(10.dp))
                            .background(colorResource(R.color.input_field))
                    )


                    Text(
                        text = "Vibrate",
                        fontSize = 25.sp,
                        color = colorResource(R.color.text_color_white),
                        textAlign = TextAlign.Left,
                        modifier = Modifier
                            .padding(
                                start = 25.dp,
                                top = 0.dp,
                                end = 0.dp,
                                bottom = 0.dp
                            )
                            .align(Alignment.Start)
                    )
                    val checkedState = remember { mutableStateOf(true) }
                        Checkbox(
                            checked = checkedState.value,
                            onCheckedChange = { checkedState.value = it },
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .align(Alignment.Start)
                        )

                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.input_field))
                    )
                    {
                        Text("Set alarm",
                            color = colorResource(R.color.text_color_white)
                        )
                    }

                }
            }
        }
    )
}

@Composable
fun TimePick() {
    Row (
        modifier = Modifier
            .padding(20.dp),
        horizontalArrangement  = Arrangement.Center
    ) {
        var hourValue by remember { mutableStateOf(0) }
        var minuteValue by remember { mutableStateOf(0) }

        Column {
            NumberPicker(
                value = hourValue,
                range = 0..23,
                onValueChange = {
                    hourValue = it
                },
                textStyle = TextStyle(
                    fontSize = 45.sp,
                    color = colorResource(R.color.text_color_white)
                )
            )
        }
        Column {
            NumberPicker(
                value = minuteValue,
                range = 0..59,
                onValueChange = {
                    minuteValue = it
                },
                textStyle = TextStyle(
                    fontSize = 45.sp,
                    color = colorResource(R.color.text_color_white)
                )
            )
        }
    }
}

@Composable
fun GameList(gameList : List<GameButton>) {
    // in the below line, we are creating a
    // lazy row for displaying a horizontal list view.
    LazyRow (horizontalArrangement = Arrangement.Start) {
        // in the below line, we are setting data for each item of our listview.
        itemsIndexed(gameList) { index, item ->
            GameItem(gameBtn = item)
        }
    }
}

@Composable
fun GameItem(gameBtn: GameButton) {
    var selected by remember { mutableStateOf(false) }
    val color = if (selected) colorResource(R.color.background_theme) else Color(152,152,152)
    Column (modifier = Modifier
        .padding(
            start = 25.dp,
            top = 0.dp,
            end = 0.dp,
            bottom = 25.dp
        )
        .clip(RoundedCornerShape(10.dp))
        .background(color)
    ) {
        Button(
            onClick = { selected = !selected },
            colors = ButtonDefaults.buttonColors(backgroundColor = color),
            modifier = Modifier
                .size(60.dp, 60.dp)
        ) {
            Image(
            painter = painterResource(gameBtn.iconResourceId),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp, 60.dp)
            )
        }
    }
}

@Composable
@Preview
fun AlarmCreationPreview() {
    val navController = rememberNavController()
    AlarmCreationScreen(navController)
}