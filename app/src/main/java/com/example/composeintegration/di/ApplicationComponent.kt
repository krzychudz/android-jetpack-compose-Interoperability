package com.example.composeintegration.di

import com.example.composeintegration.di.modules.NetworkModule
import com.example.composeintegration.di.repositories.UserRepository
import com.example.composeintegration.fragments.compose.people_list.ComposeFragment
import com.example.composeintegration.network.NetworkService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NetworkModule::class
])
interface ApplicationComponent {
    fun networkService(): NetworkService
    fun userRepository(): UserRepository

    fun inject(composeFragment: ComposeFragment)
}