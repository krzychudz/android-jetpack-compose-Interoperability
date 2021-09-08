package com.example.composeintegration.fragments.compose.people_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common_data.result.ResultState
import com.example.composeintegration.di.repositories.UserRepository
import com.example.composeintegration.network.models.User
import kotlinx.coroutines.launch

class ComposeFragmentViewModel constructor(
    val userRepository: UserRepository
): ViewModel() {

    private val _peopleData: MutableState<ResultState<List<User>>> = mutableStateOf(ResultState.InProgress)
    val peopleData: State<ResultState<List<User>>> = _peopleData

    private val _navigateToState: MutableState<NavigateToState> = mutableStateOf(NavigateToState.Initialized)
    val navigateToState: State<NavigateToState> = _navigateToState


    fun getUserData() {
        viewModelScope.launch {
            kotlin.runCatching {
                _peopleData.value = ResultState.InProgress
                userRepository.getUsers()
            }.onSuccess {
                _peopleData.value = ResultState.Success(it)
            }.onFailure {
                _peopleData.value = ResultState.Error(it)
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

    fun getPersonByUUID(personUUID: String?): User? {
        val peopleList = (_peopleData.value as? ResultState.Success)?.data
        return peopleList?.find { person -> person.login?.uuid == personUUID }
    }

    sealed class NavigateToState {
        object Initialized: NavigateToState()
        data class ToDetailsFragment(val selectedPersonUUID: String? = null): NavigateToState()
    }
}
