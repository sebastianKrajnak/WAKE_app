package com.example.wake_app.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.wake_app.BottomBarScreen
import com.example.wake_app.R
import com.example.wake_app.activity.ClickSequenceGameActivity
import com.example.wake_app.data.DataSource.alarmSounds
import com.example.wake_app.data.DataSource.languages
import com.example.wake_app.model.minigames.ConstructWord
import com.example.wake_app.ui.theme.md_theme_dark_background
import com.example.wake_app.ui.theme.md_theme_light_background
import com.example.wake_app.ui.theme.md_theme_switch_unchecked_thumb

@Composable
fun SettingsScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                backgroundColor =
                if (isSystemInDarkTheme()) md_theme_dark_background
                else md_theme_light_background
            )
        },
        content =  { PageContent(navController = navController) }
    )
}

@Composable
private fun PageContent(modifier: Modifier = Modifier, navController: NavHostController) {

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        DropdownRow(Text = "Language", DropdownItems = languages)
        DropdownRow(Text = "Default alarm sound", DropdownItems = alarmSounds)
        SwitcherRow(Text = "Dark mode", switchOn = isSystemInDarkTheme())
        AboutRow(Text = "About")
    }
}

@Composable
fun DropdownRow(Text: String, @StringRes DropdownItems: List<Int>, modifier: Modifier = Modifier) {
    Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp)
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
fun SwitcherRow(Text: String, modifier: Modifier = Modifier, switchOn: Boolean = true) {
    val checkedState = remember { mutableStateOf(switchOn) }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(45.dp)
    ) {
        Text(
            text = Text,
            modifier = modifier.padding(start = 8.dp),
            fontSize = 25.sp,
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
            colors = SwitchDefaults.colors(
                uncheckedThumbColor = md_theme_switch_unchecked_thumb
            )
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
            .height(45.dp)
    ) {
        Text(
            text = Text,
            modifier = modifier.padding(start = 8.dp),
            fontSize = 25.sp,
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
    val navController = rememberNavController()
    SettingsScreen(navController)
}