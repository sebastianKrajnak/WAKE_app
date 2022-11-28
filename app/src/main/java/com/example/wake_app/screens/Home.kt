package com.example.wake_app.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.wake_app.BottomBarScreen
import com.example.wake_app.R
import com.example.wake_app.model.Alarm
import com.example.wake_app.data.DataSource.gameButtons
import com.example.wake_app.data.DataSource.weekdayButtons
import com.example.wake_app.model.AlarmRepository
import com.example.wake_app.model.ExternalAlarmRepository
import com.example.wake_app.model.SharedViewModel
import com.example.wake_app.ui.theme.inter


@Composable
fun HomeScreen(navController: NavHostController, sharedViewModel: SharedViewModel) {
    val context = LocalContext.current
    val repo: AlarmRepository by lazy { ExternalAlarmRepository(context) }
    var alarmList = repo.getAlarmList()

    Scaffold(

        topBar = {
            TopAppBar(title = { Text("Alarms") }, backgroundColor = colorResource(R.color.background_light))
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {navController.navigate(BottomBarScreen.AlarmCreation.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }},
                backgroundColor = colorResource(R.color.main_accent_dark),
                content = {
                        Icon(Icons.Filled.Add,"")
                },
                modifier = Modifier.padding(bottom = 30.dp, end = 15.dp)
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(R.color.background_light)),
            ) {
                LazyColumn {
                    items(alarmList) {
                        AlarmItem(alarm = it, navController, repo, sharedViewModel)
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AlarmItem(alarm: Alarm, NavController: NavHostController, repo: AlarmRepository, sharedViewModel: SharedViewModel) {
    var expanded by remember { mutableStateOf(false) }
    val checkedState = remember { mutableStateOf(alarm.active) }
    val textColor = if (checkedState.value) colorResource(R.color.text_color_white) else Color.DarkGray
    val iconColorFilter = if (checkedState.value)
        ColorFilter.tint(colorResource(R.color.main_accent))
    else
        ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0F) })

    Card(
        onClick = { expanded = !expanded},
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        elevation = 1.dp,
        border = BorderStroke(0.dp, colorResource(R.color.background_light ))
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AlarmInformation(alarm)
                Switch(
                    checked = checkedState.value,
                    onCheckedChange = {
                        checkedState.value = it
                        repo.updateAlarm(alarm, Alarm(alarm.time, alarm.games, alarm.weekdays, alarm.name, !alarm.active))
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = colorResource(id = R.color.main_accent),
                        uncheckedThumbColor = Color.LightGray,
                        checkedTrackColor = colorResource(id = R.color.main_accent_dark),
                        uncheckedTrackColor = Color.DarkGray,
                    ),
                    modifier = Modifier.size(55.dp)
                )
            }

            if (expanded) {
                Column {
                    Row {
                        Text(
                            text = "Selected games:  ",
                            Modifier.padding(start = 8.dp),
                            color = textColor
                        )
                        // TODO add if to check for selected games from the alarm object
                        for (game in gameButtons) {
                            if (alarm.games[game.index]) {
                                Image(
                                    painter = painterResource(game.iconResourceId),
                                    contentDescription = null,
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier
                                        .size(23.dp)
                                        .padding(2.dp),
                                    colorFilter = iconColorFilter,
                                )
                            }
                        }
                    }
                    Row (
                        Modifier
                            .padding(start = 18.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        IconButton(onClick = {
                                repo.deleteAlarm(alarm)
                                NavController.navigate(BottomBarScreen.Home.route)
                        }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = null,
                                tint = colorResource(R.color.main_accent)
                            )
                        }

                        IconButton(
                            onClick = {
                                sharedViewModel.addAlarmView(alarm)
                                NavController.navigate(BottomBarScreen.AlarmEdit.route) {
                                    popUpTo(NavController.graph.findStartDestination().id)
                                    launchSingleTop = true
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = null,
                                    tint = colorResource(R.color.main_accent)
                                )
                        }
                        
                    }
                }
            }
        }
    }
}

@Composable
fun AlarmInformation(alarm: Alarm, modifier: Modifier = Modifier) {
    val textColor = if (alarm.active) colorResource(R.color.text_color_white) else Color.DarkGray

    Column {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 8.dp, bottom = 0.dp)
                .defaultMinSize(minHeight = 2.dp)
                ){
            Text(
                text = alarm.time,
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = inter,
                color = textColor,
                modifier = Modifier.padding(bottom = 0.dp),
                style = TextStyle(
                    lineHeight = 0.em
                )
            )
            Text(
                text = alarm.name,
                modifier = modifier.padding(start = 9.dp),
                fontSize = 20.sp,
                fontFamily = inter,
                color = textColor
            )
        }

        Row (
            modifier = Modifier
                .padding(bottom = 2.dp),
                ){
            for(day in weekdayButtons){
                Text(
                    text = day.day,
                    fontSize = 12.sp,
                    color =
                        if (alarm.active and alarm.weekdays[day.index])
                            colorResource(R.color.main_accent)
                        else
                            Color.DarkGray,
                                    modifier = Modifier.padding(start = 12.dp)
                )
            }
        }
    }
}

/*
@Composable
@Preview
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController)
}*/
