package com.example.wake_app.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import com.example.wake_app.R
import com.example.wake_app.data.DataSource.alarmSounds
import com.example.wake_app.data.DataSource.languages

@Composable
fun SettingsScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Settings") }, backgroundColor = colorResource(R.color.background_light))
        },
        content =  { PageContent() }
    )
}

@Composable
private fun PageContent(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(R.color.background_light)),
    ) {
        DropdownRow(Text = "Language", DropdownItems = languages)
        DropdownRow(Text = "Default alarm sound", DropdownItems = alarmSounds)
        SwitcherRow(Text = "Push notifications")
        SwitcherRow(Text = "Dark mode")
        AboutRow(Text = "About")
    }
}

@Composable
fun DropdownRow(Text: String, @StringRes DropdownItems: List<Int>, modifier: Modifier = Modifier) {
    Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
                //.background(colorResource(R.color.background_dark))
                .height(45.dp)
    ) {

        var mExpanded by remember { mutableStateOf(false) }

        var mSelectedText by remember { mutableStateOf("") }

        val icon = if (mExpanded)
            Icons.Rounded.KeyboardArrowUp
        else
            Icons.Rounded.KeyboardArrowDown

        Text(
            text = Text,
            modifier = modifier.padding(start = 8.dp),
            fontSize = 25.sp,
            color = colorResource(R.color.text_color_white),
        )
        Spacer(
            modifier
                .weight(1f)
                .fillMaxWidth()
        )
        Box(modifier = modifier
            .padding(end = 8.dp)
        ) {
            IconButton(onClick = { mExpanded = !mExpanded }) {
                Icon(icon, "", tint = Color.White)
            }
            DropdownMenu(
                expanded = mExpanded,
                onDismissRequest = { mExpanded = false })
            {
                DropdownItems.forEach { label ->
                    DropdownMenuItem(onClick = {
                        mSelectedText = label.toString()
                        mExpanded = false
                    }) {
                        Text(text = stringResource(label))
                    }
                }
            }
        }
    }
}

@Composable
fun SwitcherRow(Text: String, modifier: Modifier = Modifier) {
    val checkedState = remember { mutableStateOf(true) }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            //.background(colorResource(R.color.background_dark))
            .height(45.dp)
    ) {
        Text(
            text = Text,
            modifier = modifier.padding(start = 8.dp),
            fontSize = 25.sp,
            color = colorResource(R.color.text_color_white),
        )
        Spacer(
            modifier
                .weight(5f)
                .fillMaxWidth()
        )
        Switch(
            checked = checkedState.value,
            onCheckedChange = { checkedState.value = it },
            modifier = modifier.size(55.dp),
        )
    }
}

@Composable
fun AboutRow(Text: String, modifier: Modifier = Modifier) {
    val icon = Icons.Rounded.KeyboardArrowRight
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            //.background(colorResource(R.color.background_dark))
            .height(45.dp)
    ) {
        Text(
            text = Text,
            modifier = modifier.padding(start = 8.dp),
            fontSize = 25.sp,
            color = colorResource(R.color.text_color_white),
        )
        Spacer(
            modifier
                .weight(1f)
                .fillMaxWidth()
        )
        IconButton(
            modifier = modifier.padding(end = 8.dp),
            onClick = {/* TODO */ }) {
            Icon(icon, "", tint = Color.White)
        }
    }
}


@Composable
@Preview
fun SettingsScreenPreview() {
    SettingsScreen()
}