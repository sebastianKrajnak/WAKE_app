package com.example.wake_app.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.wake_app.data.DataSource.alarmSounds
import com.example.wake_app.data.DataSource.languages

@Composable
fun SettingsScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Settings") })
        },
        content =  { PageContent() }
    )
}

@Composable
fun PageContent(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black),
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
                .background(Color.Cyan)
                .height(45.dp)
    ) {

        var mExpanded by remember { mutableStateOf(false) }

        var mSelectedText by remember { mutableStateOf("") }

        val icon = if (mExpanded)
            Icons.Filled.KeyboardArrowUp
        else
            Icons.Filled.KeyboardArrowDown

        Text(
            text = Text,
            modifier = modifier.padding(start = 8.dp),
            fontSize = 30.sp,
            color = Color.Black,
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
                Icon(icon, "")
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
            .background(Color.Cyan)
            .height(45.dp)
    ) {
        Text(
            text = Text,
            modifier = modifier.padding(start = 8.dp),
            fontSize = 30.sp,
            color = Color.Black,
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
    val icon = Icons.Filled.KeyboardArrowRight
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.Cyan)
            .height(45.dp)
    ) {
        Text(
            text = Text,
            modifier = modifier.padding(start = 8.dp),
            fontSize = 30.sp,
            color = Color.Black,
        )
        Spacer(
            modifier
                .weight(1f)
                .fillMaxWidth()
        )
        IconButton(
            modifier = modifier.padding(end = 8.dp),
            onClick = {/* TODO */ }) {
            Icon(icon, "")
        }
    }
}


@Composable
@Preview
fun SettingsScreenPreview() {
    SettingsScreen()
}