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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.wake_app.BottomBarScreen
import com.example.wake_app.BottomNavGraph
import com.example.wake_app.model.Alarm
import com.example.wake_app.data.DataSource.alarms

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
                modifier = Modifier.padding(bottom = 50.dp)
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
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
                .background(Color.Cyan)
        ) {
            AlarmInformation(Alarm.time, Alarm.description)
        }
    }
}

@Composable
fun AlarmInformation(@StringRes AlarmTime: Int, AlarmDescription: Int, modifier: Modifier = Modifier) {
    val checkedState = remember { mutableStateOf(true) }
    Row {
        Text(
            text = stringResource(AlarmTime),
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = stringResource(AlarmDescription),
            modifier = modifier.padding(start = 8.dp),
            fontSize = 20.sp,
            color = Color.Black
        )
        Spacer(Modifier.weight(1f).fillMaxHeight())
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