package com.example.wake_app.screens

import android.app.TimePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.wake_app.R
import com.example.wake_app.data.DataSource.gameButtons
import com.example.wake_app.data.DataSource.weekdayButtons
import com.example.wake_app.model.Alarm
import com.example.wake_app.model.AlarmRepository
import com.example.wake_app.model.ExternalAlarmRepository
import com.example.wake_app.model.SharedViewModel
import com.example.wake_app.ui.theme.md_theme_dark_background
import com.example.wake_app.ui.theme.md_theme_light_background
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AlarmEditScreen(navController: NavHostController, sharedViewModel: SharedViewModel) {
    val alarm = sharedViewModel.alarm
    var newAlarm = Alarm()
    var alarmName by remember { mutableStateOf("") }
    var ringTone by remember { mutableStateOf("All Star") }

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

    val focusManager = LocalFocusManager.current

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
                backgroundColor =
                    if (isSystemInDarkTheme()) md_theme_dark_background
                    else md_theme_light_background
            )
        },
        content = {
            Box (
                modifier = Modifier
                    .fillMaxSize()
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
                        label = { Text(text = "Alarm name", fontSize = 20.sp) },
                        modifier = Modifier
                            .padding(
                                start = 25.dp,
                                top = 0.dp,
                                end = 0.dp,
                                bottom = 0.dp
                            )
                            .align(Alignment.Start),
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
                        label = { Text(text = "Choose ringtone", fontSize = 20.sp) },
                        modifier = Modifier
                            .padding(
                                start = 25.dp,
                                top = 0.dp,
                                end = 0.dp,
                                bottom = 0.dp
                            )
                            .align(Alignment.Start),
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
                    if (alarm != null) {
                        newAlarm.vibrate = alarm.vibrate
                    }
                    var checkedState = remember { mutableStateOf(newAlarm.vibrate) }
                        Checkbox(
                            checked = checkedState.value,
                            onCheckedChange = {
                                checkedState.value = !checkedState.value
                                              },
                            modifier = Modifier
                                .padding(start = 30.dp)
                                .align(Alignment.Start)
                        )
                    newAlarm.vibrate = checkedState.value

                    Button(
                        onClick = {
                            try {
                                if (alarm != null) {
                                    val newAlarmTmp = Alarm(time.value, newAlarm.games, newAlarm.weekdays, alarmName, newAlarm.vibrate, true, alarm.id)
                                    repo.updateAlarm(alarm, newAlarmTmp)
                                    setAlarm(mContext, newAlarmTmp)
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                            navController.navigateUp()
                        },
                        shape = CircleShape,
                        //colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.main_accent_dark))
                    )
                    {
                        Text(text = "Save alarm", fontSize = 15.sp)
                    }

                }
            }
        }
    )
}
