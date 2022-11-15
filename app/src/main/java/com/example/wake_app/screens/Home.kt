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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wake_app.R
import com.example.wake_app.model.Alarm
import com.example.wake_app.data.DataSource.alarms

val createAlarm = { /* Do something */ }

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Alarms") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = createAlarm,
                backgroundColor = colorResource(R.color.background_dark),
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
                    .background(colorResource(R.color.background_dark)),
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
        backgroundColor = colorResource(R.color.background_dark),
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
    Row {
        Text(
            text = stringResource(AlarmTime),
            fontSize = 35.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.text_color_white)
        )
        Text(
            text = stringResource(AlarmDescription),
            modifier = modifier.padding(start = 8.dp),
            fontSize = 20.sp,
            color = colorResource(R.color.text_color_white)
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
    HomeScreen()
}