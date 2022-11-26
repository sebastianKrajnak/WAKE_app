package com.example.wake_app.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.stringResource
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
import com.example.wake_app.data.DataSource.alarms
import com.example.wake_app.data.DataSource.weekdayButtons
import com.example.wake_app.ui.theme.MainAccent
import com.example.wake_app.ui.theme.inter

val createAlarm = { /* Do something */ }

@Composable
fun HomeScreen(navController: NavHostController) {
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
                modifier = Modifier.padding(bottom = 80.dp, end = 15.dp)
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(R.color.background_light)),
            ) {
                LazyColumn {
                    items(alarms) {
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
fun AlarmInformation(@StringRes AlarmTime: Int, AlarmDescription: Int, modifier: Modifier = Modifier) {
    val checkedState = remember { mutableStateOf(true) }
    Column {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 8.dp, bottom = 1.dp).defaultMinSize(minHeight = 2.dp)
                ){
            Text(
                text = stringResource(AlarmTime),
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = inter,
                color = colorResource(R.color.text_color_white),
                modifier = Modifier.padding(bottom = 0.dp)
            )
            Text(
                text = stringResource(AlarmDescription),
                modifier = modifier.padding(start = 9.dp),
                fontSize = 20.sp,
                fontFamily = inter,
                color = colorResource(R.color.text_color_white)
            )
            Spacer(
                Modifier
                    .weight(1f)
                    .fillMaxHeight())
            Switch(
                checked = checkedState.value,
                onCheckedChange = { checkedState.value = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = colorResource(id = R.color.main_accent),
                    uncheckedThumbColor = Color.LightGray,
                    checkedTrackColor = colorResource(id = R.color.main_accent_dark),
                    uncheckedTrackColor = Color.DarkGray,
                ),
                modifier = Modifier.size(55.dp),
            )
        }
        Row (
            modifier = Modifier.fillMaxWidth().padding(bottom = 2.dp),
            //horizontalArrangement = Arrangement.SpaceEvenly,
                ){
            for(day in weekdayButtons){
                Text(
                    text = day.day,
                    fontSize = 12.sp,
                    color = colorResource(R.color.text_color_white),
                    modifier = Modifier.padding(start = 12.dp)
                )
            }
        }
    }
}

@Composable
@Preview
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController)
}