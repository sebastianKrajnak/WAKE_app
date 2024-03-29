package com.example.wake_app.screens

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.ui.graphics.ColorFilter
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
import com.example.wake_app.BROADCAST_REQUEST_CODE
import com.example.wake_app.R
import com.example.wake_app.data.DataSource.gameButtons
import com.example.wake_app.data.DataSource.weekdayButtons
import com.example.wake_app.model.*
import org.apache.commons.lang3.SerializationUtils
import com.example.wake_app.ui.theme.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AlarmCreationScreen(navController: NavHostController) {
    val alarm = Alarm()

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
                        label = { Text(text = "Ringtone", fontSize = 20.sp) },
                        placeholder = { Text(text = "All Star") },
                        readOnly = true,
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
                                setAlarm(mContext, alarm)
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                            navController.navigateUp()
                        },
                        shape = CircleShape,
                    )
                    {
                        Text(text = "Set alarm", fontSize = 15.sp)
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
    val color =
        if (selected) {
            if (isSystemInDarkTheme())
                md_theme_dark_secondary
            else
                md_theme_light_secondary
        }
        else {
            if (isSystemInDarkTheme())
                md_theme_dark_background
            else
                md_theme_light_background
        }

    val iconColorFilter =
        if (selected)
            ColorFilter.tint(
                if (isSystemInDarkTheme())
                    md_theme_dark_onSecondary
                else
                    md_theme_light_onSecondary
            )
        else
            ColorFilter.tint(
                if (isSystemInDarkTheme())
                    md_theme_dark_onBackground
                else
                    md_theme_light_onBackground
            )

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
        OutlinedButton(
            onClick = {
                selected = !selected
                alarm.games[gameBtn.index] = selected
                      },
            colors = ButtonDefaults.buttonColors(backgroundColor = color),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .size(60.dp, 60.dp),
            border = BorderStroke(1.dp, if (isSystemInDarkTheme()) md_theme_dark_secondary else md_theme_light_secondary)
        ) {
            Image(
                painter = painterResource(gameBtn.iconResourceId),
                contentDescription = null,
                modifier = Modifier.size(60.dp, 60.dp),
                colorFilter = iconColorFilter
            )
        }
    }
}


@Composable
fun Weekday(weekdayButton: WeekdayButton, alarm: Alarm) {
    var selected by remember { mutableStateOf(false) }
    selected = alarm.weekdays[weekdayButton.index]
    val color =
        if (selected) {
            if (isSystemInDarkTheme())
                md_theme_dark_secondary
            else
                md_theme_light_secondary
        }
        else {
            if (isSystemInDarkTheme())
                md_theme_dark_background
            else
                md_theme_light_background
        }

    OutlinedButton(
        onClick = {
            selected = !selected
            alarm.weekdays[weekdayButton.index] = selected
                  },
        shape = CircleShape,
        contentPadding = PaddingValues(6.dp),
        modifier = Modifier.size(40.dp),
        border = BorderStroke(1.dp,
            if (isSystemInDarkTheme())
                md_theme_dark_secondary
            else
                md_theme_light_secondary
        ),
        colors =  ButtonDefaults.buttonColors(
            backgroundColor = color
        )
    ){
        Text(text = weekdayButton.day)
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun AlarmCreationPreview() {
    val navController = rememberNavController()
    AlarmCreationScreen(navController)
}


@RequiresApi(Build.VERSION_CODES.O)
fun setAlarm(context: Context, alarm: Alarm) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, AlarmNotification::class.java)

    intent.putExtra("alarm", SerializationUtils.serialize(alarm))
    intent.action = alarm.id.toString()
    val pendingIntent = PendingIntent.getBroadcast(context, BROADCAST_REQUEST_CODE, intent, PendingIntent.FLAG_IMMUTABLE)

    val calendar: Calendar = Calendar.getInstance()
    val hours = alarm.time.split(":").get(0).toInt()
    val minutes = alarm.time.split(":").get(1).toInt()
    calendar.set(
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH),
        hours,
        minutes,
        0
    )

    val repeating: Boolean = alarm.weekdays.contains(true)
    if (repeating) {
        val timeTillNextTrigger = calculateRepeatingTime(alarm, calendar)
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeTillNextTrigger, pendingIntent)
    } else {
        if (calendar.timeInMillis <= System.currentTimeMillis()) calendar.add(Calendar.DAY_OF_YEAR, 1)
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
    }

    // Todo make pretty toast message
    Toast.makeText(context, calculateTimeTillAlarm(alarm, calendar), Toast.LENGTH_LONG).show()
}


@RequiresApi(Build.VERSION_CODES.O)
fun cancelAlarm(context: Context, alarm: Alarm) {
    val intent = Intent(context, AlarmNotification::class.java)
    intent.action = alarm.id.toString()
    PendingIntent.getBroadcast(context, BROADCAST_REQUEST_CODE, intent, PendingIntent.FLAG_IMMUTABLE).cancel()
}


private fun calculateRepeatingTime(alarm: Alarm, calendar: Calendar): Long {
    val calendarToWeekdaysMapping: HashMap<Int, Int> = hashMapOf(
        2 to 0,
        3 to 1,
        4 to 2,
        5 to 3,
        6 to 4,
        7 to 5,
        1 to 6,
    )
    var days = 0
    var currentIdx = calendarToWeekdaysMapping.get(calendar[Calendar.DAY_OF_WEEK])!!


    if (alarm.weekdays.get(currentIdx) && calendar.timeInMillis <= System.currentTimeMillis()) {
        if (currentIdx == 6) {
            currentIdx = 0
            days++
        } else {
            currentIdx++
            days++
        }
    }

    while (!alarm.weekdays.get(currentIdx)) {
        if (currentIdx == 6) {
            currentIdx = 0
            days++
        } else {
            currentIdx++
            days++
        }
    }

    calendar.add(Calendar.DAY_OF_YEAR, days)
    return calendar.timeInMillis
}

@RequiresApi(Build.VERSION_CODES.O)
fun calculateTimeTillAlarm(alarm: Alarm, calendar: Calendar) : String {
    if (alarm.weekdays.contains(true)) {
        val timeTillRun = calculateRepeatingTime(alarm, calendar)
        calendar.timeInMillis = timeTillRun - System.currentTimeMillis()
    } else {
        calendar.timeInMillis = calendar.timeInMillis - System.currentTimeMillis()


    }
    if (TimeUnit.DAYS.convert(calendar.timeInMillis, TimeUnit.MILLISECONDS) != 0L) {
        return "Alarm is set for " + TimeUnit.DAYS.convert(calendar.timeInMillis, TimeUnit.MILLISECONDS) + " days from now"
    } else if(TimeUnit.HOURS.convert(calendar.timeInMillis, TimeUnit.MILLISECONDS) != 0L) {
        return "Alarm is set for " + TimeUnit.HOURS.convert(calendar.timeInMillis, TimeUnit.MILLISECONDS) + " hours from now"
    } else if(TimeUnit.MINUTES.convert(calendar.timeInMillis, TimeUnit.MILLISECONDS) != 0L) {
        return "Alarm is set for " + TimeUnit.MINUTES.convert(calendar.timeInMillis, TimeUnit.MILLISECONDS) + " minutes from now"
    } else if(TimeUnit.SECONDS.convert(calendar.timeInMillis, TimeUnit.MILLISECONDS) != 0L) {
        return "Alarm is set for 1 minute from now"
    } else {
        return "Alarm is set!"
    }
}





















