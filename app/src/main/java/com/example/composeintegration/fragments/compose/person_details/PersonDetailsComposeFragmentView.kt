package com.example.composeintegration.fragments.compose.person_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.composeintegration.R
import com.example.composeintegration.composables.PlainHeader
import com.example.composeintegration.network.models.User

@Composable
fun PersonDetailsComposeScreen(userData: User?) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        PlainHeader(stringResource(id = R.string.overview_header))
        PersonOverview(userData = userData)
        PlainHeader(stringResource(id = R.string.extra_info_header), modifier = Modifier.padding(top = 6.dp))
    }
}

@Composable
fun PersonOverview(userData: User?) {
    Card(elevation = 4.dp, modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
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
            Column(modifier = Modifier.padding(horizontal = 4.dp)) {
                val userNameData = userData?.name
                Text(
                    text ="${userNameData?.first} ${userNameData?.last}",
                    style = MaterialTheme.typography.h5
                )
                Text(userData?.email ?: "", fontSize = 12.sp)
            }
        }
    }
}