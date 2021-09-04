package com.example.composeintegration.fragments.compose.people_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeintegration.di.repositories.UserRepository
import com.example.composeintegration.network.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ComposeFragmentViewModel @Inject constructor(
    val userRepository: UserRepository
): ViewModel() {
    private val _peopleData: MutableState<PeopleDataUiState> = mutableStateOf(PeopleDataUiState.InProgress)
    val peopleData: State<PeopleDataUiState> = _peopleData

    private val _navigateToState: MutableState<NavigateToState> = mutableStateOf(NavigateToState.Initialized)
    val navigateToState: State<NavigateToState> = _navigateToState


    fun getUserData() {
        viewModelScope.launch {
            kotlin.runCatching {
                _peopleData.value = PeopleDataUiState.InProgress
                userRepository.getUsers()
            }.onSuccess {
                _peopleData.value = PeopleDataUiState.Success(it)
            }.onFailure {
                _peopleData.value = PeopleDataUiState.Error(it)
            }
        }
    }

    fun navigateTo(destination: NavigateToState) {
        viewModelScope.launch {
            _navigateToState.value = destination
        }
    }

    fun resetNavigationData() {
        _navigateToState.value = NavigateToState.Initialized
    }

    sealed class PeopleDataUiState {
        data class Success(val users: List<User>?): PeopleDataUiState()
        data class Error(val exception: Throwable): PeopleDataUiState()
        object InProgress : PeopleDataUiState()
    }

    sealed class NavigateToState {
        object Initialized: NavigateToState()
        object ToDetailsFragment: NavigateToState()
    }
}
