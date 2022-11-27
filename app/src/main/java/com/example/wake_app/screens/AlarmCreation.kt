package com.example.wake_app.screens

import android.app.AlarmManager
import android.app.TimePickerDialog
import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.wake_app.R
import com.example.wake_app.data.DataSource.gameButtons
import com.example.wake_app.data.DataSource.weekdayButtons
import com.example.wake_app.model.GameButton
import com.example.wake_app.model.WeekdayButton
import java.text.SimpleDateFormat
import java.util.*
import android.app.PendingIntent

import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AlarmCreationScreen(navController: NavHostController) {
    var textLabel by remember { mutableStateOf("") }
    var ringTone by remember { mutableStateOf("") }

    val mContext = LocalContext.current

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
                    //TimePick()
                    TextButton(onClick = { timePickerDialog.show() }) {
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

                    GameList(gameList = gameButtons)


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
                            Weekday(weekdayButton = button)
                        }
                    }

                    Spacer(modifier = Modifier.height(9.dp))


                    OutlinedTextField(
                        value = textLabel,
                        onValueChange = { if (it.length <= 15) textLabel = it},
                        label = { Text(text = "Label", fontSize = 20.sp,
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

                    OutlinedTextField(
                        value = ringTone,
                        onValueChange = {ringTone = it},
                        label = { Text(text = "Alarm ringtone", fontSize = 20.sp,
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
                    val checkedState = remember { mutableStateOf(true) }
                        Checkbox(
                            checked = checkedState.value,
                            onCheckedChange = { checkedState.value = it },
                            modifier = Modifier
                                .padding(start = 30.dp)
                                .align(Alignment.Start)
                        )

                    Button(
                        onClick = {setAlarm(mContext, timePickerDialog)},
                        shape = CircleShape,
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(R.color.input_field))
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
fun GameList(gameList : List<GameButton>) {
    // in the below line, we are creating a
    // lazy row for displaying a horizontal list view.
    LazyRow (horizontalArrangement = Arrangement.Start) {
        // in the below line, we are setting data for each item of our listview.
        itemsIndexed(gameList) { _, item ->
            GameItem(gameBtn = item)
        }
    }
}

@Composable
fun GameItem(gameBtn: GameButton) {
    var selected by remember { mutableStateOf(false) }
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
            onClick = { selected = !selected },
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
fun Weekday(weekdayButton: WeekdayButton) {
    var selected by remember { mutableStateOf(false) }
    val color = if (selected) colorResource(R.color.main_accent) else colorResource(R.color.background_light)
    OutlinedButton(
        onClick = {
            selected = !selected
            // create a bool array in AlarmClass where index == WeekdayButton.index and change val from false to true
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


@RequiresApi(Build.VERSION_CODES.O)
fun setAlarm(context: Context, timePickerDialog: TimePickerDialog) {
    val sec =  10
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, AlarmNotification::class.java)
    val days = false // Todo set repeating days
    intent.putExtra("2", days);
    val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)

    } else {
        TODO("VERSION.SDK_INT < M")
    }


    val calendar: Calendar = Calendar.getInstance()
    calendar.set(
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH),
        21,
        19,
        0
    )





    val repeating: Boolean = true
    if (repeating) {
        // Todo get range
        val interval : Long = 1000 * 60 * 1
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, interval, pendingIntent)
    } else {
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
    }

    Toast.makeText(context, "Alarm is set for 10 sec from now", Toast.LENGTH_LONG).show()
    Log.d("Alarm Bell", "testsss")
    System.out.println("Testing")
}
