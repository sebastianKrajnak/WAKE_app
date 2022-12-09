package com.example.wake_app

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@ExperimentalMaterialApi
@Composable
fun ExpandableCard(
    title: String,
    successPercentage: Double = 0.0,
    firstTryPercentage: Double = 0.0,
    avgTime: Double = 0.0,
    graph: Painter

) {
    var expanded by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(targetValue = if(expanded) 180f else 0f)

    Card(
        onClick = { expanded = !expanded},
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
            .padding(15.dp),
        shape =  RoundedCornerShape(4.dp),

    ){
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)) {
            Row( verticalAlignment = Alignment.CenterVertically){
                Text(
                    modifier = Modifier.weight(6f),
                    text = title,
                    fontSize = 24.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium)
                        .weight(1f)
                        .rotate(
                            rotation
                        ),
                    onClick = {
                    expanded = !expanded
                }) {
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                }
            }
            if(expanded) {
                Text( text = "Successful awakening: $successPercentage%" )
                Text(text = "Woke up on the first try: $firstTryPercentage%")
                Text(text = "Average solving time: $avgTime seconds")
                Image(
                    painter = graph,
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
@Preview
fun ExpandableCardPreview() {
    ExpandableCard(title = "Test", successPercentage = 19.0, firstTryPercentage = 13.6, avgTime = 24.4,
        graph = painterResource(id = R.drawable.success_rate_per_game)
    )
}
