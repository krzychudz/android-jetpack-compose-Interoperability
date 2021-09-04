package com.example.composeintegration.fragments.compose.people_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeintegration.di.repositories.UserRepository
import com.example.composeintegration.network.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ComposeFragmentViewModel @Inject constructor(
    val userRepository: UserRepository
): ViewModel() {
    private val _peopleData = MutableStateFlow<PeopleDataUiState>(PeopleDataUiState.InProgress)
    val peopleData: StateFlow<PeopleDataUiState> = _peopleData

    private val _navigateToState = MutableStateFlow<NavigateToState>(NavigateToState.Initialized)
    val navigateToState: StateFlow<NavigateToState> = _navigateToState


    fun getUserData() {
        viewModelScope.launch {
            kotlin.runCatching {
                _peopleData.emit(PeopleDataUiState.InProgress)
                userRepository.getUsers()
            }.onSuccess {
                _peopleData.emit(PeopleDataUiState.Success(it))
            }.onFailure {
                _peopleData.emit(PeopleDataUiState.Error(it))
            }
        }
    }

    fun navigateTo(destination: NavigateToState) {
        viewModelScope.launch {
            _navigateToState.emit(destination)
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
