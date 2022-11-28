package com.example.wake_app.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.wake_app.BottomBarScreen
import com.example.wake_app.R
import com.example.wake_app.model.Alarm
import com.example.wake_app.model.AlarmRepository
import com.example.wake_app.model.ExternalAlarmRepository
import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileInputStream
import java.io.ObjectInputStream
import java.util.stream.Collectors.toList

val createAlarm = { /* Do something */ }


//var alarmList =  emptyList<Alarm>()


@Composable
fun HomeScreen(navController: NavHostController) {

    val context = LocalContext.current
    val repo: AlarmRepository by lazy { ExternalAlarmRepository(context) }
    val alarmList = repo.getAlarmList()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Alarms") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {navController.navigate(BottomBarScreen.AlarmCreation.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }},
                backgroundColor = Color.LightGray,
                content = {
                        Icon(Icons.Filled.Add,"")
                },
                modifier = Modifier.padding(bottom = 50.dp)
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
                        AlarmItem(Alarm = it)
                    }
                }
            }
        }
    )
}

@Composable
fun AlarmItem(Alarm: Alarm) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
//            .clickable{ },
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .background(colorResource(R.color.background_dark))
        ) {
            AlarmInformation(Alarm.time, Alarm.description)
        }
    }
}

@Composable
fun AlarmInformation(AlarmTime: String, AlarmDescription: String, modifier: Modifier = Modifier) {
    val checkedState = remember { mutableStateOf(true) }
    Row {
        Text(
            text = AlarmTime,
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.text_color_white)
        )
        Text(
            text = AlarmDescription,
            modifier = modifier.padding(start = 8.dp),
            fontSize = 20.sp,
            color = colorResource(R.color.text_color_white)
        )
        Spacer(
            Modifier
                .weight(1f)
                .fillMaxHeight())
        Switch(
            checked = checkedState.value,
            onCheckedChange = { checkedState.value = it },
            Modifier.size(55.dp),
        )
    }
}

@Composable
@Preview
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController)
}