package com.example.composeintegration.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.composeintegration.di.repositories.UserRepository
import com.example.composeintegration.fragments.compose.people_list.ComposeFragmentViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(
    private val userRepository: UserRepository
    ): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        if (modelClass == ComposeFragmentViewModel::class.java) {
            ComposeFragmentViewModel(userRepository = userRepository) as T
        } else {
            throw  IllegalStateException("Unknown entity")
        }
}