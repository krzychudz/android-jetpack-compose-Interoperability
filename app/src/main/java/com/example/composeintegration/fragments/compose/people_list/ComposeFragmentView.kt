package com.example.composeintegration.fragments.compose.people_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.composeintegration.R
import com.example.composeintegration.composables.CenterContent
import com.example.composeintegration.composables.Header
import com.example.composeintegration.fragments.compose.people_list.ComposeFragmentViewModel.PeopleDataUiState
import com.example.composeintegration.network.models.User
import com.example.composeintegration.network.models.UserName
import com.example.composeintegration.network.models.UserPicture

class UserPreviewProvider : PreviewParameterProvider<User> {
    override val values: Sequence<User>
        get() = sequenceOf(User(
            UserName("First", "Last"),
            UserPicture(null, null),
            "tmpEmail@email.com",
            null,
            null,
            null
        ))
}



@ExperimentalCoilApi
@Composable
@Preview
fun ComposeScreen(viewModel: ComposeFragmentViewModel? = null, navController: NavController? = null) {
    val peopleDataState = viewModel?.peopleData?.value

    when(val navState = viewModel?.navigateToState?.value) {
        is ComposeFragmentViewModel.NavigateToState.ToDetailsFragment -> {
            navController?.navigate(ComposeFragmentDirections.actionComposeFragmentToPersonDetailsComposeFragment(navState.selectedPersonUUID))
        }
    }

    LaunchedEffect(key1 = Unit) {
        if (peopleDataState !is PeopleDataUiState.Success) {
            viewModel?.getUserData()
        }
    }

    Column(modifier = Modifier.fillMaxHeight()) {
        Header(text = stringResource(id = R.string.all_people_header))
        PeopleDataSection(peopleDataState, viewModel)
    }
}

@ExperimentalCoilApi
@Composable
fun PeopleDataSection(peopleDataState: PeopleDataUiState?, viewModel: ComposeFragmentViewModel?) {

    when (peopleDataState) {
        is PeopleDataUiState.InProgress -> CenterContent {
            CircularProgressIndicator()
        }
        is PeopleDataUiState.Error -> CenterContent {
            Text(text = stringResource(id = R.string.general_error, peopleDataState.exception.message ?: ""))
        }
        is PeopleDataUiState.Success -> {
            peopleDataState.users?.let { PeopleDataList(data = it, viewModel) }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun PeopleDataList(data: List<User>, viewModel: ComposeFragmentViewModel?) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(vertical = 8.dp)) {
        items(data, itemContent = { personData ->
            Person(personData) {
                viewModel?.navigateTo(ComposeFragmentViewModel.NavigateToState.ToDetailsFragment(
                    personData.login?.uuid
                ))
            }
        })
    }
}

@ExperimentalCoilApi
@Composable
@Preview
fun Person(@PreviewParameter(UserPreviewProvider::class) data: User, onPressedCallback: ((user: User) -> Unit)? = null) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onPressedCallback?.invoke(data)
        },
        elevation = 4.dp) {
        Row() {
            Image(
                painter = rememberImagePainter(
                    data.picture?.medium,
                    builder = {
                        placeholder(R.drawable.user_placeholder)
                    }
                ),
                contentDescription = null,
                modifier = Modifier.size(128.dp),
            )
            Column(Modifier.padding(8.dp)) {
                Text(
                    text ="${data.name?.first} ${data.name?.last}",
                    style = MaterialTheme.typography.h5
                )
                Text(data.email ?: "")
            }
        }
    }
}