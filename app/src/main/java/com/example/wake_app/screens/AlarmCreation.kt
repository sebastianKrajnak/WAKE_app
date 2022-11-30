package com.example.wake_app.screens

import android.app.TimePickerDialog
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.wake_app.R
import com.example.wake_app.data.DataSource.gameButtons
import com.example.wake_app.data.DataSource.weekdayButtons
import com.example.wake_app.model.*
import java.text.SimpleDateFormat

import java.util.*

@Composable
fun AlarmCreationScreen(navController: NavHostController) {
    val alarm = Alarm()

    var alarmName by remember { mutableStateOf("") }
    var ringTone by remember { mutableStateOf("") }

    val mContext = LocalContext.current
    val repo: AlarmRepository by lazy { ExternalAlarmRepository(mContext) }

    // Declaring and initializing a calendar
    val calendar = Calendar.getInstance()
    val hour = calendar[Calendar.HOUR_OF_DAY]
    val minute = calendar[Calendar.MINUTE]

    // Value for storing time as a string
    val time = remember { mutableStateOf("") }
    time.value = SimpleDateFormat("HH:mm").format(Date())

    // Creating a TimePicker dialog
    val timePickerDialog = TimePickerDialog(
        mContext,
        {_, hour : Int, minute: Int ->
            time.value = String.format("%02d:%02d", hour, minute)
        }, hour, minute, true
    )

    val focusManager = LocalFocusManager.current


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
            },
                backgroundColor = colorResource(R.color.background_light)
            )
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
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(5.dp)

                ) {
                    TextButton(onClick = {
                        timePickerDialog.show()
                    }) {
                        Text(
                            text = time.value,
                            fontSize = 90.sp,
                            )
                    }

                    Text(
                        text = "Games",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier
                            .padding(
                                start = 38.dp,
                                top = 0.dp,
                                end = 0.dp,
                                bottom = 0.dp
                            )
                            .align(Alignment.Start),
                        color = colorResource(R.color.text_color_white)
                    )

                    GameList(gameList = gameButtons, alarm)


                    Text(
                        text = "Repeat",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Left,
                        modifier = Modifier
                            .padding(
                                start = 38.dp,
                                top = 0.dp,
                                end = 0.dp,
                                bottom = 0.dp
                            )
                            .align(Alignment.Start),
                        color = colorResource(R.color.text_color_white)
                    )

                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ){
                        for(button in weekdayButtons){
                            Weekday(weekdayButton = button, alarm)
                        }
                    }

                    Spacer(modifier = Modifier.height(9.dp))


                    OutlinedTextField(
                        value = alarmName,
                        onValueChange = { if (it.length <= 15) {
                            alarmName = it
                            alarm.name = alarmName}},
                        label = { Text(text = "Alarm name", fontSize = 20.sp,
                                    color = colorResource(R.color.text_color_white))
                                },
                        modifier = Modifier
                            .padding(
                                start = 25.dp,
                                top = 0.dp,
                                end = 0.dp,
                                bottom = 0.dp
                            )
                            .align(Alignment.Start),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = colorResource(R.color.text_color_white),
                            focusedBorderColor = colorResource(R.color.main_accent),
                            unfocusedBorderColor = colorResource(R.color.main_accent_dark)
                        ),
                        shape = CircleShape,
                        maxLines = 1,
                        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(9.dp))

                    OutlinedTextField(
                        value = ringTone,
                        onValueChange = {ringTone = it},
                        label = { Text(text = "Choose ringtone", fontSize = 20.sp,
                            color = colorResource(R.color.text_color_white))
                        },
                        modifier = Modifier
                            .padding(
                                start = 25.dp,
                                top = 0.dp,
                                end = 0.dp,
                                bottom = 0.dp
                            )
                            .align(Alignment.Start),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            textColor = colorResource(R.color.text_color_white),
                            focusedBorderColor = colorResource(R.color.main_accent),
                            unfocusedBorderColor = colorResource(R.color.main_accent_dark)
                        ),
                        shape = CircleShape,
                        maxLines = 1,
                        keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(9.dp))

                    Text(
                        text = "Vibrate",
                        fontSize = 20.sp,
                        color = colorResource(R.color.text_color_white),
                        textAlign = TextAlign.Left,
                        modifier = Modifier
                            .padding(
                                start = 38.dp,
                                top = 0.dp,
                                end = 0.dp,
                                bottom = 0.dp
                            )
                            .align(Alignment.Start)
                    )
                    val checkedState = remember { mutableStateOf(true) }
                        Checkbox(
                            checked = checkedState.value,
                            onCheckedChange = {
                                checkedState.value = it
                                              },
                            modifier = Modifier
                                .padding(start = 30.dp)
                                .align(Alignment.Start)
                        )

                    alarm.vibrate = checkedState.value
                    alarm.time = time.value
                    Button(
                        onClick = {
                            try {
                                repo.addAlarm(alarm)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                            navController.navigateUp()
                        },
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.main_accent_dark))
                    )
                    {
                        Text(text = "Set alarm",
                            fontSize = 15.sp,
                            color = colorResource(R.color.text_color_white)
                        )
                    }

                }
            }
        }
    )
}

@Composable
fun GameList(gameList : List<GameButton>, alarm: Alarm) {
    // in the below line, we are creating a
    // lazy row for displaying a horizontal list view.
    LazyRow (horizontalArrangement = Arrangement.Start) {
        // in the below line, we are setting data for each item of our listview.
        itemsIndexed(gameList) { _, item ->
            GameItem(gameBtn = item, alarm)
        }
    }
}

@Composable
fun GameItem(gameBtn: GameButton, alarm: Alarm) {
    var selected by remember { mutableStateOf(false) }
    selected = alarm.games[gameBtn.index]
    val color = if (selected) colorResource(R.color.main_accent) else Color(152,152,152)

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
            onClick = {
                selected = !selected
                alarm.games[gameBtn.index] = selected
                      },
            colors = ButtonDefaults.buttonColors(backgroundColor = color),
            modifier = Modifier
                .size(60.dp, 60.dp)
        ) {
            Image(
            painter = painterResource(gameBtn.iconResourceId),
            contentDescription = null,
            modifier = Modifier.size(60.dp, 60.dp)
            )
        }
    }
}


@Composable
fun Weekday(weekdayButton: WeekdayButton, alarm: Alarm) {
    var selected by remember { mutableStateOf(false) }
    selected = alarm.weekdays[weekdayButton.index]
    val color = if (selected) colorResource(R.color.main_accent) else colorResource(R.color.background_light)

    OutlinedButton(
        onClick = {
            selected = !selected
            alarm.weekdays[weekdayButton.index] = selected
                  },
        shape = CircleShape,
        contentPadding = PaddingValues(6.dp),
        modifier = Modifier.size(40.dp),
        border = BorderStroke(1.dp, colorResource(R.color.main_accent_dark)),
        colors =  ButtonDefaults.buttonColors(
            backgroundColor = color
        )
    ){
        Text(text = weekdayButton.day, color = colorResource(R.color.text_color_white))
    }
}


@Composable
@Preview
fun AlarmCreationPreview() {
    val navController = rememberNavController()
    AlarmCreationScreen(navController)
}