package com.example.wake_app.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wake_app.data.Alarm
import com.example.wake_app.data.alarms

@Composable
fun HomeScreen() {
    LazyColumn {
        items(alarms) {
            AlarmItem(Alarm = it)
        }
    }
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
            fontSize = 35.sp
        )
        Text(
            text = stringResource(AlarmDescription),
            modifier = modifier.padding(start = 8.dp),
            fontSize = 20.sp
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