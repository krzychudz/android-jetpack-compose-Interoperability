package com.example.composeintegration.fragments.compose.person_details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Upload
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.composeintegration.R
import com.example.composeintegration.composables.PlainHeader
import com.example.composeintegration.network.models.User

@ExperimentalAnimationApi
@Composable
fun PersonDetailsComposeScreen(userData: User?) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        PlainHeader(stringResource(id = R.string.overview_header))
        PersonOverview(userData = userData)
        PlainHeader(
            stringResource(id = R.string.extra_info_header),
            modifier = Modifier.padding(top = 6.dp)
        )
        AdditionalDataSection(userData = userData)
    }
}

@Composable
fun PersonOverview(userData: User?) {
    Card(elevation = 4.dp, modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Surface(
                shape = RoundedCornerShape(50)
            ) {
                Image(
                    painter = rememberImagePainter(
                        userData?.picture?.large,
                        builder = {
                            placeholder(R.drawable.user_placeholder)
                        }
                    ),
                    contentDescription = "Person image",
                    modifier = Modifier.size(128.dp),
                )
            }
            Column(modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)) {
                val userNameData = userData?.name
                Text(
                    text = "${userNameData?.first} ${userNameData?.last}",
                    style = MaterialTheme.typography.h5
                )
                Text(userData?.email ?: "", fontSize = 12.sp)
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun AdditionalDataSection(userData: User?) {
    var isAdditionalInfoExpanded by remember { mutableStateOf(false) }

    val userNameData = userData?.name
    val chevronAngle: Float by animateFloatAsState(
        targetValue = if (!isAdditionalInfoExpanded) 270F else 90F,
        animationSpec = tween(
            durationMillis = 100,
            easing = LinearEasing
        )
    )

    Card(
        elevation = 4.dp,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .width(16.dp)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("${userNameData?.first} ${userNameData?.last}")
                IconButton(
                    onClick = { isAdditionalInfoExpanded = !isAdditionalInfoExpanded },
                    modifier = Modifier.rotate(chevronAngle)
                ) {
                    Icon(Icons.Filled.ChevronLeft, "Chevron")
                }
            }
            Divider(color = Color.Black, thickness = 1.dp)
            AnimatedVisibility(visible = isAdditionalInfoExpanded) {
                Text("Tewst12121")
            }
        }
    }
}