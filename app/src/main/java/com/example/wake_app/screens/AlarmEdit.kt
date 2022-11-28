package com.example.wake_app.screens

import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.wake_app.R
import com.example.wake_app.data.DataSource.gameButtons
import com.example.wake_app.data.DataSource.weekdayButtons
import com.example.wake_app.model.*

import java.util.*

@Composable
fun AlarmEditScreen(navController: NavHostController, sharedViewModel: SharedViewModel) {
    val alarm = sharedViewModel.alarm
    var newAlarm = Alarm()
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
    if (alarm != null) {
        time.value = alarm.time
        alarmName = alarm.name
    }

    // Creating a TimePicker dialog
    val timePickerDialog = TimePickerDialog(
        mContext,
        {_, hour : Int, minute: Int ->
            time.value = String.format("%02d:%02d", hour, minute)
        }, hour, minute, true
    )

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Edit alarm") },
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
                    TextButton(onClick = { timePickerDialog.show() }) {
                        Text(
                            text = time.value,
                            fontSize = 90.sp,
                            )
                    }
                    newAlarm.time = time.value

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

                    if (alarm != null) {
                        newAlarm.games = alarm.games
                        GameList(gameList = gameButtons, newAlarm)
                    }


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
                            if (alarm != null) {
                                newAlarm.weekdays = alarm.weekdays
                                Weekday(weekdayButton = button, newAlarm)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(9.dp))


                    OutlinedTextField(
                        value = alarmName,
                        onValueChange = { if (it.length <= 15)
                            alarmName = it
                            newAlarm.name = alarmName
                                        },
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
                        shape = CircleShape
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

                    var checkedState by remember { mutableStateOf(true) }
                    if (alarm != null) {
                        newAlarm.vibrate = alarm.vibrate
                        checkedState = newAlarm.vibrate
                    }
                        Checkbox(
                            checked = checkedState,
                            onCheckedChange = {
                                checkedState = !checkedState
                                newAlarm.vibrate = checkedState
                                              },
                            modifier = Modifier
                                .padding(start = 30.dp)
                                .align(Alignment.Start)
                        )

                    Button(
                        onClick = {
                            try {
                                if (alarm != null) {
                                    repo.updateAlarm(alarm, Alarm(time.value, newAlarm.games, newAlarm.weekdays, alarmName, checkedState, true))
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                            navController.navigateUp()
                        },
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.input_field))
                    )
                    {
                        Text(text = "Save alarm",
                            fontSize = 15.sp,
                            color = colorResource(R.color.text_color_white)
                        )
                    }

                }
            }
        }
    )
}
