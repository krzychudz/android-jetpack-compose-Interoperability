package com.example.composeintegration.fragments.compose.person_details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.composeintegration.R
import com.example.ui_composables.composables.PlainHeader
import com.example.ui_composables.composables.TextWithDefaultValue
import com.example.composeintegration.network.models.User
import com.example.composeintegration.network.models.UserName

@ExperimentalAnimationApi
@Composable
fun PersonDetailsComposeScreen(userData: User?) {
    LazyColumn(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        item {
            PlainHeader(stringResource(id = R.string.overview_header))
        }
        item {
            PersonOverview(userData = userData)
        }
        item {
            PlainHeader(
                stringResource(id = R.string.extra_info_header),
                modifier = Modifier.padding(top = 6.dp)
            )
        }
        item {
            AdditionalDataSection(userData = userData)
        }
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
                    modifier = Modifier.size(100.dp),
                )
            }
            Column(modifier = Modifier.padding(4.dp)) {
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
    var isAdditionalInfoExpanded by rememberSaveable { mutableStateOf(false) }

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
            AdditionalDataSectionHeader(userNameData = userNameData, chevronAngle = chevronAngle) {
                isAdditionalInfoExpanded = !isAdditionalInfoExpanded
            }
            Divider(color = Color.Black, thickness = 1.dp)
            AnimatedVisibility(visible = isAdditionalInfoExpanded) {
                AdditionalDataInfoSubsection(userData)
            }
        }
    }
}

@Composable
fun AdditionalDataSectionHeader(
    userNameData: UserName?,
    chevronAngle: Float,
    onChevronClicked: () -> Unit
) {
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
            onClick = onChevronClicked,
            modifier = Modifier.rotate(chevronAngle)
        ) {
            Icon(Icons.Filled.ChevronLeft, "Chevron")
        }
    }
}

@Composable
fun AdditionalDataInfoSubsection(userData: User?) {
    val userLocationData = userData?.location

    Column(modifier = Modifier.padding(8.dp)) {
        TextWithDefaultValue(
            resId = R.string.extra_info_email,
            args = userData?.email,
            modifier = Modifier.padding(vertical = 4.dp)
        )
        TextWithDefaultValue(
            resId = R.string.extra_info_phone,
            args = userData?.phone,
            modifier = Modifier.padding(vertical = 4.dp)
        )
        Divider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.padding(top = 12.dp, bottom = 12.dp, end = 16.dp)
        )
        Text(text = stringResource(id = R.string.extra_info_location), fontSize = 20.sp)
        TextWithDefaultValue(
            resId = R.string.extra_info_location_city,
            args = userLocationData?.city,
            modifier = Modifier.padding(vertical = 4.dp)
        )
        TextWithDefaultValue(
            resId = R.string.extra_info_location_state,
            args = userLocationData?.state,
            modifier = Modifier.padding(vertical = 4.dp)
        )
        TextWithDefaultValue(
            resId = R.string.extra_info_location_post_code,
            args = userLocationData?.postcode,
            modifier = Modifier.padding(vertical = 4.dp)
        )
    }
}